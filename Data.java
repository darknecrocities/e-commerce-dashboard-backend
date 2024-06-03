import java.util.ArrayList;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class Data {
    private String[][] items;
    private int[][] prices;
    private int[][] quantities; 

    private ArrayList<String> usernames = new ArrayList<>();
    private ArrayList<String> passwords = new ArrayList<>();
    private ArrayList<String> purchaseRecords = new ArrayList<>();

    public ArrayList<String> getPurchaseRecords() {
        return purchaseRecords;
    }

    public Data() {
        initializeData();
        // Adding initial username and password
        usernames.add("darknecrocities");
        passwords.add("arcely27");
    }

    private void initializeData() {
        items = new String[][] {
            {"Laptop", "Desktop", "Ipad"},
            {"Phone", "Smartwatch", "Tablet"},
            {"Headphones", "Earphones", "Speakers"}
        };

        prices = new int[][] {
            {1000, 1500, 500},
            {800, 200, 300},
            {100, 50, 200}
        };

        quantities = new int[][] {
            {10, 20, 15},
            {30, 25, 20},
            {50, 40, 30}
        };
    }

    public String[][] getItems() {
        return items;
    }

    public int[][] getPrices() {
        return prices;
    }

    public int[][] getQuantities() {
        return quantities;
    }

    public void printData() {
        StringBuilder message = new StringBuilder();
        for (int i = 0; i < items.length; i++) {
            message.append("Category ").append(i + 1).append(":\n");
            for (int j = 0; j < items[i].length; j++) {
                message.append("Item: ").append(items[i][j]).append("\n")
                       .append("Price: $").append(prices[i][j]).append("\n")
                       .append("Quantity: ").append(quantities[i][j]).append("\n")
                       .append("----------------------\n");
            }
        }
        JOptionPane.showMessageDialog(null, message.toString());
    }

    public void login() {
        boolean loggedIn = false;
        while (!loggedIn) {
            JOptionPane.showMessageDialog(null, "E-commerce Dashboard\n[1] Login\n[2] Sign up");
            String choice = JOptionPane.showInputDialog("Enter your choice (1 for Login, 2 for Sign up):");

            if (choice != null) {
                switch (choice) {
                    case "1":
                        JOptionPane.showMessageDialog(null, "You selected Login");
                        String enteredUsername = JOptionPane.showInputDialog("Enter your username:");
                        String enteredPassword = JOptionPane.showInputDialog("Enter your password:");

                        if (authenticate(enteredUsername, enteredPassword)) {
                            JOptionPane.showMessageDialog(null, "Login successful!");
                            loggedIn = true;
                        } else {
                            JOptionPane.showMessageDialog(null, "Invalid username or password. Please try again.");
                        }
                        break;

                    case "2":
                        JOptionPane.showMessageDialog(null, "You selected Sign up");
                        String newUsername = JOptionPane.showInputDialog("Enter a new username:");
                        String newPassword = JOptionPane.showInputDialog("Enter a new password:");

                        if (signUp(newUsername, newPassword)) {
                            JOptionPane.showMessageDialog(null, "Sign up successful! Please log in.");
                            // Automatically prompt for login after sign up
                            enteredUsername = JOptionPane.showInputDialog("Enter your username:");
                            enteredPassword = JOptionPane.showInputDialog("Enter your password:");

                            if (authenticate(enteredUsername, enteredPassword)) {
                                JOptionPane.showMessageDialog(null, "Login successful!");
                                loggedIn = true;
                            } else {
                                JOptionPane.showMessageDialog(null, "Invalid username or password. Please try again.");
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "Username already exists. Please try again.");
                        }
                        break;

                    default:
                        JOptionPane.showMessageDialog(null, "Invalid choice, please enter 1 or 2.");
                        break;
                }
            } else {
                JOptionPane.showMessageDialog(null, "No choice made, exiting.");
                System.exit(0);
                break;
            }
        }
    }

    private boolean authenticate(String enteredUsername, String enteredPassword) {
        for (int i = 0; i < usernames.size(); i++) {
            if (usernames.get(i).equals(enteredUsername) && passwords.get(i).equals(enteredPassword)) {
                return true;
            }
        }
        return false;
    }

    private boolean signUp(String newUsername, String newPassword) {
        if (!usernames.contains(newUsername)) {
            usernames.add(newUsername);
            passwords.add(newPassword);
            return true;
        }
        return false;
    }

    public void buy() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the item name you want to buy: ");
        String itemToBuy = scanner.nextLine();
        if (itemToBuy != null) {
            boolean itemFound = false;
            for (int i = 0; i < items.length; i++) {
                for (int j = 0; j < items[i].length; j++) {
                    if (items[i][j].equalsIgnoreCase(itemToBuy)) {
                        itemFound = true;
                        System.out.print("Enter the quantity you want to buy: ");
                        String quantityString = scanner.nextLine();
                        try {
                            int quantityToBuy = Integer.parseInt(quantityString);
                            if (quantities[i][j] >= quantityToBuy) {
                                quantities[i][j] -= quantityToBuy;
                                String record = "Bought " + quantityToBuy + " " + items[i][j] + "(s) at $" + prices[i][j] + " each.";
                                purchaseRecords.add(record);
                                JOptionPane.showMessageDialog(null, "Purchase successful!\n" + record);
                            } else {
                                JOptionPane.showMessageDialog(null, "Insufficient quantity available.");
                            }
                        } catch (NumberFormatException e) {
                            JOptionPane.showMessageDialog(null, "Invalid quantity entered.");
                        }
                    }
                }
            }
            if (!itemFound) {
                JOptionPane.showMessageDialog(null, "Item not found.");
            }
        }
    }

    // Method to view purchase records
    public void viewPurchaseRecords() {
        StringBuilder message = new StringBuilder();
        if (purchaseRecords.isEmpty()) {
            message.append("No purchase records found.");
        } else {
            for (String record : purchaseRecords) {
                message.append(record).append("\n");
            }
        }
        JOptionPane.showMessageDialog(null, message.toString());
    }

    public void addItem(String newItem, int newPrice, int newQuantity) {
        // Create new arrays with increased size
        int newLength = items.length + 1;
        String[][] newItems = new String[newLength][];
        int[][] newPrices = new int[newLength][];
        int[][] newQuantities = new int[newLength][];
        
        // Copy existing data to the new arrays
        for (int i = 0; i < items.length; i++) {
            newItems[i] = items[i].clone(); // Clone the array to avoid reference sharing
            newPrices[i] = prices[i].clone(); // Clone the array to avoid reference sharing
            newQuantities[i] = quantities[i].clone(); // Clone the array to avoid reference sharing
        }

        newItems[newLength - 1] = new String[]{newItem};
        newPrices[newLength - 1] = new int[]{newPrice};
        newQuantities[newLength - 1] = new int[]{newQuantity};
        
        // Assign the new arrays to the instance variables
        items = newItems;
        prices = newPrices;
        quantities = newQuantities;
    }
}