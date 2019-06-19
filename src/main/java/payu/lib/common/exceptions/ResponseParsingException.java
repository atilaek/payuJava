package payu.lib.common.exceptions;


import java.io.IOException;

public class ResponseParsingException extends Exception {
    public ResponseParsingException(IOException e) {
        super(e);
    }
}
