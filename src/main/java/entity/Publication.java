package entity;

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
    private Integer publicationTemeId;

    public Publication() {
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

    public Integer getPublicationTemeId() {
        return publicationTemeId;
    }

    public void setPublicationTemeId(Integer publicationTemeId) {
        this.publicationTemeId = publicationTemeId;
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
}
