package payu.lib.idn;

import org.apache.http.NameValuePair;
import payu.lib.common.authentication.InvalidSignatureException;
import payu.lib.common.client.ApiClient;
import payu.lib.common.client.CommunicationException;
import payu.lib.common.client.InvalidXmlResponseParsingException;

import java.util.List;

public class IdnClient {

    private static final String IDN_ENDPOINT = "/order/idn.php";

    private final ApiClient apiClient;

    public IdnClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public List<NameValuePair> call(final List<NameValuePair> requestParameters) throws CommunicationException, InvalidXmlResponseParsingException, InvalidSignatureException {

        return null;//apiClient.call(IDN_ENDPOINT, requestParameters);
    }
}
