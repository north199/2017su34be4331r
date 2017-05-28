package suber.infs2605;

import java.sql.Timestamp;
public class PaymentsAlgorithm {

    /* Cost = fuel cost + 5 (for time to pick up)
Fuel cost = Pull database data on end and start time then mulitply it by 3
    
    Fields are cost, start time, end time, multiplier (constant), fixed fee
     */
    private double cost;
    public final int fuelconstant = 3;
    public final int fixedfee = 5;
    public Timestamp starttime;
    public Timestamp endtime;

    public PaymentsAlgorithm(){
        cost = 0;
        starttime = new Timestamp(0,0,0,0,0,0,0);
        endtime = new Timestamp(0,0,0,0,0,0,0);
        
    }
    
        public PaymentsAlgorithm(Timestamp starttime, Timestamp endtime){
        starttime = this.starttime;
        endtime = this.endtime;
        
    }
    
    public double calculateCost(Timestamp starttime, Timestamp endtime) {
        cost = (this.endtime.getTime() - this.starttime.getTime()) * fuelconstant + fixedfee;
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


    public Timestamp getStarttime() {
        return starttime;
    }

    public void setStarttime(Timestamp starttime) {
        this.starttime = starttime;
    }

    public Timestamp getEndtime() {
        return endtime;
    }

    public void setEndtime(Timestamp endtime) {
        this.endtime = endtime;
    }

    
    
}
