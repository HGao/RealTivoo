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
            Div div = new Div();
            div.setId("eventDiv").setCSSClass("myclass");
    
            DetailsPageWriter dpw = new DetailsPageWriter();
            dpw.write(out, detailsOutputDirectory, co);
            
            A link = new A();
            link.setHref(detailsOutputDirectory.replace("output/","")+"/"+co.getURLString()+".html").setTarget("_blank");
                            
            link.appendText(co.getName());
            div.appendChild(link); 
            
            if (!monthToEventMap.containsKey(co.getStartMonth()))
            {
                monthToEventMap.put(co.getStartMonth(), new ArrayList<Div>());
            }
            monthToEventMap.get(co.getStartMonth()).add(div);

        }

        for (String s : monthToEventMap.keySet())
        {
            Div monthDiv = new Div();
            monthDiv.setId("monthDiv").setCSSClass("myclass");
            monthDiv.appendText("Events in the month of " + s);
            for (Div d : monthToEventMap.get(s))
            {
                monthDiv.appendChild(d);
            }
            
            monthDiv.appendText("\n");

            summaryPageBody.appendChild(monthDiv);

        }
        
        mainHTML.appendChild(summaryPageHeader, summaryPageBody);
        
        out = new BufferedWriter(new FileWriter(summaryOutputFile));
        out.write(mainHTML.write());
        out.close();
    }
}
