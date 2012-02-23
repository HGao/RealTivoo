package outputter;

import java.io.IOException;
import java.util.List;

import parsers.CalendarObject;

public abstract class AbstractHTMLWriter {
    public abstract void write(String summaryOutputFile,String detailsOutputDirectory, List<CalendarObject> myCalendarObjects) throws IOException;
}
