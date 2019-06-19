package payu.lib.order.update;

import com.PMI.payu.Backend.domain.oauth.get.GetOAuthResponse;
import com.PMI.payu.Backend.domain.order.update.request.OrderUpdateRequest;
import com.PMI.payu.Backend.domain.order.update.response.OrderUpdateResponse;
import payu.lib.common.client.ApiPutClient;
import payu.lib.common.exceptions.HttpCommunicationException;
import payu.lib.common.exceptions.ResponseParsingException;

public class OrderUpdateClient {

    private static final String UPDATE_ENDPOINT = "api/v2_1/orders/";
    private final ApiPutClient apiPutClient;

    public OrderUpdateClient(ApiPutClient apiPutClient) {
        this.apiPutClient = apiPutClient;
    }

    public OrderUpdateResponse call(final OrderUpdateRequest orderUpdateRequest,
                                    final GetOAuthResponse getOAuthResponse) throws HttpCommunicationException, ResponseParsingException {
        return (OrderUpdateResponse) apiPutClient.call(
                UPDATE_ENDPOINT + orderUpdateRequest.getOrderId() + "/status"
                , orderUpdateRequest.getJSONObject(), getOAuthResponse);
    }

}
