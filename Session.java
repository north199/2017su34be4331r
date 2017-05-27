package suber.backend.member.session;


public class Session {
    
    private int user_id;
    private String email;
    private String password;
    private String AccountType;
    
    public Session() {
    }
    
    public int getUserId() {
        return this.user_id;
    }
    
    public void setUserId(int newUserId) {
        this.user_id = newUserId;
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
