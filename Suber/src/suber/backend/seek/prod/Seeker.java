/*
 *  ***********************
 *  *   SUBER - INFS2605  *
 *  * AUTHOR: Andrew Snow *
 *  ***********************
 */
package suber.backend.seek.prod;

/**
 *
 * @author Andrew
 */
public class Seeker {
    /** public void insertCarSeek(int riderId, String sqlDateSeeking, String startTime, int numberOfPeople, int startPostCode, int endPostCode) {
        //  (carOfferID, car.carID, car.userID, jDetails.getTravelTime(), String status, jDetails.getStartingPostCode(), jDetails.getDestinationPostCode(), jDetails.getQuota())
        System.out.println("Start insertCarSeek");

        Connection newConnection = getConnection();
        PreparedStatement insertStatement = null;
        ResultSet newResultSet = null;

        try {
            insertStatement = newConnection.prepareStatement("INSERT INTO ab66986_suber.trip_seeking values(0,?,?,?,?,?,?)");
            //insertStatement.setInt(1, null);
            //insertStatement.setInt(1, carSeekID);
            insertStatement.setInt(1, riderId);
            insertStatement.setString(2, sqlDateSeeking);
            insertStatement.setString(3, startTime);
            //insertStatement.setTimestamp(4, endTime);
            insertStatement.setDouble(4, numberOfPeople);
            insertStatement.setDouble(5, startPostCode);
            insertStatement.setDouble(6, endPostCode);

            // insertStatement.
            insertStatement.executeUpdate();
            System.out.println("Finish insertCarSeek");

        } catch (Exception e) {
        }
        closeConnection(newConnection, newResultSet, insertStatement);

    } **/
    
    public Seeker() {
        
    }

}
