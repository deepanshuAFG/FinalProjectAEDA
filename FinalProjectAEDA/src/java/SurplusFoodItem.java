package Java;

import java.math.BigDecimal;
import java.util.Date;

/**
 *
 * @author ELISHA KALYAN
 */
public class SurplusFoodItem {
    private int id;
    private int retailerId;
    private String itemName;
    private int quantity;
    private Date expirationDate;
    private boolean isDonation;
    private BigDecimal discountedPrice;

    // Default constructor
    public SurplusFoodItem() {
    }

    // Constructor with all fields
    public SurplusFoodItem(int id, int retailerId, String itemName, int quantity, 
                           Date expirationDate, boolean isDonation, BigDecimal discountedPrice) {
        this.id = id;
        this.retailerId = retailerId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
        this.isDonation = isDonation;
        this.discountedPrice = discountedPrice;
    }

    public void setRetailerId(int retailerId) {
        this.retailerId = retailerId;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public void setIsDonation(boolean isDonation) {
        this.isDonation = isDonation;
    }

    public void setDiscountedPrice(BigDecimal discountedPrice) {
        this.discountedPrice = discountedPrice;
    }

    public int getRetailerId() {
        return retailerId;
    }

    public String getItemName() {
        return itemName;
    }

    public int getQuantity() {
        return quantity;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public boolean isIsDonation() {
        return isDonation;
    }

    public BigDecimal getDiscountedPrice() {
        return discountedPrice;
    }

    // Getters and Setters
    // Example:
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Include getters and setters for all other fields

    @Override
    public String toString() {
        return "SurplusFoodItem{" +
                "id=" + id +
                ", retailerId=" + retailerId +
                ", itemName='" + itemName + '\'' +
                ", quantity=" + quantity +
                ", expirationDate=" + expirationDate +
                ", isDonation=" + isDonation +
                ", discountedPrice=" + discountedPrice +
                '}';
    }
}


