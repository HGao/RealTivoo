package processors;

import java.util.ArrayList;
import java.util.List;

import parsers.CalendarObject;

public class XMLKeywordExcludeProcessor extends AbstractProcessor{
     
    public List<CalendarObject> filter(String[] keyword,
            List<CalendarObject> myCalendarObjects) {
        
        List<CalendarObject> ret = new ArrayList<CalendarObject>();
        for (CalendarObject co : myCalendarObjects) {
            if (co.getName().toLowerCase().contains(keyword[0].toLowerCase())) {
                ret.add(co);
            }
        }
        return ret;
    }
    
    public boolean meetsFilterCriterion(CalendarObject co, String[] filters) {
        return co.getName().toLowerCase().contains(filters[0].toLowerCase());
    }
}
