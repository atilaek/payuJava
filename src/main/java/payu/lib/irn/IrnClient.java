package payu.lib.irn;

import org.apache.http.NameValuePair;
import payu.lib.common.authentication.InvalidSignatureException;
import payu.lib.common.client.ApiClient;
import payu.lib.common.client.CommunicationException;
import payu.lib.common.client.InvalidXmlResponseParsingException;

import java.util.List;

public class IrnClient {

    private static final String IRN_ENDPOINT = "/order/irn.php";

    private final ApiClient apiClient;

    public IrnClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public List<NameValuePair> call(final List<NameValuePair> requestParameters) throws CommunicationException, InvalidXmlResponseParsingException, InvalidSignatureException {

        return null;//apiClient.call(IRN_ENDPOINT, requestParameters);
    }
}
