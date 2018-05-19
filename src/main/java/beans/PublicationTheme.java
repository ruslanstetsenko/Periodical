package beans;

import java.io.Serializable;

public class PublicationTheme implements Serializable {
    private int id;
    private String themeName;

    public PublicationTheme() {
    }

    private PublicationTheme(Builder builder) {
        this.id = builder.id;
        this.themeName = builder.themeName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getThemeName() {
        return themeName;
    }

    public void setThemeName(String themeName) {
        this.themeName = themeName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PublicationTheme that = (PublicationTheme) o;

        return themeName != null ? themeName.equals(that.themeName) : that.themeName == null;
    }

    @Override
    public int hashCode() {
        return themeName != null ? themeName.hashCode() : 0;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PublicationTheme{");
        sb.append("id=").append(id);
        sb.append(", themeName='").append(themeName).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static class Builder {
        private int id;
        private String themeName;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setThemeName(String themeName) {
            this.themeName = themeName;
            return this;
        }

        public PublicationTheme build() {
            return new PublicationTheme(this);
        }
    }
}
