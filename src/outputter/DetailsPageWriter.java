package outputter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import parsers.CalendarObject;

import com.hp.gagawa.java.elements.*;

public class DetailsPageWriter {
    public void write(BufferedWriter out, String detailsOutputDirectory, CalendarObject co) throws IOException
    {
        Html detailsHTML = new Html();
        Head detailsHead = new Head();
        Body detailsBody = new Body();
               
        Div nameDiv = AbstractHTMLWriter.makeDiv("nameDiv", "Event name: " + co.getName());
        Div startTimeDiv = AbstractHTMLWriter.makeDiv("startTimeDiv", "Event start time: " + co.getStartTime());
        Div endTimeDiv = AbstractHTMLWriter.makeDiv("endTimeDiv", "Event end time: " + co.getEndTime());
        
        detailsBody.appendChild(nameDiv, startTimeDiv, endTimeDiv);
        
        detailsHTML.appendChild(detailsHead, detailsBody);
        
        out = new BufferedWriter(new FileWriter(detailsOutputDirectory+"/"+co.getURLString()+".html"));
        out.write(detailsHTML.write());
        out.close();
        
    }
}
