package parsers;

import java.util.*;

import org.jdom.Element;

public class DukeEventParser extends AbstractCalendarParser {

	public DukeEventParser() {

	}

	@Override
	public List<CalendarObject> parseEvents(List<Element> eventList) {
		ArrayList<String> eventNames = parseNames(eventList);
		ArrayList<String> eventStarts = parseStartDates(eventList);
		ArrayList<String> eventEnds = parseEndDates(eventList);

		return super.parseEvents(eventNames, eventStarts, eventEnds);
	}

	public String getEventName(String rawEventName) {
		return rawEventName;
	}

	public String normalizeDate(String rawDate) {
		return super.normalizeDate(rawDate);
	}

	public ArrayList<String> parseNames(List<Element> eventList) {
		ArrayList<String> ret = new ArrayList<String>();;
		ArrayList<String> rawNameValues = super.parseParameter(eventList, "summary");

		for (String s: rawNameValues)
		{
			ret.add(getEventName(s));
		}

		return ret;
	}

	public ArrayList<String> parseEndDates(List<Element> eventList) {
		return super.parseChildParameters(eventList, "end", "shortdate", "time");
	}

	public ArrayList<String> parseStartDates(List<Element> eventList) {
		return super.parseChildParameters(eventList, "start", "shortdate", "time");
	}


	public static class Factory extends AbstractCalendarParser.ParserFactory {

		public boolean isThisParserType (String url) {
    		return url.toLowerCase().contains("dukecal");
    	}

		public String getTypeOfCalendarDetected() {
			return "Duke Event Calendar";
		}
		
		public AbstractCalendarParser getParser() {
    		return new DukeEventParser();
    	}

	}



}
