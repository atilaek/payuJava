package payu.lib.common.client;

import org.apache.http.HttpResponse;

public interface ResponseParser<T> {

    T parseResponse(HttpResponse httpResponse) throws InvalidXmlResponseParsingException;
}
