package entity;

import java.io.Serializable;

public class SubscriptionBill implements Serializable {
    private int id;
    private double totalCost;
    private int validityPeriod;
    private byte paid;
    private String billNumber;

    public SubscriptionBill() {
    }

    private SubscriptionBill(Builder builder) {
        this.id = builder.id;
        this.totalCost = builder.totalCost;
        this.validityPeriod = builder.validityPeriod;
        this.paid = builder.paid;
        this.billNumber = builder.billNumber;
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

    public byte getPaid() {
        return paid;
    }

    public void setPaid(byte paid) {
        this.paid = paid;
    }

    public String getBillNumber() {
        return billNumber;
    }

    public void setBillNumber(String billNumber) {
        this.billNumber = billNumber;
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
        sb.append('}');
        return sb.toString();
    }

    public static class Builder {
        private int id;
        private double totalCost;
        private int validityPeriod;
        private byte paid;
        private String billNumber;

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

        public Builder setPaid(byte paid) {
            this.paid = paid;
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
