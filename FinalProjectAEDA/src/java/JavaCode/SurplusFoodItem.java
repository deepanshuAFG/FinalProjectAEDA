/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package JavaCode;

/**
 *
 * @author Armando
 */
import java.math.BigDecimal;
import java.time.LocalDate;

public class SurplusFoodItem {
    private int surplusId;
    private int retailerId;
    private String itemName;
    private int quantity;
    private LocalDate expirationDate;
    private boolean isDonation;
    private BigDecimal discountedPrice;

    // Constructor
    public SurplusFoodItem() {
    }

    // Getters and setters
    public int getSurplusId() {
        return surplusId;
    }

    public void setSurplusId(int surplusId) {
        this.surplusId = surplusId;
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

    public boolean isDonation() {
        return isDonation;
    }

    public void setDonation(boolean donation) {
        isDonation = donation;
    }

    public BigDecimal getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(BigDecimal discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    // toString method
    @Override
    public String toString() {
        return "SurplusFoodItem{" +
                "surplusId=" + surplusId +
                ", retailerId=" + retailerId +
                ", itemName='" + itemName + '\'' +
                ", quantity=" + quantity +
                ", expirationDate=" + expirationDate +
                ", isDonation=" + isDonation +
                ", discountedPrice=" + discountedPrice +
                '}';
    }
}