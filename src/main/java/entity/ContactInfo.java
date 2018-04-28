package entity;

import java.io.Serializable;

public class ContactInfo implements Serializable {
    private int id;
    private String phone;
    private String email;

    public ContactInfo() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ContactInfo that = (ContactInfo) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }
}
