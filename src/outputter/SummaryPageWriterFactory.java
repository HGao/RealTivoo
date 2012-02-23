package outputter;

public class SummaryPageWriterFactory extends AbstractHTMLWriterFactory {

    public AbstractHTMLWriter create() {
        return new SummaryPageWriter();
    }

}
