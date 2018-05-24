package wrappers;

import beans.*;

import java.util.List;

public class EditPublicationWrapper {
    private Publication publication;
    private List<PublicationType> publicationTypeList;
    private List<PublicationTheme> publicationThemeList;
    private List<PublicationStatus> publicationStatusList;
    private List<PublicationPeriodicyCost> publicationPeriodicyCostList;

    public EditPublicationWrapper(Publication publication, List<PublicationType> publicationTypeList, List<PublicationTheme> publicationThemeList, List<PublicationStatus> publicationStatusList, List<PublicationPeriodicyCost> publicationPeriodicyCostList) {
        this.publication = publication;
        this.publicationTypeList = publicationTypeList;
        this.publicationThemeList = publicationThemeList;
        this.publicationStatusList = publicationStatusList;
        this.publicationPeriodicyCostList = publicationPeriodicyCostList;
    }

    public Publication getPublication() {
        return publication;
    }

    public List<PublicationType> getPublicationTypeList() {
        return publicationTypeList;
    }

    public List<PublicationTheme> getPublicationThemeList() {
        return publicationThemeList;
    }

    public List<PublicationStatus> getPublicationStatusList() {
        return publicationStatusList;
    }

    public List<PublicationPeriodicyCost> getPublicationPeriodicyCostList() {
        return publicationPeriodicyCostList;
    }
}
