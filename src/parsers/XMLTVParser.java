package parsers;

import java.util.ArrayList;
import java.util.EventObject;
import java.util.List;

import org.jdom.Element;

public class XMLTVParser extends AbstractCalendarParser {

	public XMLTVParser() {

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
		for (int i = 0; i < eventList.size(); i++) {
			if (eventList.get(i).getChildren("title").size() != 0)
				eventNameList.add(eventList.get(i).getChildText("title"));
		}

		return eventNameList;
	}

	public ArrayList<String> parseEndDates(List<Element> eventList) {
		ArrayList<String> eventEndList = new ArrayList<String>();
		for (int i = 0; i < eventList.size(); i++) {
			if (eventList.get(i).getChildren("title").size() != 0) {
				String rawDate = eventList.get(i).getAttributeValue("stop");
				eventEndList.add(splitDate(rawDate));
			}
		}
		return eventEndList;
	}

	private String splitDate(String rawDate) {
		String year = rawDate.substring(0, 4);
		String month = rawDate.substring(4, 6);
		String day = rawDate.substring(6, 8);
		String hour = rawDate.substring(8, 10);
		String minute = rawDate.substring(10, 12);
		String second = rawDate.substring(12, 14);

		return day + "-" + month + "-" + year + " " + hour + ":" + minute + ":"
		+ second;
	}

	public ArrayList<String> parseStartDates(List<Element> eventList) {
		ArrayList<String> eventStartList = new ArrayList<String>();
		for (int i = 0; i < eventList.size(); i++) {
			if (eventList.get(i).getChildren("title").size() != 0) {
				String rawDate = eventList.get(i).getAttributeValue("start");
				eventStartList.add(splitDate(rawDate));
			}
		}
		return eventStartList;
	}

	public static class Factory extends AbstractCalendarParser.ParserFactory {

		public boolean isThisParserType (String url) {
			return url.toLowerCase().contains("tv");
		}

		public String getTypeOfCalendarDetected() {
			return "XMLTV Calendar";
		}

		public AbstractCalendarParser getParser() {
			return new XMLTVParser();
		}

	}



}
