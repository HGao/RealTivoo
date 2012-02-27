package processors;

import java.util.ArrayList;
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
    
    /**
     * Returns boolean indicating whether a given CalendarObject
     * 		satisfies the filter criteria in a string array
     * @param co CalendarObject being compared versus filter criteria
     * @param filterParameters criteria to filter events by
     * @return 
     */
    public abstract boolean meetsFilterCriterion(CalendarObject co, String[] filterParameters);
}
