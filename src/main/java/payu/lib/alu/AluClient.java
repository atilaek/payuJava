package payu.lib.alu;

import com.PMI.payu.Backend.domain.alu.response.AluResponse;
import com.PMI.payu.Backend.domain.auth.AuthResponse;
import org.apache.http.NameValuePair;
import org.json.JSONObject;
import payu.lib.common.authentication.InvalidSignatureException;
import payu.lib.common.client.ApiClient;
import payu.lib.common.client.CommunicationException;
import payu.lib.common.client.InvalidXmlResponseParsingException;

import java.io.UnsupportedEncodingException;
import java.util.List;

public class AluClient {

    private static final String ALU_ENDPOINT = "/api/v2_1/orders";
    private final ApiClient apiClient;

    public AluClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public AluResponse call(final JSONObject jsonObject,
                            final AuthResponse authResponse) throws CommunicationException, InvalidXmlResponseParsingException, InvalidSignatureException {

        try {
            return (AluResponse) apiClient.call(ALU_ENDPOINT, jsonObject, authResponse);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public AluResponse call2(final List<NameValuePair> authRequestParameters,
                            final AuthResponse authResponse) throws CommunicationException, InvalidXmlResponseParsingException, InvalidSignatureException {

        return (AluResponse) apiClient.call2(ALU_ENDPOINT, authRequestParameters, authResponse);
    }

}
