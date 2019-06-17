package payu.lib.auth;

import com.PMI.payu.Backend.domain.auth.AuthResponse;
import org.apache.http.HttpException;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.json.JSONObject;
import payu.lib.common.authentication.InvalidSignatureException;
import payu.lib.common.client.ApiHttpClient;
import payu.lib.common.client.CommunicationException;
import payu.lib.common.client.InvalidXmlResponseParsingException;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class AuthClient {

    private static final String AUTH_ENDPOINT = "/pl/standard/user/oauth/authorize";

    private final ApiHttpClient apiHttpClient;
    private final AuthResponseParser authResponseParser;

    public AuthClient(ApiHttpClient apiHttpClient, AuthResponseParser authResponseParser) {

        this.apiHttpClient = apiHttpClient;
        this.authResponseParser = authResponseParser;
    }

    public AuthResponse call(final List<NameValuePair> requestParameters) throws CommunicationException, InvalidXmlResponseParsingException, InvalidSignatureException {

        final HttpPost httpRequest = new HttpPost(AUTH_ENDPOINT);
        httpRequest.setEntity(new UrlEncodedFormEntity(requestParameters, StandardCharsets.UTF_8));

        final HttpResponse httpResponse;
        try {
            httpResponse = apiHttpClient.callHttp(httpRequest);
        } catch (HttpException e) {
            throw new CommunicationException(e);
        } catch (Error e) {
            throw new Error(e);
        }
        return authResponseParser.parseResponse(httpResponse);
    }


    public AuthResponse call2(final JSONObject authJson) throws CommunicationException, InvalidXmlResponseParsingException, InvalidSignatureException{

        final HttpPost httpRequest = new HttpPost(AUTH_ENDPOINT);
        httpRequest.setHeader("Content-type", "application/json");


        StringEntity params = null;
        try {
            params = new StringEntity(authJson.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        httpRequest.setEntity(params);

        final HttpResponse httpResponse;
        try {
            httpResponse = apiHttpClient.callHttp(httpRequest);
        } catch (HttpException e) {
            throw new CommunicationException(e);
        } catch (Error e) {
            throw new Error(e);
        }

        final AuthResponse response = authResponseParser.parseResponse(httpResponse);

        return response;



        /*
        final HttpPost httpRequest = new HttpPost(AUTH_ENDPOINT);

        httpRequest.setEntity(new StringEntity(authJson.toString(), StandardCharsets.UTF_8));

        final HttpResponse httpResponse;
        try {
            httpResponse = apiHttpClient.callHttp(httpRequest);
        } catch (HttpException e) {
            throw new CommunicationException(e);
        } catch (Error e) {
            throw new Error(e);
        }

        return authResponseParser.parseResponse(httpResponse);
*/
    }
}