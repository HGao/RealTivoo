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

    public ArrayList<String> parseNames(List<Element> eventList) {
        ArrayList<String> ret = new ArrayList<String>();
        ;
        ArrayList<String> rawNameValues = super.parseParameter(eventList,
                "Subject");

        for (String s : rawNameValues) {
            ret.add(getEventName(s));
        }

        return ret;
    }

    public ArrayList<String> parseStartDates(List<Element> eventList) {
        return super.parseTwoParameters(eventList, "StartDate", "StartTime");
    }

    public ArrayList<String> parseEndDates(List<Element> eventList) {
        return super.parseTwoParameters(eventList, "EndDate", "EndTime");
    }

}
