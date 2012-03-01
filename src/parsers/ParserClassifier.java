package parsers;

import java.util.*;

public class ParserClassifier {

	private ArrayList<AbstractCalendarParser.ParserFactory> myParserTypes;
	private String myURL;

	@SuppressWarnings("serial")
	public ParserClassifier(String filename) {
		myURL = filename;

		myParserTypes = new ArrayList<AbstractCalendarParser.ParserFactory>()
		{
			{
				add(new DukeBasketballParser.Factory());
				add(new DukeEventParser.Factory());
				add(new GCalParser.Factory());
				add(new NFLParser.Factory());
				add(new XMLTVParser.Factory());
			}
		};

	}

	public AbstractCalendarParser getParserType () {

		for (AbstractCalendarParser.ParserFactory type : myParserTypes){
			if (type.isThisParserType(myURL)) {
				System.out.println(type.getTypeOfCalendarDetected() + 
						" detected and being parsed.");
				return type.getParser();
			}
		}

		System.err.print("Calendar format unsupported");
		return null;

	}


}
