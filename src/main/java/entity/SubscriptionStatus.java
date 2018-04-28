package entity;

import java.io.Serializable;

public class SubscriptionStatus implements Serializable {
    private int id;
    private String statusName;

    public SubscriptionStatus() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SubscriptionStatus that = (SubscriptionStatus) o;

        return statusName != null ? statusName.equals(that.statusName) : that.statusName == null;
    }

    @Override
    public int hashCode() {
        return statusName != null ? statusName.hashCode() : 0;
    }
}
