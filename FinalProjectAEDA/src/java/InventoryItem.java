import java.time.LocalDate;

public class InventoryItem {
    private int itemId;
    private int retailerId;
    private String itemName;
    private int quantity;
    private double discounted_price;
    private LocalDate expirationDate;

    public InventoryItem(int itemId, int retailerId, String itemName, int quantity, LocalDate expirationDate, double discounted_price) {
        this.itemId = itemId;
        this.retailerId = retailerId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
        this.discounted_price = discounted_price;
    }

    // Getters and setters
    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public int getRetailerId() {
        return retailerId;
    }
    public double getdiscounted_price() {
        return discounted_price;
    }
    public void setdiscounted_price(double discounted_price) {
        this.discounted_price = discounted_price;
    }
    public void setRetailerId(int retailerId) {
        this.retailerId = retailerId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(LocalDate expirationDate) {
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "InventoryItem{" +
                "itemId=" + itemId +
                ", retailerId=" + retailerId +
                ", itemName='" + itemName + '\'' +
                ", quantity=" + quantity +
                ", expirationDate=" + expirationDate +
                '}';
    }
}
