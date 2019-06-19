package payu.lib.order.cancel;

import com.PMI.payu.Backend.domain.common.response.Response;
import com.PMI.payu.Backend.domain.oauth.get.GetOAuthResponse;
import com.PMI.payu.Backend.domain.order.cancel.response.OrderCancelResponse;
import com.PMI.payu.Backend.domain.order.create.response.OrderCreateResponse;
import payu.lib.common.client.ApiDeleteClient;
import payu.lib.common.exceptions.HttpCommunicationException;
import payu.lib.common.exceptions.ResponseParsingException;

public class OrderCancelClient {

    private static final String CANCEL_ENDPOINT = "/api/v2_1/orders";
    private final ApiDeleteClient apiDeleteClient;

    public OrderCancelClient(ApiDeleteClient apiDeleteClient) {
        this.apiDeleteClient = apiDeleteClient;
    }

    public OrderCancelResponse call(final Response response,
                                    final GetOAuthResponse getOAuthResponse) throws HttpCommunicationException, ResponseParsingException {

        return (OrderCancelResponse) apiDeleteClient.call(CANCEL_ENDPOINT
                        + "/" + ((OrderCreateResponse) response).getOrderId()
                , getOAuthResponse);
    }
}
