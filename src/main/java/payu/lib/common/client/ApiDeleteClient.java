package payu.lib.common.client;

import com.PMI.payu.Backend.domain.oauth.get.GetOAuthResponse;
import org.apache.http.client.methods.HttpDelete;
import payu.lib.common.exceptions.HttpCommunicationException;
import payu.lib.common.exceptions.ResponseParsingException;

public class ApiDeleteClient extends ApiClient {

    public ApiDeleteClient(ApiHttpClient apiHttpClient, ResponseParser apiResponseParser) {
        super(apiHttpClient, apiResponseParser);
    }

    public Object call(final String endpoint,
                       final GetOAuthResponse getOAuthResponse) throws HttpCommunicationException, ResponseParsingException {

        HttpDelete httpRequest = new HttpDelete(endpoint);
        httpRequest = (HttpDelete) fillOAuthTokenHeader(getOAuthResponse, httpRequest);
        return callClient(httpRequest);

    }
}
