package marinalucentini.backend_w6_d4.exceptions;

public class BadRequestException extends RuntimeException{
    public  BadRequestException(String message){
        super(message);
    }
}
