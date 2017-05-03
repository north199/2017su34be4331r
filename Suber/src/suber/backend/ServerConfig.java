/*
 *  ***********************
 *  *   SUBER - INFS2605  *
 *  * AUTHOR: Andrew Snow *
 *  ***********************
 */
package suber.backend;

/**
 * This class stores the MySQL DB details for retrieval in connection
 *
 * @author Andrew
 */
public class ServerConfig {

    private String host;
    private String user;
    private String pass;
    private String dbName;

    /**
     * Initialises database variables
     */
    public ServerConfig() {
        this.host = "216.172.184.208";
        this.user = "ab66986_suber";
        this.pass = "(VCm*Bu6dl*3";
        this.dbName = "ab66986_suber";
    }

    /**
     * Getter function for returning host
     *
     * @return Host as string
     */
    public String getHost() {
        return this.host;
    }

    /**
     * Getter function for returning db user
     *
     * @return User as string
     */
    public String getUser() {
        return this.user;
    }

    /**
     * Getter function for returning pass
     *
     * @return Pass as string
     */
    public String getPass() {
        return this.pass;
    }

    /**
     * Getter function for returning database
     *
     * @return Database as string
     */
    public String getDB() {
        return this.dbName;
    }

}
