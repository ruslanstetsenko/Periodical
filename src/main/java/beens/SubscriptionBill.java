package beens;

import java.io.Serializable;
import java.sql.Date;
import java.time.LocalDate;
import java.time.ZoneId;

public class SubscriptionBill implements Serializable {
    private int id;
    private double totalCost;
    private int validityPeriod = 5;
    private int paid = 1;
    private String billNumber = "";
    private Date billSetDay  = new Date(java.util.Date.from(LocalDate.now().atStartOfDay(ZoneId.systemDefault()).toInstant()).getTime());
    private int userId;

    public SubscriptionBill() {
    }

    private SubscriptionBill(Builder builder) {
        this.id = builder.id;
        this.totalCost = builder.totalCost;
        this.validityPeriod = builder.validityPeriod;
        this.paid = builder.paid;
        this.billNumber = builder.billNumber;
        this.userId = builder.userId;
        this.billSetDay = builder.billSetDay;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTotalCost() {
        return totalCost;
    }

    public void setTotalCost(double totalCost) {
        this.totalCost = totalCost;
    }

    public int getValidityPeriod() {
        return validityPeriod;
    }

    public void setValidityPeriod(int validityPeriod) {
        this.validityPeriod = validityPeriod;
    }

    public int getPaid() {
        return paid;
    }

    public void setPaid(int paid) {
        this.paid = paid;
    }

    public String getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
    }

    public Date getBillSetDay() {
        return billSetDay;
    }

    public void setBillSetDay(Date billSetDay) {
        this.billSetDay = billSetDay;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubscriptionBill that = (SubscriptionBill) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("SubscriptionBill{");
        sb.append("id=").append(id);
        sb.append(", totalCost=").append(totalCost);
        sb.append(", validityPeriod=").append(validityPeriod);
        sb.append(", paid=").append(paid);
        sb.append(", billNumber='").append(billNumber).append('\'');
        sb.append(", billSetDay=").append(billSetDay);
        sb.append('}');
        return sb.toString();
    }

    public static class Builder {
        private int id;
        private double totalCost;
        private int validityPeriod;
        private int paid;
        private String billNumber;
        private Date billSetDay;
        private int userId;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setTotalCost(double totalCost) {
            this.totalCost = totalCost;
            return this;
        }

        public Builder setValidityPeriod(int validityPeriod) {
            this.validityPeriod = validityPeriod;
            return this;
        }

        public Builder setPaid(int paid) {
            this.paid = paid;
            return this;
        }

        public Builder setBillSetDay(Date billSetDay) {
            this.billSetDay = billSetDay;
            return this;
        }

        public Builder setUserId(int userId) {
            this.userId = userId;
            return this;
        }

        public Builder setBillNumber(String billNumber) {
            this.billNumber = billNumber;
            return this;
        }

        public SubscriptionBill build() {
            return new SubscriptionBill(this);
        }
    }
}
