    package parsers;

import java.util.*;

import org.jdom.Element;

public class ParserClassifier {
    public ParserClassifier() {
        
    }
    
    public AbstractCalendarParser getParserType(List<Element> calendarEvents) {
        if (calendarEvents.get(0).getChild("Col1") != null){
            System.out.println("NFL Calendar detected");
            return new NFLParser();
        }
        else if (calendarEvents.get(0).getChild("entityType") != null) {
            System.out.println("Duke Events Calendar detected");
            return new DukeEventParser();
        }
        else if (calendarEvents.get(0).getChild("Subject") != null) {
            System.out.println("Duke Basketball Calendar detected");
            return new DukeBasketballParser();
        }
        else if (calendarEvents.get(0).getChild("display-name") != null) {
            System.out.println("XMLTV Calendar detected");
            return new XMLTVParser();
        }
        else if (calendarEvents.get(0).getText() != null) {
            System.out.println("Google Calendar detected");
            return new GCalParser();
        }
        return null;
        
    }
}
