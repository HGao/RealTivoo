package outputter;

import java.io.*;
import java.util.*;

import com.hp.gagawa.java.elements.*;

import parsers.CalendarObject;
import processors.AbstractProcessorFactory;
import processors.XMLDateProcessorFactory;
import processors.XMLKeywordProcessorFactory;

public class HTMLWriter {

    private HashMap<String, AbstractHTMLWriterFactory> writerFactory = new HashMap<String, AbstractHTMLWriterFactory>()
    {
        {
          put("summary", new SummaryPageWriterFactory());
        }
    };
    
    public void output(String outType, String summaryOutputFile,
            String detailsOutputDirectory, List<CalendarObject> myCalendarObjects) throws IOException {
        
        if (writerFactory.get(outType) == null)
        {
            System.out.println("404: Filter not found");
        }
        
        writerFactory.get(outType).create().write(summaryOutputFile, detailsOutputDirectory, myCalendarObjects);
    }
    
}
