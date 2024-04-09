<%-- 
    Document   : Inventory
    Created on : 8 Apr 2024, 1:00:14 PM
    Author     : UsEr
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="css/Inventory.css">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Inventory</title>
</head>
<body>
    <h1>Inventory</h1>

   <div class="large-box">
        <table>
            <thead>
                <tr>
                    <th class="header">Name</th>
                    <th class="header">Quantity</th>
                    <th class="header">Expiration Date</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="item" items="${inventoryItems}">
                    <tr>
                        <td>${item.itemName}</td>
                        <td>${item.quantity}</td>
                        <td>${item.expirationDate}</td>
                    </tr>
                </c:forEach>
                <c:if test="${empty inventoryItems}">
                    <p>No inventory items found.</p>
                </c:if>
            </tbody>
        </table>
    </div>

   <!-- Buttons for navigation -->
    <div class="button-container">
        <form action="Retailers.jsp" method="get"> 
            <button type="submit" name="action" value="goBack" class="back-button">PREVIOUS</button>
        </form>
        <form action="AddItem.jsp" method="get">
            <button type="submit" class="add-button">ADD</button>
        </form>
        <form action="UpdateItem.jsp" method="get">
            <button type="submit" class="update-button">UPDATE</button>
        </form>
        <form action="DeleteItem.jsp" method="get">
            <button type="submit" class="delete-button">DELETE</button>
        </form>
    </div>
  
    
    

</body>
</html>