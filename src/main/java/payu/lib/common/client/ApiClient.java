package payu.lib.common.client;

import com.PMI.payu.Backend.domain.auth.AuthResponse;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;
import payu.lib.common.authentication.ApiAuthenticationService;
import payu.lib.common.authentication.InvalidSignatureException;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class ApiClient {
    private final ApiHttpClient apiHttpClient;
    private final ApiAuthenticationService apiAuthenticationService;
    private final ResponseParser apiResponseParser;


    public ApiClient(ApiHttpClient apiHttpClient, ApiAuthenticationService apiAuthenticationService, ResponseParser apiResponseParser) {
        this.apiHttpClient = apiHttpClient;
        this.apiAuthenticationService = apiAuthenticationService;
        this.apiResponseParser = apiResponseParser;
    }

    public Object call(final String endpoint, JSONObject Jsonobject,
                       final AuthResponse authResponse) throws CommunicationException, InvalidXmlResponseParsingException, InvalidSignatureException, UnsupportedEncodingException {

        final HttpPost httpRequest = new HttpPost(endpoint);
        httpRequest.setHeader("Authorization", authResponse.getToken_type() + " " + authResponse.getAccess_token());
        httpRequest.setHeader("Content-Type", "application/json");

        httpRequest.setEntity(new StringEntity(Jsonobject.toString(), "UTF8"));

        final HttpResponse httpResponse;
        try {
            httpResponse = apiHttpClient.callHttp(httpRequest);
        } catch (HttpException e) {
            throw new CommunicationException(e);
        } catch (Error e) {
            throw new Error(e);
        }

        final Object response = apiResponseParser.parseResponse(httpResponse);

        return response;
    }
    public Object call2(final String endpoint, final List<NameValuePair> requestParameters, final AuthResponse authResponse) throws CommunicationException, InvalidXmlResponseParsingException, InvalidSignatureException {

        final List<NameValuePair> requestParametersWithSignature = apiAuthenticationService.addSignature(requestParameters);

        final HttpPost httpRequest = new HttpPost(endpoint);
        httpRequest.setHeader("Authorization", authResponse.getToken_type() + " " + authResponse.getAccess_token());
        httpRequest.setHeader("Content-Type", "application/json");

        httpRequest.setEntity(new StringEntity(requestParameters.toString(), StandardCharsets.UTF_8));

        // call http and obtain response
        final HttpResponse httpResponse;
        try {
            httpResponse = apiHttpClient.callHttp(httpRequest);
        } catch (HttpException e) {
            throw new CommunicationException(e);
        }

        final Object response = apiResponseParser.parseResponse(httpResponse);

        return response;
    }

}
