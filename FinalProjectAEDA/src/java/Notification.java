/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author SHIVANI
 */
public class Notification {
    private int id;
    private String userType;
    private String message;
    private String createdAt;
    private boolean readStatus;
    private String userType2;

    public Notification(int id, String userType, String message, String createdAt, boolean readStatus, String userType2) {
        this.id = id;
        this.userType = userType;
        this.message = message;
        this.createdAt = createdAt;
        this.readStatus = readStatus;
        this.userType2 = userType2;
    }

    // Getters and setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public boolean isReadStatus() {
        return readStatus;
    }

    public void setReadStatus(boolean readStatus) {
        this.readStatus = readStatus;
    }

    public String getUserType2() {
        return userType2;
    }

    public void setUserType2(String userType2) {
        this.userType2 = userType2;
    }
}
