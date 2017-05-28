////////////////////////////////////////////////////////////////////////////
//JOURNEYDETAILS CLASS - Contains single function
package assignment1carseek;

import java.util.Calendar;
import java.util.Date;

public class GeneralFunctions {

////////////////////////////////////////////////////////////////////////////
    //check if both dates are equal
    public static boolean checkDatesAreEqual(Date date1, Date date2) {
        Calendar cal1 = Calendar.getInstance();
        Calendar cal2 = Calendar.getInstance();
        cal1.setTime(date1);
        cal2.setTime(date2);
        boolean isSame = cal1.get(Calendar.YEAR) == cal2.get(Calendar.YEAR)
                && cal1.get(Calendar.MONTH) == cal2.get(Calendar.MONTH)
                && cal1.get(Calendar.DAY_OF_YEAR) == cal2.get(Calendar.DAY_OF_YEAR)
                && cal1.get(Calendar.HOUR_OF_DAY) == cal2.get(Calendar.HOUR_OF_DAY)
                && cal1.get(Calendar.MINUTE) == cal2.get(Calendar.MINUTE);

        return isSame;

    }

    
    public void estimatedTime(){
        
        
    }
}
