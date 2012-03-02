package parsers;

import java.util.*;

import org.jdom.Element;

public class GCalParser extends AbstractCalendarParser {

	public GCalParser() {

	}

	@Override
	public List<CalendarObject> parseEvents(List<Element> eventList) {

		ArrayList<String> eventNames = parseNames(eventList);
		ArrayList<String> eventStarts = parseStartDates(eventList);
		ArrayList<String> eventEnds = parseEndDates(eventList);

		return super.parseEvents(eventNames, eventStarts, eventEnds);
	}

	public ArrayList<String> parseNames(List<Element> eventList) {
		ArrayList<String> eventNameList = new ArrayList<String>();
		for (int i = 17; i < eventList.size(); i++) {
			if (eventList.get(i).getChildren().size() != 0)
				eventNameList.add(((Element) eventList.get(i).getChildren()
						.get(4)).getText());
		}

		return eventNameList;
	}

	public ArrayList<String> parseEndDates(List<Element> eventList) {
		return super.parseDate(eventList, "end");
	}

	public ArrayList<String> parseStartDates(List<Element> eventList) {
		return super.parseDate(eventList, "start");
	}

	public static class Factory extends AbstractCalendarParser.ParserFactory {

		public boolean isThisParserType (String url) {
			return url.toLowerCase().contains("google");
		}

		public String getTypeOfCalendarDetected() {
			return "Duke Basketball Calendar";
		}

		public AbstractCalendarParser getParser() {
			return new GCalParser();
		}

	}

}
