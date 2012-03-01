package processors;

import java.util.List;

import parsers.CalendarObject;

public class XMLKeywordExcludeProcessor extends AbstractProcessor{
     
    public List<CalendarObject> filter(String[] keyword,
            List<CalendarObject> myCalendarObjects) {
        
        return super.filter(keyword, myCalendarObjects);
    }
    
    public boolean meetsFilterCriterion(CalendarObject co, String[] filters) {
        return !co.getName().toLowerCase().contains(filters[0].toLowerCase());
    }
}
