package beans;

import java.io.Serializable;
import java.sql.Date;

public class Subscription implements Serializable {
    private int id;
    private Date subscriptionDate;
    private String subscriptionType;
    private double subscriptionCost;
    private Integer publicationId;
    private Integer subscriptionStatusId;
    private Integer usersId;
    private Integer subscriptionBillsId;

    public Subscription() {
    }

    private Subscription(Builder builder) {
        this.id = builder.id;
        this.subscriptionDate = builder.subscriptionDate;
        this.subscriptionType = builder.subscriptionType;
        this.subscriptionCost = builder.subscriptionCost;
        this.publicationId = builder.publicationId;
        this.subscriptionStatusId = builder.subscriptionStatusId;
        this.usersId = builder.usersId;
        this.subscriptionBillsId = builder.subscriptionBillsId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getSubscriptionDate() {
        return subscriptionDate;
    }

    public void setSubscriptionDate(Date subscriptionDate) {
        this.subscriptionDate = subscriptionDate;
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public void setSubscriptionType(String subscriptionType) {
        this.subscriptionType = subscriptionType;
    }

    public double getSubscriptionCost() {
        return subscriptionCost;
    }

    public void setSubscriptionCost(double subscriptionCost) {
        this.subscriptionCost = subscriptionCost;
    }

    public Integer getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(Integer publicationId) {
        this.publicationId = publicationId;
    }

    public Integer getSubscriptionStatusId() {
        return subscriptionStatusId;
    }

    public void setSubscriptionStatusId(Integer subscriptionStatusId) {
        this.subscriptionStatusId = subscriptionStatusId;
    }

    public Integer getUsersId() {
        return usersId;
    }

    public void setUsersId(Integer usersId) {
        this.usersId = usersId;
    }

    public Integer getSubscriptionBillsId() {
        return subscriptionBillsId;
    }

    public void setSubscriptionBillsId(Integer subscriptionBillsId) {
        this.subscriptionBillsId = subscriptionBillsId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Subscription that = (Subscription) o;

        if (Double.compare(that.subscriptionCost, subscriptionCost) != 0) return false;
        if (subscriptionDate != null ? !subscriptionDate.equals(that.subscriptionDate) : that.subscriptionDate != null)
            return false;
        if (subscriptionType != null ? !subscriptionType.equals(that.subscriptionType) : that.subscriptionType != null)
            return false;
        return publicationId != null ? publicationId.equals(that.publicationId) : that.publicationId == null;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = subscriptionDate != null ? subscriptionDate.hashCode() : 0;
        result = 31 * result + (subscriptionType != null ? subscriptionType.hashCode() : 0);
        temp = Double.doubleToLongBits(subscriptionCost);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (publicationId != null ? publicationId.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Subscription{");
        sb.append("id=").append(id);
        sb.append(", subscriptionDate=").append(subscriptionDate);
        sb.append(", subscriptionType='").append(subscriptionType).append('\'');
        sb.append(", subscriptionCost=").append(subscriptionCost);
        sb.append(", publicationId=").append(publicationId);
        sb.append(", subscriptionStatusId=").append(subscriptionStatusId);
        sb.append(", usersId=").append(usersId);
        sb.append(", subscriptionBillsId=").append(subscriptionBillsId);
        sb.append('}');
        return sb.toString();
    }

    public static class Builder {
        private int id;
        private Date subscriptionDate;
        private String subscriptionType;
        private double subscriptionCost;
        private Integer publicationId;
        private Integer subscriptionStatusId;
        private Integer usersId;
        private Integer subscriptionBillsId;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setSubscriptionDate(Date subscriptionDate) {
            this.subscriptionDate = subscriptionDate;
            return this;
        }

        public Builder setSubscriptionType(String subscriptionType) {
            this.subscriptionType = subscriptionType;
            return this;
        }

        public Builder setSubscriptionCost(double subscriptionCost) {
            this.subscriptionCost = subscriptionCost;
            return this;
        }

        public Builder setPublicationId(Integer publicationId) {
            this.publicationId = publicationId;
            return this;
        }

        public Builder setSubscriptionStatusId(Integer subscriptionStatusId) {
            this.subscriptionStatusId = subscriptionStatusId;
            return this;
        }

        public Builder setUsersId(Integer usersId) {
            this.usersId = usersId;
            return this;
        }

        public Builder setSubscriptionBillsId(Integer subscriptionBillsId) {
            this.subscriptionBillsId = subscriptionBillsId;
            return this;
        }

        public Subscription build() {
            return new Subscription(this);
        }
    }
}
