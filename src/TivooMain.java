import java.io.IOException;


public class TivooMain {
    public static void main(String[] args) throws IOException{
        TivooSystem s = new TivooSystem();
        
        //can merge calendars of different types
        s.loadFile("files/dukebasketball.xml");
        s.loadFile("files/dukecal.xml");

        //can filter by two or more filters
        s.filterByKeyword("duke");
        s.filterByDate("02-08-2011", "02-08-2012");

        s.outputSummaryAndDetailsPages("output/summary.html", "output/details_dir");
        s.outputMonthSummary("output/monthsummary.html", "output/details_dir");
        s.outputWeekSummary("output/weeksummary.html", "output/details_dir");
        s.outputConflictSummary("output/conflictsummary.html", "output/details_dir");

    }
}
     
