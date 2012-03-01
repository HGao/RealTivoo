package outputter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import parsers.CalendarObject;

import com.hp.gagawa.java.elements.*;

public class SummaryPageWriter extends AbstractHTMLWriter {
    private HashMap<String, ArrayList<Div>> dayToEventMap;
    private Html mainHTML;
    private Head summaryPageHeader;
    private Body summaryPageBody;
    private BufferedWriter out;

    public void write(String summaryOutputFile,
            String detailsOutputDirectory, List<CalendarObject> myCalendarObjects) throws IOException {
        
        mainHTML = new Html();
        summaryPageHeader = new Head();
        summaryPageBody = new Body();
        dayToEventMap = new HashMap<String, ArrayList<Div>>();
        
        for (CalendarObject co : myCalendarObjects)
        {
            super.addEvent(out, detailsOutputDirectory, co, dayToEventMap, co.getStartDay());
        }

        for (String s : dayToEventMap.keySet())
        {
            super.addDiv(s, dayToEventMap, summaryPageBody, "dayDiv", "Events on the day of " + s);
        }
                
        super.writeHTML(out, summaryOutputFile, mainHTML, summaryPageHeader, summaryPageBody);
    }
}
