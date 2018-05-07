package beens;

import java.io.Serializable;
import java.sql.Date;

public class Publication implements Serializable {
    private int id;
    private String name;
    private int issnNumber;
    private Date registrationDate;
    private String website;
    private Integer publicationTypeId;
    private Integer publicationStatusId;
    private Integer publicationThemeId;

    public Publication() {
    }

    private Publication(Builder builder) {
        this.id = builder.id;
        this.name = builder.name;
        this.issnNumber = builder.issnNumber;
        this.registrationDate = builder.registrationDate;
        this.website = builder.website;
        this.publicationTypeId = builder.publicationTypeId;
        this.publicationStatusId = builder.publicationStatusId;
        this.publicationThemeId = builder.publicationThemeId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIssnNumber() {
        return issnNumber;
    }

    public void setIssnNumber(int issnNumber) {
        this.issnNumber = issnNumber;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public String getWebsite() {
        return website;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public Integer getPublicationTypeId() {
        return publicationTypeId;
    }

    public void setPublicationTypeId(Integer publicationTypeId) {
        this.publicationTypeId = publicationTypeId;
    }

    public Integer getPublicationStatusId() {
        return publicationStatusId;
    }

    public void setPublicationStatusId(Integer publicationStatusId) {
        this.publicationStatusId = publicationStatusId;
    }

    public Integer getPublicationThemeId() {
        return publicationThemeId;
    }

    public void setPublicationThemeId(Integer publicationThemeId) {
        this.publicationThemeId = publicationThemeId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Publication that = (Publication) o;

        return id == that.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Publication{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", issnNumber=").append(issnNumber);
        sb.append(", registrationDate=").append(registrationDate);
        sb.append(", website='").append(website).append('\'');
        sb.append(", publicationTypeId=").append(publicationTypeId);
        sb.append(", publicationStatusId=").append(publicationStatusId);
        sb.append(", publicationThemeId=").append(publicationThemeId);
        sb.append('}');
        return sb.toString();
    }

    public static class Builder {
        private int id;
        private String name;
        private int issnNumber;
        private Date registrationDate;
        private String website;
        private Integer publicationTypeId;
        private Integer publicationStatusId;
        private Integer publicationThemeId;

        public Builder setId(int id) {
            this.id = id;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
            return this;
        }

        public Builder setIssnNumber(int issnNumber) {
            this.issnNumber = issnNumber;
            return this;
        }

        public Builder setRegistrationDate(Date registrationDate) {
            this.registrationDate = registrationDate;
            return this;
        }

        public Builder setWebsite(String website) {
            this.website = website;
            return this;
        }

        public Builder setPublicationTypeId(Integer publicationTypeId) {
            this.publicationTypeId = publicationTypeId;
            return this;
        }

        public Builder setPublicationStatusId(Integer publicationStatusId) {
            this.publicationStatusId = publicationStatusId;
            return this;
        }

        public Builder setPublicationThemeId(Integer publicationThemeId) {
            this.publicationThemeId = publicationThemeId;
            return this;
        }

        public Publication build() {
            return new Publication(this);
        }
    }
}
