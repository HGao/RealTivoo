package outputter;

import java.io.*;
import java.util.*;
import java.lang.Object;

import parsers.CalendarObject;

import com.hp.gagawa.java.elements.*;

public class SortedDateWriter extends AbstractHTMLWriter {
    private HashMap<String, ArrayList<Div>> dayToEventMap;
    private Html mainHTML;
    private Head summaryPageHeader;
    private Body summaryPageBody;
    private BufferedWriter out;

    @SuppressWarnings("unchecked")
    public void write(String summaryOutputFile, String detailsOutputDirectory,
            List<CalendarObject> myCalendarObjects) throws IOException {

        mainHTML = new Html();
        summaryPageHeader = new Head();
        summaryPageBody = new Body();
        dayToEventMap = new HashMap<String, ArrayList<Div>>();

        
        for (CalendarObject co : myCalendarObjects) {
            Div div = new Div();
            div.setId("eventDiv").setCSSClass("myclass");

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

        List<String> allDivs = new ArrayList<String>(dayToEventMap.keySet());
        Collections.sort(allDivs, new Comparator() {

            public int compare(Object o1, Object o2) {
                return reformat((String) o1).compareToIgnoreCase(reformat((String) o2));
            }

            private String reformat(String time) {
                return time.substring(6,10) + time.substring(3,5) + time.substring(0,2);
            }

        });
        
        
        for (String s : allDivs) {
            Div dayDiv = new Div();
            dayDiv.setId("dayDiv").setCSSClass("myclass");
            dayDiv.appendText("Events on the day of " + s);
            for (Div d : dayToEventMap.get(s)) {
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
