package by.epam.movierating.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Даша on 25.01.2017.
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private int id;
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private Date dateRegister;
    private String email;
    //TODO think of status, add id to equals and hashcode;
    private String status;
    private boolean isAdmin;
    private boolean isBanned;

    private List<Review> reviewList;
    private List<Rating> ratingList;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getDateRegister() {
        return dateRegister;
    }

    public void setDateRegister(Date dateRegister) {
        this.dateRegister = dateRegister;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public void setAdmin(boolean admin) {
        isAdmin = admin;
    }

    public boolean isBanned() {
        return isBanned;
    }

    public void setBanned(boolean banned) {
        isBanned = banned;
    }

    public List<Review> getReviewList() {
        return reviewList;
    }

    public void setReviewList(List<Review> reviewList) {
        this.reviewList = reviewList;
    }

    public List<Rating> getRatingList() {
        return ratingList;
    }

    public void setRatingList(List<Rating> ratingList) {
        this.ratingList = ratingList;
    }

    public String getStatus() {return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
