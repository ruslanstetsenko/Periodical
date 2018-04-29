package entity;

import java.io.Serializable;

public class PublicationStatus implements Serializable {
    private int id;
    private String statusName;

    public PublicationStatus() {
    }

    private PublicationStatus(Builder builder) {
        this.id = builder.id;
        this.statusName = builder.statusName;
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

        PublicationStatus that = (PublicationStatus) o;

        return statusName != null ? statusName.equals(that.statusName) : that.statusName == null;
    }

    @Override
    public int hashCode() {
        return statusName != null ? statusName.hashCode() : 0;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PublicationStatus{");
        sb.append("id=").append(id);
        sb.append(", statusName='").append(statusName).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static class Builder {
        private int id;
        private String statusName;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setStatusName(String statusName) {
            this.statusName = statusName;
            return this;
        }

        public PublicationStatus build() {
            return new PublicationStatus(this);
        }
    }
}
