# Food Waste Reduction Platform (FWRP) Documentation

## Version History

- **Version:** 1.0
- **Date:** March 22, 2024
- **Authors:**
  - Elisha Kalyan
  - Armando Mavova Bazeyidio
  - Deepanshu
  - AaniqAllaudin

## 1. Introduction

### About the System

The Food Waste Reduction Platform (FWRP) is a web-based application aimed at reducing food waste by facilitating connections among food retailers, consumers, and charitable organizations. It streamlines the redistribution of surplus food to address hunger and enhance food sustainability within ecosystems. The platform includes features like user registration, inventory management, and the listing and claiming of surplus food.

### Purpose of the Document

This document provides a detailed development plan for the FWRP, outlining its structure, design considerations, and functional requirements. It is intended for developers, project managers, and prospective users, among other stakeholders, to guide the development process from conceptualization to implementation.

## 2. Targeted Audience

- **Retailers:** Businesses with food inventory looking to reduce surplus by donating or selling at discounted prices.
- **Consumers:** Individuals seeking to purchase food at reduced prices or contribute to sustainability efforts by utilizing surplus food.
- **Charitable Organizations:** Non-profits aiming to streamline the acquisition of donations to aid those in need.

## 3. Scope

### In Scope

- Web-based platform development for linking food retailers, consumers, and charities.
- User registration and authentication processes.
- Retailer inventory management tools.
- Support features for charities to claim food and consumers to buy surplus food at discounts.
- Notification system for alerting users about surplus food.
- RDBMS integration for efficient data management.

### Out of Scope

- Excluding real money transactions.
- No external logistics or food delivery integration.
- Development will not include real-time chat features, only basic notifications.
- The platform will provide basic inventory management tools for retailers.

## 4. Application Architecture

### Overview

The FWRP addresses the global issue of food waste by connecting stakeholders across the food supply chain, promoting sustainability, reducing hunger, and building resilient food ecosystems.

### Key Features

- **User Registration:** For retailers, consumers, and charitable organizations.
- **Inventory Management:** Allows retailers to manage, add, and update inventory.
- **Surplus Food Identification:** Retailers can flag surplus food for donation or sale.
- **Claiming and Purchasing Food Items:** Enables charities to claim and consumers to purchase surplus food.
- **Surplus Food Alerts:** Users receive notifications based on preferences and location.

### High-Level Overview

The FWRP uses a three-tier architecture:

- **Presentation Layer:** Handles UI and user interactions, employing the MVC design pattern.
- **Business Layer:** Contains core functionalities and business logic, processing user requests.
- **Database Layer:** Manages persistent data, ensuring data integrity and consistency.

### Components

#### Presentation Layer Components

- **User Interface (UI):** Graphical interface for user interaction.
- **Controllers:** Manage user requests and actions.
- **Views:** Display UI elements and data.

#### Business Layer Components

- **Controllers/Handlers:** Process incoming requests.
- **Services:** Implement business logic.
- **Data Access Objects (DAOs):** Perform CRUD operations.

#### Database Layer Components

- **Database Management System (DBMS):** Manages data storage and retrieval.
- **Tables/Entities:** Represent data entities and relationships.
- **Queries:** SQL queries for data manipulation.

### 6. Business Architecture:

#### Retailer Use Cases:
- **Manage Inventory**:
  - *Description*: Retailers can add, update, and remove items from their inventory.
  - *Actors*: Retailer
  - *Actions*: Add Item, Update Item, Remove Item
![image](https://github.com/deepanshuAFG/FinalProjectAEDA/assets/156712128/6fe2d2ac-5e79-474a-8dd2-136369a4ffd5)

- **Identify Surplus Food**:
  - *Description*: Retailers can identify and flag surplus food items nearing expiration or more than demand.
  - *Actors*: Retailer
  - *Actions*: Flag Surplus Food

- **List Surplus Food Items**:
  - *Description*: Retailers can list surplus food items on the platform for donation or sale.
  - *Actors*: Retailer
  - *Actions*: List Item for Donation, List Item for Sale

#### Charitable Organization Use Cases:
- **Claim Food Items**:
  - *Description*: Charitable organizations can claim surplus food items listed by retailers for donation.
  - *Actors*: Charitable Organization
  - *Actions*: Claim Food Item
- **Update Inventory**:
  - *Description*: Retailer inventory is updated once a food item is claimed by a charitable organization.
  - *Actors*: Charitable Organization, Retailer
  - *Actions*: Update Retailer Inventory

#### Consumer Use Cases:
- **Purchase Food Items**:
  - *Description*: Consumers can purchase surplus food items listed by retailers at discounted rates.
  - *Actors*: Consumer
  - *Actions*: Purchase Item

- **Update Inventory**:
  - *Description*: Retailer inventory is updated once a food item is purchased by a consumer.
  - *Actors*: Retailer, Consumer
  - *Actions*: Update Retailer Inventory

#### General User Cases:
- **User Registration**:
  - *Description*: Users can register on the platform by providing necessary details.
  - *Actors*: User
  - *Actions*: Register

- **User Authentication**:
  - *Description*: Users can log in and log out of the platform.
  - *Actors*: User
  - *Actions*: Login, Logout

- **Subscribe to Surplus Food Alerts**:
  - *Description*: Users can subscribe to receive surplus food alerts based on location, communication method, and food preferences.
  - *Actors*: User
  - *Actions*: Subscribe to Alerts

- **Receive Automatic Notifications**:
  - *Description*: Users subscribed to alerts receive automatic notifications whenever retailers list surplus food items on the platform.
  - *Actors*: User
  - *Actions*: Receive Notifications

### 7. Detailed Design:
![image](https://github.com/deepanshuAFG/FinalProjectAEDA/assets/156712128/b890c3fd-fe16-45b0-b083-c173ea993208)

#### Classes:
- **User**
- **Consumer**
- **Retailer**
- **Charitable Organization**
- **Inventory Items**
- **Purchase Items**
- **Claimed Items**

##### User Class:
Represents an entity within the system with attributes like userID, name, email, password, and userType. Supports actions like registering, logging in, logging out, subscribing to alerts, and receiving notifications.

##### Retailer Class:
A business entity managing an inventory of InventoryItem objects. Can manage inventory, identify surplus food, and list items for donation or sale.

##### Charitable Organization:
A non-profit entity managing claimed items. Can claim surplus food items for donation and update retailer inventory accordingly.

##### Consumer:
An individual with a list of purchased items. Can purchase surplus food items from retailers, with actions to facilitate purchase and inventory update.

##### InventoryItem:
Represents an item within a retailer's inventory with attributes like itemID, name, quantity, expiryDate, and isSurplus flag.

##### ClaimedItem:
Represents a food item claimed by a charitable organization, including a claimID and reference to the InventoryItem object.

##### PurchaseItem:
Represents a food item purchased by a consumer, including a purchaseID and reference to the InventoryItem object.

#### Project Process Diagram (Component Diagram):
![image](https://github.com/deepanshuAFG/FinalProjectAEDA/assets/156712128/c3054732-6a5a-4ab6-b40d-23a0dff31161)

##### Presentation Component:
The initial interaction point for users, including UI elements for data input and action triggers.

##### Business Component:
Houses business logic and functionalities, processing user requests and executing business rules.

##### Database Component:
Manages persistent data storage and retrieval, interacting with the Business component for data operations.

### 8. Database:

#### ERD DIAGRAM (Version 1.0)
![image](https://github.com/deepanshuAFG/FinalProjectAEDA/assets/156712128/23e0ee4b-cd9b-473b-9b11-09e2f4f9d2e7)

#### LOGICAL DATA MODEL:

The logical data model captures entities, attributes, and relationships essential for the system, organized into tables like Users, Retailers, Surplus Food, Consumers, Charitable Organizations, Inventory, and Subscriptions. It adheres to normalization principles and ensures referential integrity for data consistency and integrity.
