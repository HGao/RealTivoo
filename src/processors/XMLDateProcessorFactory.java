package processors;

public class XMLDateProcessorFactory extends AbstractProcessorFactory{
    
    public XMLDateProcessor create() {
        return new XMLDateProcessor();
    }

}
