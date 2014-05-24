package utils;


import java.util.Date;

class UserWrapper {

    private Integer userId;

    private String userName;
    
    private String email;
    
    private Date birthDate;
    
    private String gender;
    
    private String phone;

    public UserWrapper(Integer userId, String userName, String email, Date birthDate, String gender, String phone) {
        this.userId = userId;
        
        this.userName = userName;
        this.email = email;
        this.birthDate = birthDate;
        this.gender = gender;
        this.phone = phone;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Integer getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getEmail() {
        return email;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public String getGender() {
        return gender;
    }

    public String getPhone() {
        return phone;
    }

    
}
