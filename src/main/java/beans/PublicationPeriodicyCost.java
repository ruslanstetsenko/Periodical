package beans;

import java.io.Serializable;

public class PublicationPeriodicyCost implements Serializable {
    private int id;
    private int timesPerYear;
    private double cost;
    private Integer publicationId;

    public PublicationPeriodicyCost() {
    }

    private PublicationPeriodicyCost(Builder builder) {
        this.id = builder.id;
        this.timesPerYear = builder.timesPerYear;
        this.cost = builder.cost;
        this.publicationId = builder.publicationId;
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

        PublicationPeriodicyCost that = (PublicationPeriodicyCost) o;

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

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PublicationPeriodicyCost{");
        sb.append("id=").append(id);
        sb.append(", timesPerYear=").append(timesPerYear);
        sb.append(", cost=").append(cost);
        sb.append(", publicationId=").append(publicationId);
        sb.append('}');
        return sb.toString();
    }

    public static class Builder {
        private int id;
        private int timesPerYear;
        private double cost;
        private Integer publicationId;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setTimesPerYear(int timesPerYear) {
            this.timesPerYear = timesPerYear;
            return this;
        }

        public Builder setCost(double cost) {
            this.cost = cost;
            return this;
        }

        public Builder setPublicationId(Integer publicationId) {
            this.publicationId = publicationId;
            return this;
        }

        public PublicationPeriodicyCost build() {
            return new PublicationPeriodicyCost(this);
        }
    }
}
