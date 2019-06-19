package payu.lib.order.create;

import com.PMI.payu.Backend.domain.order.create.response.OrderCreateResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import payu.lib.common.client.ResponseParser;
import payu.lib.common.exceptions.ResponseParsingException;

import java.io.IOException;

public class OrderCreateResponseParser implements ResponseParser {

    public OrderCreateResponse parseResponse(final HttpResponse httpResponse) throws ResponseParsingException {
        OrderCreateResponse createOrderResponse;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            createOrderResponse = objectMapper.readValue(httpResponse.getEntity().getContent(), OrderCreateResponse.class);
        } catch (IOException e) {
            throw new ResponseParsingException(e);
        }
        return createOrderResponse;
    }
}
