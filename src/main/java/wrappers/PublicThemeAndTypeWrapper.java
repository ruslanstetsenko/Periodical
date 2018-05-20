package wrappers;

import beans.PublicationTheme;
import beans.PublicationType;

import java.util.List;

public class PublicThemeAndTypeWrapper {
    private List<PublicationTheme> publicationThemes;
    private List<PublicationType> publicationTypes;

    public PublicThemeAndTypeWrapper(List<PublicationTheme> publicationThemes, List<PublicationType> publicationTypes) {
        this.publicationThemes = publicationThemes;
        this.publicationTypes = publicationTypes;
    }

    public List<PublicationTheme> getPublicationThemes() {
        return publicationThemes;
    }

    public List<PublicationType> getPublicationTypes() {
        return publicationTypes;
    }
}
