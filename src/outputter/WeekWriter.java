package outputter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import parsers.CalendarObject;

import com.hp.gagawa.java.elements.*;

public class WeekWriter extends AbstractHTMLWriter {
    private HashMap<String, ArrayList<Div>> weekToEventMap;
    private Html mainHTML;
    private Head summaryPageHeader;
    private Body summaryPageBody;
    private BufferedWriter out;

    public void write(String summaryOutputFile,
            String detailsOutputDirectory, List<CalendarObject> myCalendarObjects) throws IOException {
        
        mainHTML = new Html();
        summaryPageHeader = new Head();
        summaryPageBody = new Body();
        weekToEventMap = new HashMap<String, ArrayList<Div>>();
        
        for (CalendarObject co : myCalendarObjects)
        {
            super.addEvent(out, detailsOutputDirectory, co, weekToEventMap, co.getStartWeek());
        }

        for (String s : weekToEventMap.keySet())
        {   
            super.addDiv(s, weekToEventMap, summaryPageBody, "weekDiv", "Events on the week of " + s);
        }
                
        super.writeHTML(out, summaryOutputFile, mainHTML, summaryPageHeader, summaryPageBody);

    }
}
