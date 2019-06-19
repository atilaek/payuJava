package payu.lib.common.client;

import com.PMI.payu.Backend.domain.oauth.get.GetOAuthResponse;
import org.apache.http.HttpException;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.protocol.HTTP;
import payu.lib.common.exceptions.HttpCommunicationException;
import payu.lib.common.exceptions.ResponseParsingException;

class ApiClient {
    private final ApiHttpClient apiHttpClient;
    private final ResponseParser apiResponseParser;

    ApiClient(ApiHttpClient apiHttpClient, ResponseParser apiResponseParser) {
        this.apiHttpClient = apiHttpClient;
        this.apiResponseParser = apiResponseParser;
    }

    HttpRequest fillOAuthTokenHeader(final GetOAuthResponse getOAuthResponse,
                                     final HttpRequest httpRequest) {

        httpRequest.setHeader("Authorization", getOAuthResponse.getToken_type() + " " + getOAuthResponse.getAccess_token());
        httpRequest.setHeader(HTTP.CONTENT_TYPE, "application/json");
        return httpRequest;
    }

    Object callClient(final HttpRequest httpRequest) throws HttpCommunicationException, ResponseParsingException {

        HttpResponse httpResponse;
        try {
            httpResponse = apiHttpClient.callHttp(httpRequest);
        } catch (HttpException e) {
            throw new HttpCommunicationException(e);
        }

        return apiResponseParser.parseResponse(httpResponse);
    }
}
