package beans;

import java.io.Serializable;

public class ContactInfo implements Serializable {
    private int id;
    private String phone;
    private String email;

    public ContactInfo() {
    }

    private ContactInfo(Builder builder) {
        this.id = builder.id;
        this.phone = builder.phone;
        this.email = builder.email;
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

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("ContactInfo{");
        sb.append("id=").append(id);
        sb.append(", phone='").append(phone).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static class Builder {
        private int id;
        private String phone;
        private String email;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setPhone(String phone) {
            this.phone = phone;
            return this;
        }

        public Builder setEmail(String email) {
            this.email = email;
            return this;
        }

        public ContactInfo build() {
            return new ContactInfo(this);
        }
    }
}
