package payu.lib.common.client;

import com.PMI.payu.Backend.domain.oauth.get.GetOAuthResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.json.JSONObject;
import payu.lib.common.exceptions.HttpCommunicationException;
import payu.lib.common.exceptions.ResponseParsingException;

import java.nio.charset.StandardCharsets;
import java.util.List;

public class ApiPostClient extends ApiClient {

    public ApiPostClient(ApiHttpClient apiHttpClient, ResponseParser apiResponseParser) {
        super(apiHttpClient, apiResponseParser);
    }

    public Object callWithOAuthHeader(final String endpoint, final JSONObject jsonObject,
                                      final GetOAuthResponse getOAuthResponse) throws HttpCommunicationException, ResponseParsingException {

        final HttpPost httpRequest = new HttpPost(endpoint);
        fillOAuthTokenHeader(getOAuthResponse, httpRequest);
        httpRequest.setEntity(new StringEntity(jsonObject.toString(), StandardCharsets.UTF_8));

        return callClient(httpRequest);
    }

    public Object call(final String endpoint, final List<NameValuePair> parameters) throws HttpCommunicationException, ResponseParsingException {

        final HttpPost httpRequest = new HttpPost(endpoint);
        httpRequest.setEntity(new UrlEncodedFormEntity(parameters, StandardCharsets.UTF_8));
        return callClient(httpRequest);
    }
}
