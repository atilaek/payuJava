package payu.lib.common.client;

import com.PMI.payu.Backend.domain.oauth.get.GetOAuthResponse;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.json.JSONObject;
import payu.lib.common.exceptions.HttpCommunicationException;
import payu.lib.common.exceptions.ResponseParsingException;

import java.nio.charset.StandardCharsets;

public class ApiPutClient extends ApiClient {


    public ApiPutClient(ApiHttpClient apiHttpClient, ResponseParser apiResponseParser) {
        super(apiHttpClient, apiResponseParser);
    }

    public Object call(final String endpoint, JSONObject jsonObject,
                       final GetOAuthResponse getOAuthResponse) throws ResponseParsingException, HttpCommunicationException {

        HttpPut httpRequest = new HttpPut(endpoint);
        httpRequest.setEntity(new StringEntity(jsonObject.toString(), StandardCharsets.UTF_8));
        httpRequest = (HttpPut) fillOAuthTokenHeader(getOAuthResponse, httpRequest);

        return callClient(httpRequest);

    }
}
