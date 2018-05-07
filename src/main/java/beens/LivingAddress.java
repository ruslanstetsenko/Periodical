package beens;

import java.io.Serializable;

public class LivingAddress implements Serializable {
    private int id;
    private String region;
    private String district;
    private String city;
    private String street;
    private String building;
    private String appartment;

    public LivingAddress() {
    }

    private LivingAddress(Builder builder) {
        this.id = builder.id;
        this.region = builder.region;
        this.district = builder.district;
        this.city = builder.city;
        this.street = builder.street;
        this.building = builder.building;
        this.appartment = builder.appartment;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getAppartment() {
        return appartment;
    }

    public void setAppartment(String appartment) {
        this.appartment = appartment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LivingAddress that = (LivingAddress) o;

        if (region != null ? !region.equals(that.region) : that.region != null) return false;
        if (district != null ? !district.equals(that.district) : that.district != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (street != null ? !street.equals(that.street) : that.street != null) return false;
        if (building != null ? !building.equals(that.building) : that.building != null) return false;
        return appartment != null ? appartment.equals(that.appartment) : that.appartment == null;
    }

    @Override
    public int hashCode() {
        int result = region != null ? region.hashCode() : 0;
        result = 31 * result + (district != null ? district.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (building != null ? building.hashCode() : 0);
        result = 31 * result + (appartment != null ? appartment.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("LivingAddress{");
        sb.append("id=").append(id);
        sb.append(", region='").append(region).append('\'');
        sb.append(", district='").append(district).append('\'');
        sb.append(", city='").append(city).append('\'');
        sb.append(", street='").append(street).append('\'');
        sb.append(", building='").append(building).append('\'');
        sb.append(", appartment='").append(appartment).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static class Builder {
        private int id;
        private String region;
        private String district;
        private String city;
        private String street;
        private String building;
        private String appartment;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setRegion(String region) {
            this.region = region;
            return this;
        }

        public Builder setDistrict(String district) {
            this.district = district;
            return this;
        }

        public Builder setCity(String city) {
            this.city = city;
            return this;
        }

        public Builder setStreet(String street) {
            this.street = street;
            return this;
        }

        public Builder setBuilding(String building) {
            this.building = building;
            return this;
        }

        public Builder setAppartment(String appartment) {
            this.appartment = appartment;
            return this;
        }

        public LivingAddress build() {
            return new LivingAddress(this);
        }
    }
}
