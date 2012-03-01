package processors;

import java.util.*;

import parsers.CalendarObject;

public class XMLStartDateSortProcessor extends AbstractProcessor {

    @SuppressWarnings("unchecked")
    public List<CalendarObject> filter(String[] keyword,
            List<CalendarObject> myCalendarObjects) {
        
        Comparator comp = new Comparator() {
            public int compare(Object o1, Object o2) {
                CalendarObject p1 = (CalendarObject) o1;
                CalendarObject p2 = (CalendarObject) o2;
                return p1.getStartTime().compareToIgnoreCase(p2.getStartTime());
            }
        };

        return super.sort(keyword, myCalendarObjects, comp);
    }

    public boolean meetsFilterCriterion(CalendarObject co, String[] filters) {
        return true;
    }
}
