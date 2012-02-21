package parsers;

public class CalendarObject {
    String eventName;
    String eventStartTime;
    String eventEndTime;
    
    public CalendarObject(String name, String start, String end) {
        eventName = name;
        eventStartTime = start;
        eventEndTime = end;
    }
    
    public String getName() {
        return eventName;
    }

    public String getStartTime() {
        return eventStartTime;
    }
    
    public String getStartDay() {
        return eventStartTime.split(" ")[0];
    }
    
    public String getEndTime() {
        return eventEndTime;
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
        String date = eventStartTime.split(" ")[0];
        String[] dateComponents = date.split("-");
        
        ret = ret+dateComponents[0]+dateComponents[1]+dateComponents[2];
        ret = ret.replaceAll("[^a-zA-Z0-9]", "");
        return ret;
    }

}
