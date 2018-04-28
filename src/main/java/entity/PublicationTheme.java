package entity;

import java.io.Serializable;

public class PublicationTheme implements Serializable {
    private int id;
    private String themeName;

    public PublicationTheme() {
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
}
