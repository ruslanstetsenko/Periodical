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
}
