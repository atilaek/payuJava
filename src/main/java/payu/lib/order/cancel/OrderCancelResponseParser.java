package payu.lib.order.cancel;

import com.PMI.payu.Backend.domain.order.cancel.response.OrderCancelResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import payu.lib.common.client.ResponseParser;
import payu.lib.common.exceptions.ResponseParsingException;

import java.io.IOException;

public class OrderCancelResponseParser implements ResponseParser {

    public OrderCancelResponse parseResponse(final HttpResponse httpResponse) throws ResponseParsingException {
        OrderCancelResponse orderCancelResponse;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            orderCancelResponse = objectMapper.readValue(httpResponse.getEntity().getContent(), OrderCancelResponse.class);
        } catch (IOException e) {
            throw new ResponseParsingException(e);
        }
        return orderCancelResponse;
    }
}
