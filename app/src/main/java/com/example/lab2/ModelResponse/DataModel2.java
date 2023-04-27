package com.example.lab2.ModelResponse;

public class DataModel2 {
    private String userName;//
    private String email;
    private String rollNumber;//
    private String password ;
    private String message="" ;
    private String error="" ;
    String collegeName;//

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public String getUser_Id() {
        return user_Id;
    }

    public void setUser_Id(String user_Id) {
        this.user_Id = user_Id;
    }

    public String getCollegeName() {
        return collegeName;
    }

    public void setCollegeName(String collegeName) {
        this.collegeName = collegeName;
    }

    private String token;
    private User user ;
    private String user_Id;


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }


    // For Register
    public DataModel2(String userName, String collegeName,String rollNumber) {
        this.collegeName = collegeName;
        this.rollNumber = rollNumber;
        this.userName=userName;
    }

    //    For Login
    public DataModel2(String email, String password) {
        this.email = email;
        this.password = password;
    }



    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

//    public String getUsername() {
//        return username;
//    }

//    public void setUsername(String username) {
//        this.username = username;
//    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
