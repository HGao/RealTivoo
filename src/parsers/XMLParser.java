package parsers;

import java.io.*;
import java.util.*;

import org.jdom.*;
import org.jdom.input.*;

public class XMLParser {
    SAXBuilder builder;
    File myFile;
    Document myDocument;

    public XMLParser() {
        builder = new SAXBuilder();
    }

    public List<CalendarObject> loadFile(String filename){
        myFile = new File(filename);
        try {
            myDocument = builder.build(myFile);
        } catch (JDOMException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<CalendarObject> ret = new ArrayList<CalendarObject>();
        Element rootNode = myDocument.getRootElement();
        List<?> list = rootNode.getChildren();
        List<Element> eventList = new ArrayList<Element>();
        for (int i = 0; i < list.size(); i++) {
            Element currentEvent = (Element) list.get(i);
            eventList.add(currentEvent);
            //System.out.println(i + " " + currentEvent.getChild("Col1").getValue());
        }
        ParserClassifier pc = new ParserClassifier();
        AbstractCalendarParser acp = pc.getParserType(eventList);
        ret = acp.parseEvents(eventList);
        System.out.println(ret.size() + " events parsed");
        return ret;

    }
}
