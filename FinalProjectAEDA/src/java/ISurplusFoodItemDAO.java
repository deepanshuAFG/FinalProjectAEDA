package Java;

import java.util.List;
/**
 *
 * @author ELISHA KALYAN
 */
public interface ISurplusFoodItemDAO {
    List<SurplusFoodItem> listAllSurplusFoodItemsForDonation() throws Exception;
    boolean claimSurplusFoodItem(int surplusId) throws Exception;
}
