import java.sql.SQLException;
import java.util.List;
public interface SurplusFoodDAO {
    List<SurplusFood> getDiscountedItems() throws SQLException;

	void updateQuantity(int surplusId, int quantity);
	SurplusFood get(int surplusId);
}