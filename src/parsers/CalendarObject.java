package parsers;

import java.util.ArrayList;
import java.util.HashMap;

import org.joda.time.DateTime;

public class CalendarObject {
    HashMap<String, String> detailMap = new HashMap<String, String>();
    
    ArrayList<String> DETAIL_TYPES = new ArrayList<String>(){
        {
            add("eventName");
            add("eventDescription");
        }
    };
    
    String eventName;
    DateTime eventStartTime;
    DateTime eventEndTime;
    
    public CalendarObject(ArrayList<String> details, DateTime startTime, DateTime endTime) {
        eventName = details.get(0);
        eventStartTime = startTime;
        eventEndTime = endTime;
    }
    
    public String getName() {
        return eventName;
    }

    public String getStartTime() {
        return eventStartTime.getHourOfDay() + ":" 
                + eventStartTime.getMinuteOfHour();
    }
    
    public int getStartDay() {
        return eventStartTime.getDayOfYear();
    }
    
    public String getEndTime() {
        return eventEndTime.getHourOfDay() + ":"
                + eventEndTime.getMinuteOfHour();
    }
    
    public int getEndDay() {
        return eventEndTime.getDayOfYear();
    }

    public String getURLString() {
        String ret = "";
        String[] nameParts = eventName.split(" ");
        if (nameParts.length < 3){
            for (int i = 0; i < nameParts.length; i++)
            {
                ret += nameParts[i];
            }
        }
        else
        {
            for (int i = 0; i < 3; i++)
            {
                ret += nameParts[i];
            }
        }
        String date = Integer.toString(eventStartTime.getDayOfYear());
        String[] dateComponents = date.split("-");
        
        ret = ret+dateComponents[0]+dateComponents[1]+dateComponents[2];
        ret = ret.replaceAll("[^a-zA-Z0-9]", "");
        return ret;
    }

}
