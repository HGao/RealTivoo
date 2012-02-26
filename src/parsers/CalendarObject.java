package parsers;

import java.util.ArrayList;
import java.util.HashMap;

public class CalendarObject {
    HashMap<String, String> detailMap = new HashMap<String, String>();
    
    ArrayList<String> DETAIL_TYPES = new ArrayList<String>(){
        {
            add("eventName");
            add("eventStartTime");
            add("eventEndTime");
        }
    };
    
    public CalendarObject(ArrayList<String> details) {
        for (int i = 0; i < details.size(); i++)
        {
            detailMap.put(DETAIL_TYPES.get(i), details.get(i));
        }
    }
    
    public String getName() {
        return detailMap.get("eventName");
    }

    public String getStartTime() {
        return detailMap.get("eventStartTime");
    }
    
    public String getStartDay() {
        return detailMap.get("eventStartTime").split(" ")[0];
    }
    
    public String getEndTime() {
        return detailMap.get("eventEndTime");
    }

    public String getURLString() {
        String ret = "";
        String[] nameParts = detailMap.get("eventName").split(" ");
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
        String date = detailMap.get("eventStartTime").split(" ")[0];
        String[] dateComponents = date.split("-");
        
        ret = ret+dateComponents[0]+dateComponents[1]+dateComponents[2];
        ret = ret.replaceAll("[^a-zA-Z0-9]", "");
        return ret;
    }

}
