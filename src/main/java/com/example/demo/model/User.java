package com.example.demo.model;

import com.example.demo.common.model.lcp.UserStatus;
import com.example.demo.common.model.lcp.UserType;
import com.example.demo.common.util.converter.enums.UserStatusConverter;
import com.example.demo.common.util.converter.enums.UserTypeConverter;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The user model
 *
 * @author <a href="mailto:lstonoyan@gmail.com">Levon Tonoyan</a>
 */
@Entity
@Table(name = "user")
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", insertable = false, updatable = false, nullable = false)
    private long id;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "user_type_id")
    @Convert(converter = UserTypeConverter.class)
    private UserType userType;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "user_status_id")
    @Convert(converter = UserStatusConverter.class)
    private UserStatus userStatus;

    public User() {
        userType = UserType.REGULAR;
        userStatus = UserStatus.NOT_ACTIVATED;
    }

    public User(String firstName, String lastName, String mobileNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.mobileNumber = mobileNumber;
        this.userType = UserType.REGULAR;
        this.userStatus = UserStatus.NOT_ACTIVATED;
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

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (firstName != null ? !firstName.equals(user.firstName) : user.firstName != null) return false;
        if (lastName != null ? !lastName.equals(user.lastName) : user.lastName != null) return false;
        if (mobileNumber != null ? !mobileNumber.equals(user.mobileNumber) : user.mobileNumber != null) return false;
        if (userType != user.userType) return false;
        return userStatus == user.userStatus;
    }

    @Override
    public int hashCode() {
        int result = (int) (id ^ (id >>> 32));
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (mobileNumber != null ? mobileNumber.hashCode() : 0);
        result = 31 * result + (userType != null ? userType.hashCode() : 0);
        result = 31 * result + (userStatus != null ? userStatus.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mobileNumber='" + mobileNumber + '\'' +
                ", userType=" + userType +
                ", userStatus=" + userStatus +
                '}';
    }
}
