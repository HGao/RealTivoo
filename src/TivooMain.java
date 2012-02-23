import java.io.IOException;


public class TivooMain {
    public static void main(String[] args) throws IOException{
        TivooSystem s = new TivooSystem();
        s.loadFile("files/dukebasketball.xml");
        s.filterByKeyword("duke");
        s.filterByDate("01-01-2012", "31-12-2012");
        s.outputSummaryAndDetailsPages("output/summary.html", "output/details_dir");

    }
}
