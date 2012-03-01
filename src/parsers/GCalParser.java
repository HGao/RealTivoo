package parsers;

import java.util.*;

import org.jdom.Element;

public class GCalParser extends AbstractCalendarParser {

    public GCalParser() {

    }

    @Override
    public List<CalendarObject> parseEvents(List<Element> eventList) {

        ArrayList<String> eventNames = parseNames(eventList);
        ArrayList<String> eventStarts = parseStartDates(eventList);
        ArrayList<String> eventEnds = parseEndDates(eventList);
        
        return super.parseEvents(eventNames, eventStarts, eventEnds);
    }
    
    public ArrayList<String> parseNames(List<Element> eventList) {
        ArrayList<String> eventNameList = new ArrayList<String>();
        for (int i = 17; i < eventList.size(); i++) {
            if (eventList.get(i).getChildren().size() != 0)
                eventNameList.add(((Element) eventList.get(i).getChildren()
                        .get(4)).getText());
        }

        return eventNameList;
    }

    public ArrayList<String> parseEndDates(List<Element> eventList) {
        ArrayList<String> eventEndList = new ArrayList<String>();
        String[] eventDates = {};
        for (int i = 17; i < eventList.size(); i++) {
            if (eventList.get(i).getChildren().size() != 0) {
                eventDates = ((Element) eventList.get(i).getChildren().get(6))
                        .getText().split("\\s");
                if (eventDates[0].equals("When:")) {
                    eventEndList.add(getWhenDate(eventDates, "end"));
                }
                else if (eventDates[0].equals("Recurring")) {
                    eventEndList.add(getRecurringDate(eventDates));
                }
            }
        }
        return eventEndList;
    }

    public ArrayList<String> parseStartDates(List<Element> eventList) {
        ArrayList<String> eventStartList = new ArrayList<String>();
        String[] eventDates = {};
        for (int i = 17; i < eventList.size(); i++) {
            if (eventList.get(i).getChildren().size() != 0) {
                eventDates = ((Element) eventList.get(i).getChildren().get(6))
                        .getText().split("\\s");
                if (eventDates[0].equals("When:")) {
                    eventStartList.add(getWhenDate(eventDates, "start"));
                }
                else if (eventDates[0].equals("Recurring")) {
                    eventStartList.add(getRecurringDate(eventDates));
                }
            }
        }

        return eventStartList;
    }
    
    private String getWhenDate(String[] eventDates, String type)
    {
        String month = super.convertMonthNameToNum(eventDates[2]);
        String day = eventDates[3].replace(",", "");
        if (day.length() == 1) day = "0" + day;
        String year = eventDates[4];
        
        String time = "";
        
        if (type.equals("start"))
        {
            time = parseTime(eventDates[5]);
        }
        else if (type.equals("end"))
        {
            time = parseTime(eventDates[7]);
        }

        return day + "-" + month + "-" + year + " " + time;
        
    }
    

    private String getRecurringDate(String[] eventDates)
    {
        String[] date = eventDates[5].split("-");
        String day = date[2];
        String month = date[1];
        String year = date[0];
        
        String time = eventDates[6];
        
        System.out.println(day + "-" + month + "-" + year + " " + time);
        return day + "-" + month + "-" + year + " " + time;
    }
    
    private String parseTime(String theTime)
    {
        String hour = "";
        String minute = "00";
        String second = "00";
        
        
        String[] time = theTime.split(":");
                
        for (int i = 0; i < time.length; i++){
            time[i] = time[i].replace(" ", "");
        }
        
        if (theTime.contains("pm"))
        {
            if (time.length == 1)
            {
                hour = time[0].replace("pm", "");

            }
            else
            {
                hour = time[0];
                minute = time[1].replace("pm", "");
            }
            if (!hour.equals("12"))
            {
                hour = Integer.toString(Integer.parseInt(hour) + 12);
            }
        }
        else if (theTime.contains("am"))
        {
            if (time.length == 1) hour = time[0].replace("am", "");
            else {
                hour = time[0];
                minute = time[1].replace("am", "");
            }
        }
        
        if (hour.length() == 1) hour = "0" + hour;
        
        return hour + ":" + minute + ":" + second;
    }
}
