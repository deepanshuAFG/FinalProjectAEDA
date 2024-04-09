<%-- 
    Document   : AddItem
    Created on : 8 Apr 2024, 6:38:01 PM
    Author     : UsEr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Add Item</title>
</head>
<body>
    <h1>Add Item</h1>

    <!-- Form to add item -->
    <form action="InventoryServlet" method="post">
        <input type="hidden" name="action" value="add">
        <label for="itemName">Item Name:</label>
        <input type="text" id="itemName" name="itemName">
        <label for="quantity">Quantity:</label>
        <input type="number" id="quantity" name="quantity">
        <label for="expirationDate">Expiration Date:</label>
        <input type="date" id="expirationDate" name="expirationDate">
        <button type="submit">Add</button>
    </form>
</body>
</html>
