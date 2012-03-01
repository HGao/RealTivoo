package processors;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import parsers.CalendarObject;

public abstract class AbstractProcessor {
	
	
	/**
	 * 
	 * @param filterParameters
	 * @param myCalendarObjects
	 * @return list of CalendarObjects that meet filter criteria
	 */
    public List<CalendarObject> filter (String[] filterParameters, List<CalendarObject> myCalendarObjects) {
    	
    	List<CalendarObject> ret = new ArrayList<CalendarObject>();
    	for (CalendarObject co : myCalendarObjects) {
    		if (meetsFilterCriterion(co, filterParameters)) {
    			ret.add(co);
    		}
    	}
    	
    	return ret;
    }
    
    @SuppressWarnings("unchecked")
    public List<CalendarObject> sort(String[] keyword, List<CalendarObject> myCalendarObjects) {

        List<CalendarObject> ret = new ArrayList<CalendarObject>();
        ret = myCalendarObjects;

        Collections.sort(ret, new Comparator() {

            public int compare(Object o1, Object o2) {
                CalendarObject p1 = (CalendarObject) o1;
                CalendarObject p2 = (CalendarObject) o2;
                return p1.getStartTime().compareToIgnoreCase(p2.getStartTime());
            }
            
            public int compareName(CalendarObject p1, CalendarObject p2) {
                return p1.getName().compareToIgnoreCase(p2.getName());

            }
            
            public int compareStartTime(CalendarObject p1, CalendarObject p2) {
                return p1.getStartTime().compareToIgnoreCase(p2.getStartTime());

            }
            
            public int compareEndTime(CalendarObject p1, CalendarObject p2) {
                return p1.getEndTime().compareToIgnoreCase(p2.getEndTime());

            }

        });
        return ret;
    }  
    
    /**
     * Returns boolean indicating whether a given CalendarObject
     * 		satisfies the filter criteria in a string array
     * @param co CalendarObject being compared versus filter criteria
     * @param filterParameters criteria to filter events by
     * @return 
     */
    public abstract boolean meetsFilterCriterion(CalendarObject co, String[] filterParameters);
}
