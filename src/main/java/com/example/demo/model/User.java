package com.example.demo.model;

import com.example.demo.common.model.lcp.UserStatus;
import com.example.demo.common.model.lcp.UserType;

import java.io.Serializable;

/**
 * The user model
 *
 * @author <a href="mailto:lstonoyan@gmail.com">Levon Tonoyan</a>
 */
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private long id;

    private String firstName;

    private String lastName;

    private UserType userType;

    private UserStatus userStatus;

    public User() {
        userType = UserType.REGULAR;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public UserStatus getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(UserStatus userStatus) {
        this.userStatus = userStatus;
    }
}
