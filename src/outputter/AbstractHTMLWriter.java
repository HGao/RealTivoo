package outputter;

import java.io.*;
import java.util.*;

import com.hp.gagawa.java.elements.*;

import parsers.CalendarObject;

public abstract class AbstractHTMLWriter {
    public abstract void write(String summaryOutputFile, 
            String detailsOutputDirectory, 
            List<CalendarObject> myCalendarObjects) throws IOException;

    public static Div makeDiv(String id, String divText)
    {
        Div thisDiv = new Div();
        thisDiv.setId(id).setCSSClass("myclass");
        thisDiv.appendText(divText);
        return thisDiv;
    }
    
    public void addEvent(BufferedWriter out, String detailsOutputDirectory, CalendarObject co, HashMap<String, ArrayList<Div>> eventMap, String eventKey) throws IOException
    {
        Div div = new Div();
        div.setId("eventDiv").setCSSClass("myclass");

        DetailsPageWriter dpw = new DetailsPageWriter();
        dpw.write(out, detailsOutputDirectory, co);
        
        A link = new A();
        link.setHref(detailsOutputDirectory.replace("output/","")+"/"+co.getURLString()+".html").setTarget("_blank");
                        
        link.appendText(co.getName());
        div.appendChild(link); 
        
        if (!eventMap.containsKey(eventKey))
        {
            eventMap.put(eventKey, new ArrayList<Div>());
        }
        eventMap.get(eventKey).add(div);
    }
    
    public void writeHTML(BufferedWriter out, String filename, Html mainHTML, Head summaryPageHeader, Body summaryPageBody) throws IOException{
        mainHTML.appendChild(summaryPageHeader, summaryPageBody);
        out = new BufferedWriter(new FileWriter(filename));
        out.write(mainHTML.write());
        out.close();
    }
    
    public void addDiv(String s, HashMap<String, ArrayList<Div>> eventMap, Body summaryPageBody, String divName, String divText) {
        Div dayDiv = makeDiv(divName, divText);
        for (Div d : eventMap.get(s))
        {
            dayDiv.appendChild(d);
        }
        
        dayDiv.appendChild(new Br());

        summaryPageBody.appendChild(dayDiv);
    }
}
