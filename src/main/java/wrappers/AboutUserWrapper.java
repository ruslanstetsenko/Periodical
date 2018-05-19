package wrappers;

import beans.*;

public class AboutUserWrapper {
    private Account account;
    private PassportIdentNumber passportIdentNumber;
    private LivingAddress livingAddress;
    private ContactInfo contactInfo;
    private User user;

    public AboutUserWrapper(Account account, PassportIdentNumber passportIdentNumber, LivingAddress livingAddress, ContactInfo contactInfo, User user) {
        this.account = account;
        this.passportIdentNumber = passportIdentNumber;
        this.livingAddress = livingAddress;
        this.contactInfo = contactInfo;
        this.user = user;
    }

    public Account getAccount() {
        return account;
    }

    public PassportIdentNumber getPassportIdentNumber() {
        return passportIdentNumber;
    }

    public LivingAddress getLivingAddress() {
        return livingAddress;
    }

    public ContactInfo getContactInfo() {
        return contactInfo;
    }

    public User getUser() {
        return user;
    }
}
