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
    List<CalendarObject> sortedCalendarObjects;
    
    public TivooSystem() {
        parser = new XMLParser();
        processor = new XMLProcessor();
        writer = new HTMLWriter();
        myCalendarObjects = new ArrayList<CalendarObject>();
        sortedCalendarObjects = new ArrayList<CalendarObject>();

    }

    public void loadFile(String filename) {
        myCalendarObjects.addAll(parser.loadFile(filename));
    }

    public void filterByKeyword(String keyword) {
        String[] parameters = { keyword };
        myCalendarObjects = processor.filter("keyword", parameters, myCalendarObjects);
        System.out.println("Only showing events containing '" + keyword + "'!");
    }
    
    public void filterByNoKeyword(String keyword) {
        String[] parameters = { keyword };
        myCalendarObjects = processor.filter("noKeyword", parameters, myCalendarObjects);
        System.out.println("Only showing events not containing '" + keyword + "'!");       
    }
    
    public void filterByAttributeKeyword(String keyword, String attribute) {
        String[] parameters = { keyword, attribute };
        myCalendarObjects = processor.filter("attributeKeyword", parameters, myCalendarObjects);
        System.out.println("Only showing events containing '" + keyword + "'!");         
    }
    
    public void filterByDate(String startDate, String endDate) {
        String[] parameters = { startDate, endDate };
        myCalendarObjects = processor.filter("date", parameters, myCalendarObjects);
        System.out.println("Only showing events between on " + startDate + " and " + endDate + "!");
        //consolePrint(myCalendarObjects);
    }
    
    public void sortByName() {
        String[] parameters = {};
        sortedCalendarObjects = processor.filter("sortName", parameters, myCalendarObjects);
    }
    
    public void sortByStartDate() {
        String[] parameters = {};
        sortedCalendarObjects = processor.filter("sortStart", parameters, myCalendarObjects);      
    }
    
    public void sortByEndDate() {
        String[] parameters = {};
        sortedCalendarObjects = processor.filter("sortEnd", parameters, myCalendarObjects);  
    }

    public void outputSummaryAndDetailsPages(String summaryOutputFile, String detailsOutputDirectory) throws IOException {
        writer.output("summary", summaryOutputFile, detailsOutputDirectory, myCalendarObjects);
    }

    public void outputMonthSummary(String summaryOutputFile, String detailsOutputDirectory) throws IOException {
        writer.output("month", summaryOutputFile, detailsOutputDirectory, myCalendarObjects);
    }

    public void outputWeekSummary(String summaryOutputFile, String detailsOutputDirectory) throws IOException {
        writer.output("week", summaryOutputFile, detailsOutputDirectory, myCalendarObjects);
    }
    
    public void outputConflictSummary(String summaryOutputFile, String detailsOutputDirectory) throws IOException {
        writer.output("conflict", summaryOutputFile, detailsOutputDirectory, myCalendarObjects);
    }

    public void outputSortedSummary(String summaryOutputFile, String detailsOutputDirectory) throws IOException {
        writer.output("sorted", summaryOutputFile, detailsOutputDirectory, myCalendarObjects);
    }
    
    @SuppressWarnings("unused")
    private void consolePrint(List<CalendarObject> eventList){
        for (CalendarObject co : eventList)
        {
            System.out.println("Event called '" + co.getName() + "' occuring on " + co.getStartTime());
        }
    }
    
}
