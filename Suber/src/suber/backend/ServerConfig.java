/*
 *  ***********************
 *  *   SUBER - INFS2605  *
 *  * AUTHOR: Andrew Snow *
 *  ***********************
 */
package suber.backend;

/**
 * This class stores the MySQL DB details for retrieval in connection
 * @author Andrew
 */
public class ServerConfig {
    private String host;
    private String user;
    private String pass;
    private String dbName;
    
    public ServerConfig() {
        this.host = "216.172.184.208";
        this.user = "ab66986_suber";
        this.pass = "(VCm*Bu6dl*3";
        this.dbName = "ab66986_suber";
    }
    
    public String getHost() {
        return this.host;
    }
    
    public String getUser() {
        return this.user;
    }
    
    public String getPass() {
        return this.pass;
    }
    
    public String getDB() {
        return this.dbName;
    }
   
}
