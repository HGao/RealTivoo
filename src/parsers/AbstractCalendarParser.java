package parsers;

import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

import org.jdom.Element;

public abstract class AbstractCalendarParser {

    public abstract List<CalendarObject> parseEvents(List<Element> eventList);

    public List<CalendarObject> parseEvents(List<String> eventNames,
            List<String> eventStarts, List<String> eventEnds) {
        List<CalendarObject> ret = new ArrayList<CalendarObject>();
        for (int i = 0; i < eventNames.size(); i++) {
            ArrayList<String> details = new ArrayList<String>();
            details.add(eventNames.get(i));
            details.add(eventStarts.get(i));
            details.add(eventEnds.get(i));
            ret.add(new CalendarObject(details));
        }

        return ret;
    }

    public abstract String getEventName(String rawEventName);

    protected String trim(String str) {
        return str.substring(0, str.length() - 1);
    }

    public String normalizeDate(String rawDate) {
        String[] eventParameters = rawDate.split(" ");

        String[] dateParameters = eventParameters[0].split("/");
        String day = dateParameters[1];
        if (day.length() == 1)
            day = "0" + day;
        String month = dateParameters[0];
        if (month.length() == 1)
            month = "0" + month;
        String year = dateParameters[2];
        if (year.length() == 2)
            year = "20" + year;

        String date = day + "-" + month + "-" + year;

        String[] timeParameters = eventParameters[1].split(":");
        String hour = timeParameters[0];
        String minute = timeParameters[1];
        String second = "00";

        String time = "";

        if (eventParameters[2].equals("PM") && !hour.equals("12")) {
            time = Integer.toString(Integer.parseInt(hour) + 12) + ":" + minute
                    + ":" + second;
        } else {
            time = hour + ":" + minute + ":" + second;
        }

        return date + " " + time;
    }

    public ArrayList<String> parseParameter(List<Element> eventList,
            String parameter) {
        ArrayList<String> ret = new ArrayList<String>();
        for (Element e : eventList) {
            String eventValue = e.getChildText(parameter);
            ret.add(eventValue);
        }
        return ret;
    }

    public ArrayList<String> parseTwoParameters(List<Element> eventList,
            String dateParam, String timeParam) {
        ArrayList<String> ret = new ArrayList<String>();
        for (Element e : eventList) {
            String eventValue = normalizeDate(e.getChildText(dateParam) + " "
                    + e.getChildText(timeParam));
            ret.add(eventValue);
        }
        return ret;
    }

    public ArrayList<String> parseChildParameters(List<Element> eventList,
            String parent, String startChild, String endChild) {
        ArrayList<String> ret = new ArrayList<String>();
        for (Element e : eventList) {
            String eventStartTime = normalizeDate(e.getChild(parent)
                    .getChildText(startChild)
                    + " " + e.getChild(parent).getChildText(endChild));
            ret.add(eventStartTime);
        }
        return ret;
    }

}
