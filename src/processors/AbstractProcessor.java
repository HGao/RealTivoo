package processors;

import java.util.List;

import parsers.CalendarObject;

public abstract class AbstractProcessor {
    public abstract List<CalendarObject> filter(String[] parameters, List<CalendarObject> myCalendarObjects);
}
