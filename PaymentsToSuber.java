/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package suber.infs2605;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author michaelliang
 */
public class PaymentsToSuber {

    public int monthlyfee = 10;
    public int membershipfee = 5;
    public int[] fees = new int[100];
    private boolean refund;//true until two weeks
    private String datepaid;
    public Date currentdate;
    public Date formatteddatepaid;
    
    //Crate no arg object
    public PaymentsToSuber(){
        refund = true;
        currentdate = new Date();
    }
    
     //Log a transactions
    public void logFee(int payment){
      
        for (int i = 0; i<fees.length;i++){
            if (fees[i]==0){
                fees[i] = payment;
                break;
            }
            else{
                continue;
            }
        }
    }
    

    //If two weeks has passed, log refund as false. You can deconstruct the string into different fields
    public void refundExpiry() throws ParseException{
        formatteddatepaid = new SimpleDateFormat("dd/MM/yyyy").parse(datepaid);
        
        double diffTime = currentdate.getTime() - formatteddatepaid.getTime();
        
        int millisecondsintwoweeks = 14*24*60*60*1000;
        
        if (diffTime>millisecondsintwoweeks){
            refund = false;
        }
        else{
        }
    }
    public int getMonthlyfee() {
        return monthlyfee;
    }

    public void setMonthlyfee(int monthlyfee) {
        this.monthlyfee = monthlyfee;
    }

    public int getMembershipfee() {
        return membershipfee;
    }

    public void setMembershipfee(int membershipfee) {
        this.membershipfee = membershipfee;
    }

    public int getFees(int index) {
        return fees[index];
    }

    public void setFees(int[] fees) {
        this.fees = fees;
    }

    public boolean isRefund() {
        return refund;
    }

    public void setRefund(boolean refund) {
        this.refund = refund;
    }

    public String getDatepaid() {
        return datepaid;
    }

    public void setDatepaid(String datepaid) {
        this.datepaid = datepaid;
    }
    
    
    
}