package suber.infs2605;

import java.sql.Timestamp;

public class PaymentsAlgorithm {

    /* Cost = fuel cost + 5 (for time to pick up)
Fuel cost = Pull database data on end and start time then mulitply it by 3
    
    Fields are cost, start time, end time, multiplier (constant), fixed fee
     */
    private double cost;
    private final int fuelconstant = 3;
    private final int fixedfee = 5;
    private int estimatedtraveltime;
//    public Timestamp starttime;
//    public Timestamp endtime;

    public PaymentsAlgorithm() {
        cost = 0;
        estimatedtraveltime = 0;
//        starttime = new Timestamp(0,0,0,0,0,0,0);
//        endtime = new Timestamp(0,0,0,0,0,0,0);

    }

    public PaymentsAlgorithm(int estimatedtraveltime) {
        estimatedtraveltime = this.estimatedtraveltime;
//        starttime = this.starttime;
//        endtime = this.endtime;

    }

    public double calculateCost(Timestamp starttime, Timestamp endtime) {
        cost = estimatedtraveltime * fuelconstant + fixedfee;
        return cost;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public int getFuelconstant() {
        return fuelconstant;
    }

    public int getFixedfee() {
        return fixedfee;
    }

    public int getEstimatedtraveltime() {
        return estimatedtraveltime;
    }

    public void setEstimatedtraveltime(int estimatedtraveltime) {
        this.estimatedtraveltime = estimatedtraveltime;
    }
}
