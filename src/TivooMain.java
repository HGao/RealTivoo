import java.io.IOException;


public class TivooMain {
    public static void main(String[] args) throws IOException{
        TivooSystem s = new TivooSystem();
        s.loadFile("files/tv.xml");

        s.filterByKeyword("mad");
        s.filterByDate("02-08-2011", "02-08-2012");

        //s.filterByKeyword("");
        //s.filterByDate("01-01-2012", "15-02-2012");

        s.outputSummaryAndDetailsPages("output/summary.html", "output/details_dir");

    }
}
     
