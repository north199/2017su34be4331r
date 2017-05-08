/*
 *  ***********************
 *  *   SUBER - INFS2605  *
 *  * AUTHOR: Andrew Snow *
 *  ***********************
 */
package suber.backend;

import suber.backend.security.Crypto;

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
    public ServerConfig() throws Exception {
        this.host = Crypto.decryptString("QFILuExmvOO8bvKO16w/1w==");
        this.user = Crypto.decryptString("4A+VTJZbIwzLIc0US1j+qg==");
        this.pass = Crypto.decryptString("5Lc+OsDVgxo1hrLgQZsEew==");
        this.dbName = Crypto.decryptString("4A+VTJZbIwzLIc0US1j+qg==");
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
