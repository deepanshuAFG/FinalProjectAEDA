<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.util.List" %>
<%@ page import="Java.SurplusFoodItem" %> <!-- Adjust if necessary. Case-sensitive and must match your class's package. -->

<!DOCTYPE html>
<html>
<head>
    <title>Surplus Food List</title>
    <link rel='stylesheet' type='text/css' href='styles.css'> <!-- Ensure you have styles.css in your web directory -->
    <script>
        // Assume you have filled in the necessary JavaScript functions here
        function updateTotal(id, name) {
            // Function to update the total selections
        }

        function submitSelections() {
            // Function to submit the selected items
        }
    </script>
</head>
<body>
<h2>Surplus Food List</h2>
<form id="claimForm">
    <table>
        <tr>
            <th>Name</th>
            <th>Available Quantity</th>
            <th>Select Quantity</th>
        </tr>
        <% 
        // Attempt to retrieve the foodItems attribute.
        Object foodItemsObj = request.getAttribute("foodItems");
        if (foodItemsObj != null && foodItemsObj instanceof List<?>) {
            List<?> rawFoodItems = (List<?>) foodItemsObj;
            for (Object itemObj : rawFoodItems) {
                if (itemObj instanceof SurplusFoodItem) {
                    SurplusFoodItem item = (SurplusFoodItem) itemObj;
        %>
                <tr>
                    <td><%= item.getItemName() %></td>
                    <td><%= item.getQuantity() %></td>
                    <td>
                        <input type="number" id="quantity-<%= item.getId() %>" name="quantity-<%= item.getId() %>"
                               value="0" min="0" max="<%= item.getQuantity() %>"
                               oninput="updateTotal(<%= item.getId() %>, '<%= item.getItemName() %>')">
                    </td>
                </tr>
        <% 
                }
            }
        } else {
            // Handle the case where foodItems is not available or not a List.
            out.println("<tr><td colspan='3'>No items available.</td></tr>");
        }
        %>
    </table>
    <input type="button" value="Submit Selections" onclick="submitSelections()">
</form>

<h3>Selected Items:</h3>
<ul id="selectedItemsList">
    <!-- Selected items will be listed here -->
</ul>
</body>
</html>
