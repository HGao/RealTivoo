package processors;

import java.util.*;

import parsers.CalendarObject;

public class XMLNameSortProcessor extends AbstractProcessor {

    @SuppressWarnings("unchecked")
    public List<CalendarObject> filter(String[] keyword,
            List<CalendarObject> myCalendarObjects) {

        List<CalendarObject> ret = new ArrayList<CalendarObject>();
        ret = myCalendarObjects;

        Collections.sort(ret, new Comparator() {

            public int compare(Object o1, Object o2) {
                CalendarObject p1 = (CalendarObject) o1;
                CalendarObject p2 = (CalendarObject) o2;
                return p1.getName().compareToIgnoreCase(p2.getName());
            }

        });
        return ret;
    }

    public boolean meetsFilterCriterion(CalendarObject co, String[] filters) {
        return co.getName().toLowerCase().contains(filters[0].toLowerCase());
    }
}
