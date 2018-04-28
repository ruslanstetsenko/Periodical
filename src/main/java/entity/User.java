package entity;

import java.io.Serializable;
import java.sql.Date;

public class User implements Serializable {
    private int id;
    private String name;
    private String surname;
    private String lastName;
    private Date birthday;
    private Date registrationDate;
    private Integer passportIdentNumberId;
    private Integer accountsId;
    private Integer livingAddressId;
    private Integer contactInfoId;
    private Integer userRoleId;

    public User() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Integer getPassportIdentNumberId() {
        return passportIdentNumberId;
    }

    public void setPassportIdentNumberId(Integer passportIdentNumberId) {
        this.passportIdentNumberId = passportIdentNumberId;
    }

    public Integer getAccountsId() {
        return accountsId;
    }

    public void setAccountsId(Integer accountsId) {
        this.accountsId = accountsId;
    }

    public Integer getLivingAddressId() {
        return livingAddressId;
    }

    public void setLivingAddressId(Integer livingAddressId) {
        this.livingAddressId = livingAddressId;
    }

    public Integer getContactInfoId() {
        return contactInfoId;
    }

    public void setContactInfoId(Integer contactInfoId) {
        this.contactInfoId = contactInfoId;
    }

    public Integer getUserRoleId() {
        return userRoleId;
    }

    public void setUserRoleId(Integer userRoleId) {
        this.userRoleId = userRoleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        return id == user.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
