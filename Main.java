import javax.swing.JFrame;
import javax.swing.JPanel;

public class Main {
    public static void main(String[] args) {
        Data data = new Data(); // Initialize Data object
        data.login();

        while (true) { // Loop indefinitely until the user chooses to quit
            int choice;
            try {
                // Get user choice
                System.out.print("Welcome to E-Commerce Dashboard\n[1] View Report\n" +
                        "[2] Buy Items\n" +
                        "[3] Add Items\n" +
                        "[4] Quit\nEnter your choice (1-4): ");
                choice = Integer.parseInt(System.console().readLine());
                if (choice < 1 || choice > 4) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number between 1 and 4.");
                continue;
            }

            switch (choice) {
                case 1:
                    // Display the graph
                    System.out.println("Displaying the graph...");

                    // Create a JFrame to display the graph
                    JFrame graphFrame = new JFrame("E-Commerce Sales Report");
                    graphFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

                    // Get purchase records and create graph panel
                    JPanel graphPanel = new StockAnalyticalGraph(data.getPurchaseRecords()).createGraphPanel();
                    graphFrame.add(graphPanel);

                    // Set frame size and make it visible
                    graphFrame.setSize(800, 600);
                    graphFrame.setLocationRelativeTo(null);
                    graphFrame.setVisible(true);
                    break;


                case 2:
                    // Display the items when user selects to view report
                    data.printData();
                    data.buy();
                    break;

                case 3:
                    // Implement adding items
                    System.out.println("Adding items...");
                    System.out.print("Enter the name of Item: ");
                    String itemName = System.console().readLine();
                    System.out.print("Enter the price of Item: ");
                    String priceInput = System.console().readLine();
                    System.out.print("Enter the quantity of Item: ");
                    String quantityInput = System.console().readLine();
                    try {
                        int itemPrice = Integer.parseInt(priceInput);
                        int itemQuantity = Integer.parseInt(quantityInput);
                        data.addItem(itemName, itemPrice, itemQuantity);
                        System.out.println("Item added successfully!");
                    } catch (NumberFormatException ex) {
                        System.out.println("Invalid price or quantity entered!");
                    }
                    break;

                case 4:
                    System.exit(0); // Exit the program
                    break;

                default:
                    System.out.println("Invalid choice! Please enter a number between 1 and 4.");
            }
        }
    }
}
