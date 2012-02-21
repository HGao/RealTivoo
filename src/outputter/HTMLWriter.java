package outputter;

import java.io.*;
import java.util.*;

import com.hp.gagawa.java.elements.*;

import parsers.CalendarObject;

public class HTMLWriter {
    
    private HashMap<String, ArrayList<Div>> dayToEventMap;
    private Body summaryPageBody;
    private BufferedWriter out;

    public void outputSummaryAndDetailsPages(String summaryOutputFile,
            String detailsOutputDirectory, List<CalendarObject> myCalendarObjects) throws IOException {
        
        summaryPageBody = new Body();
        dayToEventMap = new HashMap<String, ArrayList<Div>>();
        
        for (CalendarObject co : myCalendarObjects)
        {
            Div div = new Div();
            div.setId("eventDiv").setCSSClass("myclass");
    
            createDetailsPage(detailsOutputDirectory, co);
            
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
            Div dayDiv = new Div();
            dayDiv.setId("dayDiv").setCSSClass("myclass");
            dayDiv.appendText(s);
            for (Div d : dayToEventMap.get(s))
            {
                dayDiv.appendChild(d);
            }
            
            dayDiv.appendText("\n");

            summaryPageBody.appendChild(dayDiv);

        }
        out = new BufferedWriter(new FileWriter(summaryOutputFile));
        out.write(summaryPageBody.write());
        out.close();
    }
    
    public void createDetailsPage(String detailsOutputDirectory, CalendarObject co) throws IOException
    {
        Body detailsBody = new Body();
        
        Div nameDiv = new Div();
        nameDiv.setId("nameDiv").setCSSClass("myClass");
        nameDiv.appendText("Event name: " + co.getName());
        
        Div startTimeDiv = new Div();
        startTimeDiv.setId("startTimeDiv").setCSSClass("myClass");
        startTimeDiv.appendText("Event start time: " + co.getStartTime());
        
        Div endTimeDiv = new Div();
        endTimeDiv.setId("endTimeDiv").setCSSClass("myClass");
        endTimeDiv.appendText("Event end time: " + co.getEndTime());
        
        detailsBody.appendChild(nameDiv, startTimeDiv, endTimeDiv);
        
        out = new BufferedWriter(new FileWriter(detailsOutputDirectory+"/"+co.getURLString()+".html"));
        out.write(detailsBody.write());
        out.close();
        
    }

}
