import java.io.IOException;


public class TivooMain {
    public static void main(String[] args) throws IOException{
        TivooSystem s = new TivooSystem();
        s.loadFile("files/dukecal.xml");
        s.filterByKeyword("duke");
        s.filterByDate("01-08-2010", "31-12-2011");
        s.outputSummaryAndDetailsPages("output/summary.html", "output/details_dir");

    }
}