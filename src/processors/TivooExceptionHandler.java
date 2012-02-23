package processors;

public class TivooExceptionHandler extends Exception{
    
    public TivooExceptionHandler(String msg){
        super(msg);
        printStackTrace();
    }

}
