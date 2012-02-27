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
            Div div = new Div();
            div.setId("eventDiv").setCSSClass("myclass");
    
            DetailsPageWriter dpw = new DetailsPageWriter();
            dpw.write(out, detailsOutputDirectory, co);
            
            A link = new A();
            link.setHref(detailsOutputDirectory.replace("output/","")+"/"+co.getURLString()+".html").setTarget("_blank");
                            
            link.appendText(co.getName());
            div.appendChild(link); 
            
            if (!weekToEventMap.containsKey(co.getStartWeek()))
            {
                weekToEventMap.put(co.getStartWeek(), new ArrayList<Div>());
            }
            weekToEventMap.get(co.getStartWeek()).add(div);

        }

        for (String s : weekToEventMap.keySet())
        {
            Div weekDiv = new Div();
            weekDiv.setId("weekDiv").setCSSClass("myclass");
            weekDiv.appendText("Events on the week of " + s);
            for (Div d : weekToEventMap.get(s))
            {
                weekDiv.appendChild(d);
            }
            
            weekDiv.appendText("\n");

            summaryPageBody.appendChild(weekDiv);

        }
        
        mainHTML.appendChild(summaryPageHeader, summaryPageBody);
        
        out = new BufferedWriter(new FileWriter(summaryOutputFile));
        out.write(mainHTML.write());
        out.close();
    }
}
