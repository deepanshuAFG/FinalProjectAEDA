import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SurplusFoodDAOImpl implements SurplusFoodDAO {
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/fwrp";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "Aaniq**143**3675";

    public SurplusFoodDAOImpl(Connection connection) {
		// TODO Auto-generated constructor stub
	}

	@Override
    public List<SurplusFood> getDiscountedItems() throws SQLException {
        List<SurplusFood> discountedItems = new ArrayList<>();
        try (Connection connection = DriverManager.getConnection(JDBC_URL, USERNAME, PASSWORD);
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM surplus_food WHERE discounted_price IS NOT NULL");
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                SurplusFood surplusFood = new SurplusFood();
                surplusFood.setSurplusId(resultSet.getInt("surplus_id"));
                surplusFood.setRetailerId(resultSet.getInt("retailer_id"));
                surplusFood.setItemName(resultSet.getString("item_name"));
                surplusFood.setQuantity(resultSet.getInt("quantity"));
                surplusFood.setExpirationDate(resultSet.getDate("expiration_date"));
                surplusFood.setDonation(resultSet.getBoolean("is_donation"));
                surplusFood.setDiscountedPrice(resultSet.getDouble("discounted_price"));
                discountedItems.add(surplusFood);
            }
        }
        return discountedItems;
    }

	@Override
	public void updateQuantity(int surplusId, int quantity) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public SurplusFood get(int surplusId) {
		// TODO Auto-generated method stub
		return null;
	}
}