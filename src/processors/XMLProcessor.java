package processors;

import java.util.*;

import parsers.CalendarObject;

public class XMLProcessor {
    
    private HashMap<String, AbstractProcessorFactory> processorFactory = new HashMap<String, AbstractProcessorFactory>()
    {
        {
          put("keyword", new XMLKeywordProcessorFactory());
          put("date", new XMLDateProcessorFactory());
        }
    };

    public List<CalendarObject> filter(String type, String[] parameters, List<CalendarObject> myCalendarObjects)
    {
        if (processorFactory.get(type) == null)
        {
            System.out.println("404: Filter not found");
            return null;
        }
        
        return processorFactory.get(type).create().filter(parameters, myCalendarObjects);
    }

}
