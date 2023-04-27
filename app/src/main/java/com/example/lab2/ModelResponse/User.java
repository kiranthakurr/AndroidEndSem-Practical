package com.example.lab2.ModelResponse;

public class User {
    private String username;
    private String email;
    private String _id;
    private String avatar;
    private String rollNumber , phone , roomNumber , branch ,hostelName,address;

    //    NOTE NOTE NOTE
    //make sure make the constrcutor according to the fields in the RESPONE User...otherwise they will be assigned in wrong manner in user properties
//    id,username,avatar,email --> same sequence as in response USER
    public User(String _id,String username,  String avatar,String email) {
        this.username = username;
        this.email = email;
        this._id = _id;
        this.avatar = avatar;
    }

    public User(String _id,String username,String avatar, String email ,  String rollNumber, String branch) {
        this.username = username;
        this.email = email;
        this._id = _id;
        this.avatar = avatar;
        this.rollNumber = rollNumber;
        this.branch = branch;
    }

    public User(String id, String username, String avatar, String email, String rollNumber, String branch, String address, String phone) {
        this.username = username;
        this.email = email;
        this._id = id;
        this.avatar = avatar;
        this.rollNumber = rollNumber;
        this.branch = branch;
        this.address = address;
        this.phone=phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(String roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    public String getHostelName() {
        return hostelName;
    }

    public void setHostelName(String hostelName) {
        this.hostelName = hostelName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}

