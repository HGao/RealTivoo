package processors;

import java.util.*;

import parsers.CalendarObject;

public class XMLProcessor {
    
    private HashMap<String, AbstractProcessor> processorFactory = new HashMap<String, AbstractProcessor>()
    {
        {
          put("keyword", new XMLKeywordProcessor());
          put("date", new XMLDateProcessor());
          put("noKeyword", new XMLKeywordExcludeProcessor());
          put("attributeKeyword", new XMLAttributeKeywordProcessor());
          put("attributeNoKeyword", new XMLAttributeKeywordExcludeProcessor());      
          put("sortName", new XMLNameSortProcessor());        
          put("sortStart", new XMLStartDateSortProcessor());        
          put("sortEnd", new XMLEndDateSortProcessor());        

         }
    };

    public List<CalendarObject> filter(String type, String[] parameters, List<CalendarObject> myCalendarObjects) 
    {
//        try {
//            processorFactory.get(type).filter(parameters, myCalendarObjects);
//            }
//        catch (Exception e){
//            e.printStackTrace();
//        }
        
        return processorFactory.get(type).filter(parameters, myCalendarObjects);
    }

}
