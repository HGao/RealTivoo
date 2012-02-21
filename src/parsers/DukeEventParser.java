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

    public String getEventName(String rawEventName) {
        return rawEventName;
    }

    public String normalizeDate(String rawDate) {
        return super.normalizeDate(rawDate);
    }
    
    @Override
    public ArrayList<String> parseEndDates(List<Element> eventList) {
        ArrayList<String> ret = new ArrayList<String>();
        for (Element e : eventList) {
            String eventEndTime = normalizeDate(e.getChild("end").getChildText(
                    "shortdate")
                    + " " + e.getChild("end").getChildText("time"));
            ret.add(eventEndTime);
        }
        return ret;
    }

    @Override
    public ArrayList<String> parseNames(List<Element> eventList) {
        ArrayList<String> ret = new ArrayList<String>();
        for (Element e : eventList) {
            String eventName = getEventName(e.getChildText("summary"));
            ret.add(eventName);
        }
        return ret;
    }

    @Override
    public ArrayList<String> parseStartDates(List<Element> eventList) {
        ArrayList<String> ret = new ArrayList<String>();
        for (Element e : eventList) {
            String eventStartTime = normalizeDate(e.getChild("start")
                    .getChildText("shortdate")
                    + " " + e.getChild("start").getChildText("time"));
            ret.add(eventStartTime);
        }
        return ret;
    }

}
