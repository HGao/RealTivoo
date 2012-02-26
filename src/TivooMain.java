import java.io.IOException;


public class TivooMain {
    public static void main(String[] args) throws IOException{
        TivooSystem s = new TivooSystem();
        s.loadFile("files/dukecal.xml");
        s.filterByKeyword("");
        //s.filterByDate("01-01-2012", "15-02-2012");
        s.outputSummaryAndDetailsPages("output/summary.html", "output/details_dir");

    }
}
