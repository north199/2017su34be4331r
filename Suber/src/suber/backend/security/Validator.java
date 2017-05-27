/*
 *  ***********************
 *  *   SUBER - INFS2605  *
 *  * AUTHOR: Andrew Snow *
 *  ***********************
 */
package suber.backend.security;

import java.util.regex.Pattern;

/**
 *
 * @author Andrew
 */
public class Validator {
    public Validator() {
        
    }
    
    /** 
     * Checks if text contains malicious SQL injection stuff
     * @param text String to check
     * @return True if it does
     */
    public static boolean isMaliciousText (String text) {
        Pattern sqli = Pattern.compile("((WHERE|OR)[ ]+[\\(]*[ ]*([\\(]*[0-9]+[\\)]*)[ ]*=[ ]*[\\)]*[ ]*\\3)|AND[ ]+[\\(]*[ ]*([\\(]*1[0-9]+|[2-9][0-9]*[\\)]*)[ ]*[\\(]*[ ]*=[ ]*[\\)]*[ ]*\\4");
        return (sqli.matcher(text).matches());
    }
    
}
