package validate;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

public class EditPublicValidator {
    String pubName;
    int issn;
    Date setDate;
    String[] costs;
    HttpServletRequest request;

    public EditPublicValidator(HttpServletRequest request) {
    }

    private EditPublicValidator(Builder builder) {
        this.pubName = builder.pubName;
        this.issn = builder.issn;
        this.setDate = builder.setDate;
        this.costs = builder.costs;
    }

//    public boolean validate() {
//        String issn = Integer.valueOf(issn).toString().length() != 8
//        if (!pubName.isEmpty() && pubName.length() < 45 && Integer.valueOf(issn) == 0)
//                )
//        return true;
//    }

    public static class Builder {
        String pubName;
        int issn;
        Date setDate;
        String[] costs;

        public Builder setPubName(String pubName) {
            this.pubName = pubName;
            return this;
        }

        public Builder setIssn(int issn) {
            this.issn = issn;
            return this;
        }

        public Builder setSetDate(Date setDate) {
            this.setDate = setDate;
            return this;
        }

        public Builder setCosts(String[] costs) {
            this.costs = costs;
            return this;
        }

        public EditPublicValidator build() {
            return new EditPublicValidator(this);
        }
    }
}
