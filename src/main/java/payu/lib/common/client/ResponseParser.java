package payu.lib.common.client;

import org.apache.http.HttpResponse;
import payu.lib.common.exceptions.ResponseParsingException;

public interface ResponseParser<T> {

    T parseResponse(HttpResponse httpResponse) throws ResponseParsingException;
}
