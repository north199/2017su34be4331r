package suber.backend.member.session;

public class Session {
    private String userId;
    private String email;
    private String password;
    private String AccountType;

    public Session() {
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String newUserId) {
        this.userId = newUserId;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String newEmail) {
        this.email = newEmail;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String newPassword) {
        this.password = newPassword;
    }

    public String getAccountType() {
        return this.AccountType;
    }

    public void setAccountType(String newAccountType) {
        this.AccountType = newAccountType;
    }
}
