package beens;

import java.io.Serializable;
import java.sql.Date;

public class PassportIdentNumber implements Serializable {
    private int id;
    private String serial;
    private int number;
    private Date dateOfIssue;
    private String issuedBy;
    private int idNumber;

    public PassportIdentNumber() {
    }

    private PassportIdentNumber(Builder builder) {
        this.id = builder.id;
        this.serial = builder.serial;
        this.number = builder.number;
        this.dateOfIssue = builder.dateOfIssue;
        this.issuedBy = builder.issuedBy;
        this.idNumber = builder.idNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Date getDateOfIssue() {
        return dateOfIssue;
    }

    public void setDateOfIssue(Date dateOfIssue) {
        this.dateOfIssue = dateOfIssue;
    }

    public String getIssuedBy() {
        return issuedBy;
    }

    public void setIssuedBy(String issuedBy) {
        this.issuedBy = issuedBy;
    }

    public int getIdNumber() {
        return idNumber;
    }

    public void setIdNumber(int idNumber) {
        this.idNumber = idNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PassportIdentNumber that = (PassportIdentNumber) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PassportIdentNumber{");
        sb.append("id=").append(id);
        sb.append(", serial='").append(serial).append('\'');
        sb.append(", number=").append(number);
        sb.append(", dateOfIssue=").append(dateOfIssue);
        sb.append(", issuedBy='").append(issuedBy).append('\'');
        sb.append(", idNumber=").append(idNumber);
        sb.append('}');
        return sb.toString();
    }

    public static class Builder {
        private int id;
        private String serial;
        private int number;
        private Date dateOfIssue;
        private String issuedBy;
        private int idNumber;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setSerial(String serial) {
            this.serial = serial;
            return this;
        }

        public Builder setNumber(int number) {
            this.number = number;
            return this;
        }

        public Builder setDateOfIssue(Date dateOfIssue) {
            this.dateOfIssue = dateOfIssue;
            return this;
        }

        public Builder setIssuedBy(String issuedBy) {
            this.issuedBy = issuedBy;
            return this;
        }

        public Builder setIdNumber(int idNumber) {
            this.idNumber = idNumber;
            return this;
        }

        public PassportIdentNumber build() {
            return new PassportIdentNumber(this);
        }
    }
}
