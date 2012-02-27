import java.io.IOException;
import java.util.*;

import outputter.*;
import parsers.*;
import processors.*;


public class TivooSystem {
    XMLParser parser;
    XMLProcessor processor;
    HTMLWriter writer;
    List<CalendarObject> myCalendarObjects;
    
    public TivooSystem() {
        parser = new XMLParser();
        processor = new XMLProcessor();
        writer = new HTMLWriter();
    }

    public void loadFile(String filename) {
        myCalendarObjects = parser.loadFile(filename);
    }

    public void filterByKeyword(String keyword) {
        String[] parameters = { keyword };
        myCalendarObjects = processor.filter("keyword", parameters, myCalendarObjects);
        System.out.println("Only showing events containing '" + keyword + "'!");
    }
    
    public void filterByDate(String startDate, String endDate) {
        String[] parameters = { startDate, endDate };
        myCalendarObjects = processor.filter("date", parameters, myCalendarObjects);
        System.out.println("Only showing events between on " + startDate + " and " + endDate + "!");
        //consolePrint(myCalendarObjects);
    }

    public void outputSummaryAndDetailsPages(String summaryOutputFile, String detailsOutputDirectory) throws IOException {
        writer.output("summary", summaryOutputFile, detailsOutputDirectory, myCalendarObjects);
    }
    
    @SuppressWarnings("unused")
    private void consolePrint(List<CalendarObject> eventList){
        for (CalendarObject co : eventList)
        {
            System.out.println("Event called '" + co.getName() + "' occuring on " + co.getStartTime());
        }
    }
    
}
