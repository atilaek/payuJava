package payu.lib.order.create;

import com.PMI.payu.Backend.domain.oauth.get.GetOAuthResponse;
import com.PMI.payu.Backend.domain.order.create.response.OrderCreateResponse;
import org.json.JSONObject;
import payu.lib.common.client.ApiPostClient;
import payu.lib.common.exceptions.HttpCommunicationException;
import payu.lib.common.exceptions.ResponseParsingException;

public class OrderCreateClient {

    private static final String ALU_ENDPOINT = "/api/v2_1/orders";
    private final ApiPostClient apiPostClient;

    public OrderCreateClient(ApiPostClient apiPostClient) {
        this.apiPostClient = apiPostClient;
    }

    public OrderCreateResponse call(final JSONObject jsonObject,
                                    final GetOAuthResponse getOAuthResponse) throws HttpCommunicationException, ResponseParsingException {

        return (OrderCreateResponse) apiPostClient.callWithOAuthHeader(ALU_ENDPOINT, jsonObject, getOAuthResponse);
    }
}
