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
            Div div = new Div();
            div.setId("eventDiv").setCSSClass("myclass");
    
            DetailsPageWriter dpw = new DetailsPageWriter();
            dpw.write(out, detailsOutputDirectory, co);
            
            A link = new A();
            link.setHref(detailsOutputDirectory.replace("output/","")+"/"+co.getURLString()+".html").setTarget("_blank");
                            
            link.appendText(co.getName());
            div.appendChild(link); 
            
            if (!dayToEventMap.containsKey(co.getStartDay()))
            {
                dayToEventMap.put(co.getStartDay(), new ArrayList<Div>());
            }
            dayToEventMap.get(co.getStartDay()).add(div);

        }

        for (String s : dayToEventMap.keySet())
        {
            Div dayDiv = super.makeDiv("dayDiv", "Events on the day of " + s);
            for (Div d : dayToEventMap.get(s))
            {
                dayDiv.appendChild(d);
            }
            
            dayDiv.appendText("\n");

            summaryPageBody.appendChild(dayDiv);

        }
        
        mainHTML.appendChild(summaryPageHeader, summaryPageBody);
        
        out = new BufferedWriter(new FileWriter(summaryOutputFile));
        out.write(mainHTML.write());
        out.close();
    }
}
