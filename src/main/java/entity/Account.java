package entity;

import java.io.Serializable;

public class Account implements Serializable {
    private int id;
    private String login;
    private String password;

    public Account() {
    }

    private Account(AccountBuilder accountBuilder) {
        this.id = accountBuilder.id;
        this.login = accountBuilder.login;
        this.password = accountBuilder.password;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Account account = (Account) o;

        return id == account.id;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Account{");
        sb.append("id=").append(id);
        sb.append(", login='").append(login).append('\'');
        sb.append(", password='").append(password).append('\'');
        sb.append('}');
        return sb.toString();
    }

    public static class AccountBuilder {
        private int id;
        private String login;
        private String password;

        public AccountBuilder setId(int id) {
            this.id = id;
            return this;
        }

        public AccountBuilder setLogin(String login) {
            this.login = login;
            return this;
        }

        public AccountBuilder setPassword(String password) {
            this.password = password;
            return this;
        }

        public Account built() {
            return new Account(this);
        }
    }

}
