package processors;

import java.io.IOException;
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
        try {
            processorFactory.get(type).create();
            }
        catch (Exception e){
            e.printStackTrace();
        }
        
        return processorFactory.get(type).create().filter(parameters, myCalendarObjects);
    }

}
