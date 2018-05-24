package wrappers;

import beans.*;

import java.util.List;

public class FullPublicationInfoWrapper {

    private Publication publication;
    private PublicationType publicationType;
    private PublicationTheme publicationTheme;
    private PublicationStatus publicationStatus;


    private List<Publication> publicationList;
    private List<SubscriptionBill> subscriptionBillList;
    private List<PublicationType> publicationTypeList;
    private List<PublicationTheme> publicationThemeList;
    private List<PublicationStatus> publicationStatusList;
    private List<PublicationPeriodicyCost> publicationPeriodicyCostList;

    public FullPublicationInfoWrapper() {
    }

    private FullPublicationInfoWrapper(Builder builder) {
        this.publication = builder.publication;
        this.publicationType = builder.publicationType;
        this.publicationTheme = builder.publicationTheme;
        this.publicationStatus = builder.publicationStatus;
        this.publicationList = builder.publicationList;
        this.subscriptionBillList = builder.subscriptionBillList;
        this.publicationTypeList = builder.publicationTypeList;
        this.publicationThemeList = builder.publicationThemeList;
        this.publicationStatusList = builder.publicationStatusList;
        this.publicationPeriodicyCostList = builder.publicationPeriodicyCostList;
    }

    public Publication getPublication() {
        return publication;
    }

    public PublicationType getPublicationType() {
        return publicationType;
    }

    public PublicationTheme getPublicationTheme() {
        return publicationTheme;
    }

    public PublicationStatus getPublicationStatus() {
        return publicationStatus;
    }

    public List<Publication> getPublicationList() {
        return publicationList;
    }

    public List<SubscriptionBill> getSubscriptionBillList() {
        return subscriptionBillList;
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

    public static class Builder {
        private Publication publication;
        private PublicationType publicationType;
        private PublicationTheme publicationTheme;
        private PublicationStatus publicationStatus;


        private List<Publication> publicationList;
        private List<SubscriptionBill> subscriptionBillList;
        private List<PublicationType> publicationTypeList;
        private List<PublicationTheme> publicationThemeList;
        private List<PublicationStatus> publicationStatusList;
        private List<PublicationPeriodicyCost> publicationPeriodicyCostList;

        public Builder setPublication(Publication publication) {
            this.publication = publication;
            return this;
        }

        public Builder setPublicationType(PublicationType publicationType) {
            this.publicationType = publicationType;
            return this;
        }

        public Builder setPublicationTheme(PublicationTheme publicationTheme) {
            this.publicationTheme = publicationTheme;
            return this;
        }

        public Builder setPublicationStatus(PublicationStatus publicationStatus) {
            this.publicationStatus = publicationStatus;
            return this;
        }

        public Builder setPublicationList(List<Publication> publicationList) {
            this.publicationList = publicationList;
            return this;
        }

        public Builder setSubscriptionBillList(List<SubscriptionBill> subscriptionBillList) {
            this.subscriptionBillList = subscriptionBillList;
            return this;
        }

        public Builder setPublicationTypeList(List<PublicationType> publicationTypeList) {
            this.publicationTypeList = publicationTypeList;
            return this;
        }

        public Builder setPublicationThemeList(List<PublicationTheme> publicationThemeList) {
            this.publicationThemeList = publicationThemeList;
            return this;
        }

        public Builder setPublicationStatusList(List<PublicationStatus> publicationStatusList) {
            this.publicationStatusList = publicationStatusList;
            return this;
        }

        public Builder setPublicationPeriodicyCostList(List<PublicationPeriodicyCost> publicationPeriodicyCostList) {
            this.publicationPeriodicyCostList = publicationPeriodicyCostList;
            return this;
        }

        public FullPublicationInfoWrapper build() {
            return new FullPublicationInfoWrapper(this);
        }
    }

}
