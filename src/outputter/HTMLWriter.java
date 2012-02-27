package outputter;

import java.io.*;
import java.util.*;

import com.hp.gagawa.java.elements.*;

import parsers.CalendarObject;

public class HTMLWriter {

    private HashMap<String, AbstractHTMLWriter> writerFactory = new HashMap<String, AbstractHTMLWriter>()
    {
        {
          put("summary", new SummaryPageWriter());
          put("week", new WeekWriter());
          put("month", new MonthWriter());
          put("conflict", new ConflictWriter());

        }
    };
    
    public void output(String outType, String summaryOutputFile,
            String detailsOutputDirectory, List<CalendarObject> myCalendarObjects) throws IOException {
        
        try {
            writerFactory.get(outType).write(summaryOutputFile, detailsOutputDirectory, myCalendarObjects);            
        }
        catch (Exception e){
            e.printStackTrace();
        }
        
        writerFactory.get(outType).write(summaryOutputFile, detailsOutputDirectory, myCalendarObjects);
    }
    
}
