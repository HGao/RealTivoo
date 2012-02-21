package processors;

import java.util.ArrayList;
import java.util.List;

import parsers.CalendarObject;

public class XMLProcessor {

    public List<CalendarObject> filterByKeyword(String keyword, List<CalendarObject> myCalendarObjects)
    {
        List<CalendarObject> ret = new ArrayList<CalendarObject>();
        for (CalendarObject co : myCalendarObjects)
        {
            if (co.getName().toLowerCase().contains(keyword.toLowerCase()))
            {
                ret.add(co);
            }
        }
        return ret;
    }

    public List<CalendarObject> filterByDate(String startDate, String endDate, List<CalendarObject> myCalendarObjects) 
    {
   
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
    
    public int getDateInt(String date) {
        return Integer.parseInt(date.substring(6, 10) + date.substring(3, 5) + date.substring(0, 2));
    }

}
