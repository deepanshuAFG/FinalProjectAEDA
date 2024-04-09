import java.util.Date;

public class SurplusFood {
    private int surplusId;
    private int retailerId;
    private String itemName;
    private int quantity;
    private Date expirationDate;
    private boolean isDonation;
    private double discountedPrice;

    // Default constructor
    public SurplusFood() {
    }

    // Parameterized constructor
    public SurplusFood(int surplusId, int retailerId, String itemName, int quantity, Date expirationDate, boolean isDonation, double discountedPrice) {
        this.surplusId = surplusId;
        this.retailerId = retailerId;
        this.itemName = itemName;
        this.quantity = quantity;
        this.expirationDate = expirationDate;
        this.isDonation = isDonation;
        this.discountedPrice = discountedPrice;
    }

    // Getters and Setters
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

    public Date getExpirationDate() {
        return expirationDate;
    }

    public void setExpirationDate(Date expirationDate) {
        this.expirationDate = expirationDate;
    }

    public boolean isDonation() {
        return isDonation;
    }

    public void setDonation(boolean donation) {
        isDonation = donation;
    }

    public double getDiscountedPrice() {
        return discountedPrice;
    }

    public void setDiscountedPrice(double discountedPrice) {
        this.discountedPrice = discountedPrice;
    }
}
