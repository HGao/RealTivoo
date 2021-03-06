package parsers;

import java.util.*;

import org.jdom.Element;

public class NFLParser extends AbstractCalendarParser {


	public NFLParser() {

	}

	@Override
	public ArrayList<CalendarObject> parseEvents(List<Element> eventList) {
		ArrayList<CalendarObject> ret = new ArrayList<CalendarObject>();
		for (Element e : eventList) {
			String eventName = getEventName(e.getChildText("Col1"));
			String eventStartTime = normalizeDate(e.getChildText("Col8"));
			String eventEndTime = normalizeDate(e.getChildText("Col9"));

			ArrayList<String> details = new ArrayList<String>();
			details.add(eventName);
			details.add(eventStartTime);
			details.add(eventEndTime);
			CalendarObject newEvent = new CalendarObject(details);
			ret.add(newEvent);
		}
		return ret;
	}

	public String getEventName(String rawEventName) {
		String team1 = rawEventName.split("[ ]+")[3];
		String team2 = rawEventName.split("[ ]+")[5];

		return team1 + " @ " + team2;
	}

	@Override
	public String normalizeDate(String rawDate) {
		String[] eventParameters = rawDate.split(" ");

		String[] dateParameters = eventParameters[0].split("-");
		String day = dateParameters[2];
		String month = dateParameters[1];
		String year = dateParameters[0];

		String date = day + "-" + month + "-" + year;

		return date + " " + eventParameters[1];
	}


	public static class Factory extends AbstractCalendarParser.ParserFactory {

		public boolean isThisParserType (String url) {
			return url.toLowerCase().contains("nfl");
		}

		public String getTypeOfCalendarDetected() {
			return "NFL Parser";
		}

		public AbstractCalendarParser getParser() {
			return new NFLParser();
		}
	}


}
