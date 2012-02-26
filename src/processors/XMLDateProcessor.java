package processors;

import java.util.ArrayList;
import java.util.List;

import parsers.CalendarObject;

public class XMLDateProcessor extends AbstractProcessor{
    
    public List<CalendarObject> filter(String[] dates,
            List<CalendarObject> myCalendarObjects) {

        String startDate = dates[0];
        String endDate = dates[1];
        
        int startDateInt = getDateInt(startDate);
        int endDateInt = getDateInt(endDate);

        List<CalendarObject> ret = new ArrayList<CalendarObject>();
        for (CalendarObject co : myCalendarObjects) {

            int coStartDateInt = getDateInt(co.getStartTime());
            int coEndDateInt = getDateInt(co.getEndTime());

            if (coStartDateInt >= startDateInt && coEndDateInt <= endDateInt) {
                ret.add(co);
            }

        }
        return ret;
    }

    
    /**
     * Return integer representation of a date given in string format
     * 		with eight digits representing YYYYMMDD 
     * @param date 
     * @return integer form of date
     */
    public int getDateInt(String date) {
        return Integer.parseInt(date.substring(6, 10) + date.substring(3, 5)
                + date.substring(0, 2));
    }
    
    /**
     * 
     * @param co
     * @return 
     */
    public boolean meetsFilterCriterion (CalendarObject co, String[] filters) {
    	int filterStartDate = getDateInt(filters[0]);
    	int filterEndDate = getDateInt(filters[1]);
    	
    	int eventStartDate = getDateInt(co.getStartTime());
    	int eventEndDate = getDateInt(co.getEndTime());
    	
    	return eventStartDate >= filterStartDate && eventEndDate <= filterEndDate;
    }
}
