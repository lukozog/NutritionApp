package ozog.model;

public class Mail {
    private String mailAddress;

    private String mailText;

    private String mailTopic;

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    public String getMailText() {
        return mailText;
    }

    public void setMailText(String mailText) {
        this.mailText = mailText;
    }

    public String getMailTopic() {
        return mailTopic;
    }

    public void setMailTopic(String mailTopic) {
        this.mailTopic = mailTopic;
    }

    @Override
    public String toString() {
        return "Mail{" +
                "mailAddress='" + mailAddress + '\'' +
                ", mailText='" + mailText + '\'' +
                ", mailTopic='" + mailTopic + '\'' +
                '}';
    }
}
