package payu.lib.ios;

import org.apache.http.NameValuePair;
import payu.lib.common.authentication.InvalidSignatureException;
import payu.lib.common.client.ApiClient;
import payu.lib.common.client.CommunicationException;
import payu.lib.common.client.InvalidXmlResponseParsingException;

import java.util.List;

public class IosClient {

    private static final String IOS_ENDPOINT = "/order/ios.php";

    private final ApiClient apiClient;

    public IosClient(ApiClient apiClient) {
        this.apiClient = apiClient;
    }

    public List<NameValuePair> call(final List<NameValuePair> requestParameters) throws CommunicationException, InvalidXmlResponseParsingException, InvalidSignatureException {

        return null;//apiClient.call(IOS_ENDPOINT, requestParameters);
    }
}
