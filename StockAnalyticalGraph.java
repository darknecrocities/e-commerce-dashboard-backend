import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import javax.swing.*;

public class StockAnalyticalGraph extends JFrame {

    private ArrayList<String> purchaseRecords;

    public StockAnalyticalGraph(ArrayList<String> purchaseRecords) {
        this.purchaseRecords = purchaseRecords;
        setTitle("Purchase Analysis");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        JPanel graphPanel = createGraphPanel();
        add(graphPanel);
        setVisible(true);
    }

    public JPanel createGraphPanel() {
        JPanel panel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                Graphics2D g2d = (Graphics2D) g;

                int width = getWidth();
                int height = getHeight();
                int padding = 40;

                // Analyze purchase records to find the count of each item
                HashMap<String, Integer> itemCounts = analyzePurchaseRecords();

                // Draw the title
                g2d.setColor(Color.black);
                g2d.drawString("Purchase Analysis", 20, 20);

                // Draw the bars
                int barWidth = (width - 2 * padding) / itemCounts.size();
                int x = padding;
                for (Map.Entry<String, Integer> entry : itemCounts.entrySet()) {
                    String itemName = entry.getKey();
                    int count = entry.getValue();
                    int barHeight = (int) ((double) count / getMaxCount(itemCounts) * (height - 2 * padding));
                    g2d.setColor(Color.blue);
                    g2d.fillRect(x, height - padding - barHeight, barWidth, barHeight);
                    g2d.setColor(Color.black);
                    g2d.drawString(itemName, x + barWidth / 2 - 10, height - padding + 15);
                    x += barWidth;
                }
            }
        };

        return panel;
    }

    private HashMap<String, Integer> analyzePurchaseRecords() {
        // Create a map to store the count of each item
        HashMap<String, Integer> itemCounts = new HashMap<>();

        // Iterate through purchase records and count each item
        for (String record : purchaseRecords) {
            String[] parts = record.split(" ");
            String itemName = parts[1]; // Assuming the item name is the second part
            itemCounts.put(itemName, itemCounts.getOrDefault(itemName, 0) + 1);
        }

        return itemCounts;
    }

    private int getMaxCount(HashMap<String, Integer> itemCounts) {
        int maxCount = 0;
        for (int count : itemCounts.values()) {
            if (count > maxCount) {
                maxCount = count;
            }
        }
        return maxCount;
    }

    public static void main(String[] args) {
        // Sample purchase records
        ArrayList<String> purchaseRecords = new ArrayList<>();
        purchaseRecords.add("Bought Laptop");
        purchaseRecords.add("Bought Laptop");
        purchaseRecords.add("Bought Desktop");
        purchaseRecords.add("Bought Tablet");

        // Create the graph
        SwingUtilities.invokeLater(() -> new StockAnalyticalGraph(purchaseRecords));
    }
}
