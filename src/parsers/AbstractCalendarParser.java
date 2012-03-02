package parsers;

import java.util.*;

import org.jdom.Element;

public abstract class AbstractCalendarParser {

    public abstract List<CalendarObject> parseEvents(List<Element> eventList);

    private HashMap<String, String> MonthToNumMap = new HashMap<String, String>() {
        {
            put("Jan", "01");
            put("Feb", "02");
            put("Mar", "03");
            put("Apr", "04");
            put("May", "05");
            put("Jun", "06");
            put("Jul", "07");
            put("Aug", "08");
            put("Sep", "09");
            put("Oct", "10");
            put("Nov", "11");
            put("Dec", "12");
        }
    };

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

    public ArrayList<String> parseNames(List<Element> eventList,
            String parameter) {
        ArrayList<String> ret = new ArrayList<String>();
        ArrayList<String> rawNameValues = parseParameter(eventList, parameter);

        for (String s : rawNameValues) {
            ret.add(getEventName(s));
        }

        return ret;
    }

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

    public String getEventName(String rawEventName) {
        return rawEventName;
    }

    public String convertMonthNameToNum(String month) {
        return MonthToNumMap.get(month);
    }

    public static abstract class ParserFactory {

        public abstract boolean isThisParserType(String url);

        public abstract String getTypeOfCalendarDetected();

        public abstract AbstractCalendarParser getParser();
    }

    public ArrayList<String> parseDate(List<Element> eventList, String date) {
        ArrayList<String> eventDateList = new ArrayList<String>();
        String[] eventDates = {};
        for (int i = 17; i < eventList.size(); i++) {
            if (eventList.get(i).getChildren().size() != 0) {
                eventDates = ((Element) eventList.get(i).getChildren().get(6))
                        .getText().split("\\s");
                if (eventDates[0].equals("When:")) {
                    eventDateList.add(getWhenDate(eventDates, date));
                } else if (eventDates[0].equals("Recurring")) {
                    eventDateList.add(getRecurringDate(eventDates));
                }
            }
        }

        return eventDateList;
    }

    private String getWhenDate(String[] eventDates, String type) {
        String month = convertMonthNameToNum(eventDates[2]);
        String day = eventDates[3].replace(",", "");
        if (day.length() == 1)
            day = "0" + day;
        String year = eventDates[4];

        String time = "";

        if (type.equals("start")) {
            time = parseTime(eventDates[5]);
        } else if (type.equals("end")) {
            time = parseTime(eventDates[7]);
        }

        return day + "-" + month + "-" + year + " " + time;

    }

    private String parseTime(String theTime) {
        String hour = "";
        String minute = "00";
        String second = "00";

        String[] time = theTime.split(":");

        for (int i = 0; i < time.length; i++) {
            time[i] = time[i].replace(" ", "");
        }

        if (theTime.contains("pm")) {
            if (time.length == 1) {
                hour = time[0].replace("pm", "");

            } else {
                hour = time[0];
                minute = time[1].replace("pm", "");
            }
            if (!hour.equals("12")) {
                hour = Integer.toString(Integer.parseInt(hour) + 12);
            }
        } else if (theTime.contains("am")) {
            if (time.length == 1)
                hour = time[0].replace("am", "");
            else {
                hour = time[0];
                minute = time[1].replace("am", "");
            }
        }

        if (hour.length() == 1)
            hour = "0" + hour;

        return hour + ":" + minute + ":" + second;
    }

    private String getRecurringDate(String[] eventDates) {
        String[] date = eventDates[5].split("-");
        String day = date[2];
        String month = date[1];
        String year = date[0];

        String time = eventDates[6];

        System.out.println(day + "-" + month + "-" + year + " " + time);
        return day + "-" + month + "-" + year + " " + time;
    }

}
