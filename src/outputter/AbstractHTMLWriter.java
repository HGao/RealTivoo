package outputter;

import java.io.IOException;
import java.util.List;

import com.hp.gagawa.java.elements.Div;

import parsers.CalendarObject;

public abstract class AbstractHTMLWriter {
    public abstract void write(String summaryOutputFile,String detailsOutputDirectory, List<CalendarObject> myCalendarObjects) throws IOException;

    public Div makeDiv(String id, String divText)
    {
        Div thisDiv = new Div();
        thisDiv.setId(id).setCSSClass("myclass");
        thisDiv.appendText(divText);
        return thisDiv;
    }
}
