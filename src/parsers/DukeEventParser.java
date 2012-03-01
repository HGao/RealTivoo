package parsers;

import java.util.*;

import org.jdom.Element;

public class DukeEventParser extends AbstractCalendarParser {

    public DukeEventParser() {

    }

    @Override
    public List<CalendarObject> parseEvents(List<Element> eventList) {
        ArrayList<String> eventNames = parseNames(eventList);
        ArrayList<String> eventStarts = parseStartDates(eventList);
        ArrayList<String> eventEnds = parseEndDates(eventList);
        
        return super.parseEvents(eventNames, eventStarts, eventEnds);
    }
    
    public ArrayList<String> parseNames(List<Element> eventList) {
        return super.parseNames(eventList, "summary");
    }
    
    public ArrayList<String> parseEndDates(List<Element> eventList) {
        return super.parseChildParameters(eventList, "end", "shortdate", "time");
    }

    public ArrayList<String> parseStartDates(List<Element> eventList) {
        return super.parseChildParameters(eventList, "start", "shortdate", "time");
    }
    

}
