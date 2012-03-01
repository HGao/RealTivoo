package outputter;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import parsers.CalendarObject;

import com.hp.gagawa.java.elements.*;

public class ConflictWriter extends AbstractHTMLWriter {
    private HashMap<String, ArrayList<Div>> dayToEventMap;
    private Html mainHTML;
    private Head summaryPageHeader;
    private Body summaryPageBody;
    private BufferedWriter out;

    public void write(String summaryOutputFile, String detailsOutputDirectory,
            List<CalendarObject> myCalendarObjects) throws IOException {

        mainHTML = new Html();
        summaryPageHeader = new Head();
        summaryPageBody = new Body();
        dayToEventMap = new HashMap<String, ArrayList<Div>>();

        for (CalendarObject co : myCalendarObjects) {
            Div div = new Div();
            div.setId("eventDiv").setCSSClass("myclass");
            div.setAttribute("startTime", co.getStartHour());
            div.setAttribute("endTime", co.getEndHour());
            div.setAttribute("name", co.getName());

            DetailsPageWriter dpw = new DetailsPageWriter();
            dpw.write(out, detailsOutputDirectory, co);

            A link = new A();
            link.setHref(
                    detailsOutputDirectory.replace("output/", "") + "/"
                            + co.getURLString() + ".html").setTarget("_blank");

            link.appendText(co.getName());
            div.appendChild(link);

            if (!dayToEventMap.containsKey(co.getStartDay())) {
                dayToEventMap.put(co.getStartDay(), new ArrayList<Div>());
            }
            dayToEventMap.get(co.getStartDay()).add(div);

        }

        for (String s : dayToEventMap.keySet()) {
            Div dayDiv = super.makeDiv("dayDiv", "Events on the day of " + s);
            boolean hasConflict = false;
            for (int i = 0; i < dayToEventMap.get(s).size() - 1; i++)
            {
                for (int j = i + 1; j < dayToEventMap.get(s).size(); j++) {
                    Div event1 = dayToEventMap.get(s).get(i);
                    Div event2 = dayToEventMap.get(s).get(j);
                    if (conflict(event1, event2)) {
                        hasConflict = true;
                        dayDiv.appendChild(new Div());
                        dayDiv.appendText("\"" + event1.getAttribute("name") + "\" conflicts with \"" + event2.getAttribute("name") + "\"");
                    }
                }
            }

            if (hasConflict) {
                summaryPageBody.appendChild(dayDiv);
                summaryPageBody.appendText("</br>");

            }

        }

        mainHTML.appendChild(summaryPageHeader, summaryPageBody);

        out = new BufferedWriter(new FileWriter(summaryOutputFile));
        out.write(mainHTML.write());
        out.close();
    }

    private boolean conflict(Div event1, Div event2) {
        return (convertToInt(event1.getAttribute("startTime")) <= convertToInt(event2.getAttribute("startTime")) && 
                convertToInt(event1.getAttribute("endTime")) >= convertToInt(event2.getAttribute("startTime")))
                || (convertToInt(event1.getAttribute("startTime")) >= convertToInt(event2.getAttribute("startTime")) && 
                convertToInt(event1.getAttribute("endTime")) >= convertToInt(event2.getAttribute("endTime")));

    }

    private int convertToInt(String date) {
        return Integer.parseInt(date.replace(":", ""));
    }
}
