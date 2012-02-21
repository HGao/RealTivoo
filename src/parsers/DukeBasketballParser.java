package parsers;

import java.util.*;

import org.jdom.Element;

public class DukeBasketballParser extends AbstractCalendarParser {

    public DukeBasketballParser() {

    }

    @Override
    public List<CalendarObject> parseEvents(List<Element> eventList) {
        ArrayList<String> eventNames = parseNames(eventList);
        ArrayList<String> eventStarts = parseStartDates(eventList);
        ArrayList<String> eventEnds = parseEndDates(eventList);
        
        return super.parseEvents(eventNames, eventStarts, eventEnds);
    }

    public String getEventName(String rawEventName) {
        String[] rawEventData = rawEventName.split(" ");
        String team1 = "";
        String team2 = "";

        boolean firstTeam = true;
        for (int i = 0; i < rawEventData.length; i++) {
            if (rawEventData[i].equals("vs"))
                firstTeam = false;
            else if (firstTeam)
                team1 += rawEventData[i] + " ";
            else {
                if (rawEventData[i].contains("("))
                    break;
                team2 += rawEventData[i] + " ";
            }
        }

        return trim(team1) + " @ " + trim(team2);
    }

    public String normalizeDate(String rawDate) {
        return super.normalizeDate(rawDate);
    }

    @Override
    public ArrayList<String> parseEndDates(List<Element> eventList) {
        ArrayList<String> ret = new ArrayList<String>();
        for (Element e : eventList) {
            String eventEndTime = normalizeDate(e.getChildText("EndDate") + " "
                    + e.getChildText("EndTime"));
            ret.add(eventEndTime);
        }
        return ret;
    }

    @Override
    public ArrayList<String> parseNames(List<Element> eventList) {
        ArrayList<String> ret = new ArrayList<String>();
        for (Element e : eventList) {
            String eventName = getEventName(e.getChildText("Subject"));
            ret.add(eventName);
        }
        return ret;
    }

    @Override
    public ArrayList<String> parseStartDates(List<Element> eventList) {
        ArrayList<String> ret = new ArrayList<String>();
        for (Element e : eventList) {
            String eventStartTime = normalizeDate(e.getChildText("StartDate")
                    + " " + e.getChildText("StartTime"));
            ret.add(eventStartTime);
        }
        return ret;
    }

}
