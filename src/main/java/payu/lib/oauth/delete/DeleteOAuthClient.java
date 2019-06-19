package payu.lib.oauth.delete;

import com.PMI.payu.Backend.domain.oauth.delete.DeleteOAuthResponse;
import com.PMI.payu.Backend.domain.oauth.get.GetOAuthResponse;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import payu.lib.common.client.ApiHttpClient;
import payu.lib.common.exceptions.HttpCommunicationException;

public class DeleteOAuthClient {

    private static final String DELETE_OAUTH_ENDPOINT = "/api/v2_1/tokens/";

    private final ApiHttpClient apiHttpClient;
    private final DeleteOAuthResponseParser deleteOAuthResponseParser;

    public DeleteOAuthClient(ApiHttpClient apiHttpClient, DeleteOAuthResponseParser deleteOAuthResponseParser) {

        this.apiHttpClient = apiHttpClient;
        this.deleteOAuthResponseParser = deleteOAuthResponseParser;
    }

    public DeleteOAuthResponse call(final GetOAuthResponse getOAuthResponse) throws HttpCommunicationException {

        final HttpDelete httpRequest = new HttpDelete(DELETE_OAUTH_ENDPOINT);
        httpRequest.setHeader("Authorization", getOAuthResponse.getToken_type() + " " + getOAuthResponse.getAccess_token());
        //httpRequest.setHeader("Content-Type", "application/json");

        final HttpResponse httpResponse;
        try {
            httpResponse = apiHttpClient.callHttp(httpRequest);
        } catch (HttpException e) {
            throw new HttpCommunicationException(e);
        } catch (Error e) {
            throw new Error(e);
        }
        return deleteOAuthResponseParser.parseResponse(httpResponse);
    }
}