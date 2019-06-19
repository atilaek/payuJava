package payu.lib.order.update;

import com.PMI.payu.Backend.domain.order.update.response.OrderUpdateResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import payu.lib.common.client.ResponseParser;
import payu.lib.common.exceptions.ResponseParsingException;

import java.io.IOException;

public class OrderUpdateResponseParser implements ResponseParser {

    public OrderUpdateResponse parseResponse(final HttpResponse httpResponse) throws ResponseParsingException {
        OrderUpdateResponse orderUpdateResponse;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            orderUpdateResponse = objectMapper.readValue(httpResponse.getEntity().getContent(), OrderUpdateResponse.class);
        } catch (IOException e) {
            throw new ResponseParsingException(e);
        }
        return orderUpdateResponse;
    }
}
