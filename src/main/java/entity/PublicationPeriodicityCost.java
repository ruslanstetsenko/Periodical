package entity;

import java.io.Serializable;

public class PublicationPeriodicityCost implements Serializable {
    private int id;
    private int timesPerYear;
    private double cost;
    private int publicationId;

    public PublicationPeriodicityCost() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTimesPerYear() {
        return timesPerYear;
    }

    public void setTimesPerYear(int timesPerYear) {
        this.timesPerYear = timesPerYear;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getPublicationId() {
        return publicationId;
    }

    public void setPublicationId(int publicationId) {
        this.publicationId = publicationId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PublicationPeriodicityCost that = (PublicationPeriodicityCost) o;

        if (timesPerYear != that.timesPerYear) return false;
        return Double.compare(that.cost, cost) == 0;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = timesPerYear;
        temp = Double.doubleToLongBits(cost);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        return result;
    }
}
