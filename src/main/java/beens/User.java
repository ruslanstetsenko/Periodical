package beens;

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

    private User(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.surname = builder.surname;
        this.lastName = builder.lastName;
        this.birthday = builder.birthday;
        this.registrationDate = builder.registrationDate;
        this.passportIdentNumberId = builder.passportIdentNumberId;
        this.accountsId = builder.accountsId;
        this.livingAddressId = builder.livingAddressId;
        this.contactInfoId = builder.contactInfoId;
        this.userRoleId = builder.userRoleId;
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

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("User{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", surname='").append(surname).append('\'');
        sb.append(", lastName='").append(lastName).append('\'');
        sb.append(", birthday=").append(birthday);
        sb.append(", registrationDate=").append(registrationDate);
        sb.append(", passportIdentNumberId=").append(passportIdentNumberId);
        sb.append(", accountsId=").append(accountsId);
        sb.append(", livingAddressId=").append(livingAddressId);
        sb.append(", contactInfoId=").append(contactInfoId);
        sb.append(", userRoleId=").append(userRoleId);
        sb.append('}');
        return sb.toString();
    }

    public static class Builder {
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

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setSurname(String surname) {
            this.surname = surname;
            return this;
        }

        public Builder setLastName(String lastName) {
            this.lastName = lastName;
            return this;
        }

        public Builder setBirthday(Date birthday) {
            this.birthday = birthday;
            return this;
        }

        public Builder setRegistrationDate(Date registrationDate) {
            this.registrationDate = registrationDate;
            return this;
        }

        public Builder setPassportIdentNumberId(Integer passportIdentNumberId) {
            this.passportIdentNumberId = passportIdentNumberId;
            return this;
        }

        public Builder setAccountsId(Integer accountsId) {
            this.accountsId = accountsId;
            return this;
        }

        public Builder setLivingAddressId(Integer livingAddressId) {
            this.livingAddressId = livingAddressId;
            return this;
        }

        public Builder setContactInfoId(Integer contactInfoId) {
            this.contactInfoId = contactInfoId;
            return this;
        }

        public Builder setUserRoleId(Integer userRoleId) {
            this.userRoleId = userRoleId;
            return this;
        }

        public User build() {
            return new User(this);
        }
    }
}
