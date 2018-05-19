package beans;

import java.io.Serializable;

public class PublicationType implements Serializable {
    private int id;
    private String typeName;

    public PublicationType() {
    }

    private PublicationType(Builder builder) {
        this.id = builder.id;
        this.typeName = builder.typeName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PublicationType that = (PublicationType) o;

        return typeName != null ? typeName.equals(that.typeName) : that.typeName == null;
    }

    @Override
    public int hashCode() {
        return typeName != null ? typeName.hashCode() : 0;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PublicationType{");
        sb.append("id=").append(id);
        sb.append(", typeName='").append(typeName).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static class Builder {
        private int id;
        private String typeName;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setTypeName(String typeName) {
            this.typeName = typeName;
            return this;
        }

        public PublicationType build() {
            return new PublicationType(this);
        }
    }
}
