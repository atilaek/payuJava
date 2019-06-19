package payu.lib.oauth.get;

import com.PMI.payu.Backend.domain.oauth.get.GetOAuthRequest;
import com.PMI.payu.Backend.domain.oauth.get.GetOAuthResponse;
import payu.lib.common.client.ApiPostClient;
import payu.lib.common.exceptions.HttpCommunicationException;
import payu.lib.common.exceptions.ResponseParsingException;

public class GetOAuthClient {

    private static final String GET_AUTH_ENDPOINT = "/pl/standard/user/oauth/authorize";

    private final ApiPostClient apiPostClient;

    public GetOAuthClient(ApiPostClient apiPostClient) {
        this.apiPostClient = apiPostClient;
    }


    public GetOAuthResponse call(final GetOAuthRequest getOAuthRequest) throws HttpCommunicationException, ResponseParsingException {
        return (GetOAuthResponse) apiPostClient.call(GET_AUTH_ENDPOINT,
                getOAuthRequest.getParameters());
    }
}