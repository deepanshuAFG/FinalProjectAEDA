/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Java;
import java.time.LocalDate;
/**
 *
 * @author ELISHA KALYAN
 */
public class InventoryItem {
    private int itemId;
    private int retailerId;
    private String itemName;
    private int quantity;
    private double discountedPrice; // Changed to camelCase
    private LocalDate expirationDate;

    public InventoryItem(int itemId, int retailerId, String itemName, int quantity, LocalDate expirationDate, double discountedPrice) {
        this.itemId = itemId;
        this.retailerId = retailerId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
        this.discountedPrice = discountedPrice; // Changed to camelCase
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

    public double getDiscountedPrice() { // Method name corrected
        return discountedPrice;
    }

    public void setDiscountedPrice(double discountedPrice) { // Method name corrected
        this.discountedPrice = discountedPrice;
    }

    @Override
    public String toString() {
        return "InventoryItem{" +
                "itemId=" + itemId +
                ", retailerId=" + retailerId +
                ", itemName='" + itemName + '\'' +
                ", quantity=" + quantity +
                ", expirationDate=" + expirationDate +
                ", discountedPrice=" + discountedPrice + // Changed to camelCase
                '}';
    }
}


