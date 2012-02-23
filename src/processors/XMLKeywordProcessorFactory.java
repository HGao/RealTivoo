package processors;

public class XMLKeywordProcessorFactory extends AbstractProcessorFactory {
    public XMLKeywordProcessor create() {
        return new XMLKeywordProcessor();
    }
}
