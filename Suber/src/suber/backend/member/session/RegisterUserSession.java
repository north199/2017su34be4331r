package suber.backend.member.session;

import java.util.Date;
import suber.backend.security.Crypto;

/**
 *
 * @author Harry
 */
public class RegisterUserSession {

    private int user_id;
    private String fName;
    private String lName;
    private String phoneNumber;
    private String email;
    private String password;

    private String gender;
    private Date dob;
    private String accountType;
    private boolean isCorporate;
    private int companyCode;
    
    private int homeNumber;
    private String homeStreet;
    private String homeSuburb;
    private int homePostcode;
    private int workNumber;
    private String workStreet;
    private String workSuburb;
    private int workPostcode;

    public String getPassword() {
        return this.password;
    }
    
    public void setPassword(String password) throws Exception {
        this.password = Crypto.encryptString(password);
    }
    
    public int getUserId() {
        return this.user_id;
    }

    public void setUserId(int id) {
        this.user_id = id;
    }

    public String fFame() {
        return this.fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public String getLName() {
        return this.lName;
    }

    public void setLName(String lName) {
        this.lName = lName;
    }

    public String getPhoneNumber() {
        return this.phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getDob() {
        return this.dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getAccountType() {
        return this.accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }
    
    public boolean getIsCorporate() {
        return this.isCorporate;
    }
    
    private void setIsCorporate(boolean isCorporate) {
        this.isCorporate = isCorporate;
    }

    public int getCompanyCode() {
        return this.companyCode;
    }

    public void setCompanyCode(int companyCode) {
        this.companyCode = companyCode;
    }
    
    public int getHomeNumber() {
        return this.homeNumber;
    }
    
    public void setHomeNumber(int homeNumber) {
        this.homeNumber = homeNumber;
    }
    
    public String getHomeStreet() {
        return this.homeStreet;
    }
    
    public void setHomeStreet(String homeStreet) {
        this.homeStreet = homeStreet;
    }
    
    public String getHomeSuburb() {
        return this.homeSuburb;
    }
    
    public void setHomeSuburb(String homeSuburb) {
        this.homeSuburb = homeSuburb;
    }
    
    public int getHomePostcode() {
        return this.homePostcode;
    }
    
    public void setHomePostcode(int homePostcode) {
        this.homePostcode = homePostcode;
    }
    
    public int getWorkNumber() {
        return this.workNumber;
    }
    
    public void setWorkNumber(int workNumber) {
        this.workNumber = workNumber;
    }
    
    public String getWorkStreet() {
        return this.workStreet;
    }
    
    public void setWorkStreet(String workStreet) {
        this.workStreet = workStreet;
    }
    
    public String getWorkSuburb() {
        return this.workSuburb;
    }
    
    public void setWorkSuburb(String workSuburb) {
        this.workSuburb = workSuburb;
    }
    
    public int getWorkPostcode() {
        return this.workPostcode;
    }
    
    public void setWorkPostcode(int workPostcode) {
        this.workPostcode = workPostcode;
    }

}
