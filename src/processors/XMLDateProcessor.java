package processors;

import java.util.List;

import parsers.CalendarObject;

public class XMLDateProcessor extends AbstractProcessor{
    
    public List<CalendarObject> filter(String[] dates,
            List<CalendarObject> myCalendarObjects) {
        
        return super.filter(dates, myCalendarObjects);

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
