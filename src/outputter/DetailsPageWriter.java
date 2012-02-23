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
        
        detailsHTML.appendChild(detailsHead, detailsBody);
        
        out = new BufferedWriter(new FileWriter(detailsOutputDirectory+"/"+co.getURLString()+".html"));
        out.write(detailsHTML.write());
        out.close();
        
    }
}
