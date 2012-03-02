package processors;

import java.util.List;

import parsers.CalendarObject;

public class XMLAttributeKeywordProcessor extends AbstractProcessor {
    
    public boolean meetsFilterCriterion(CalendarObject co, String[] filters) {
        return co.getName().toLowerCase().contains(filters[0].toLowerCase());
    }
}