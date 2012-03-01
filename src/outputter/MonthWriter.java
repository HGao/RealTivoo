package outputter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import parsers.CalendarObject;

import com.hp.gagawa.java.elements.*;

public class MonthWriter extends AbstractHTMLWriter {
    private HashMap<String, ArrayList<Div>> monthToEventMap;
    private Html mainHTML;
    private Head summaryPageHeader;
    private Body summaryPageBody;
    private BufferedWriter out;

    public void write(String summaryOutputFile,
            String detailsOutputDirectory, List<CalendarObject> myCalendarObjects) throws IOException {
        
        mainHTML = new Html();
        summaryPageHeader = new Head();
        summaryPageBody = new Body();
        monthToEventMap = new HashMap<String, ArrayList<Div>>();
        
        for (CalendarObject co : myCalendarObjects)
        {
            super.addEvent(out, detailsOutputDirectory, co, monthToEventMap, co.getStartMonth());
        }

        for (String s : monthToEventMap.keySet())
        {
            super.addDiv(s, monthToEventMap, summaryPageBody, "monthDiv", "Events on the month of " + s);
        }
                
        super.writeHTML(out, summaryOutputFile, mainHTML, summaryPageHeader, summaryPageBody);

    }
}
