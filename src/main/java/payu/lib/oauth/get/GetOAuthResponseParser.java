package payu.lib.oauth.get;

import com.PMI.payu.Backend.domain.oauth.get.GetOAuthResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import payu.lib.common.client.ResponseParser;
import payu.lib.common.exceptions.ResponseParsingException;

import java.io.IOException;


public class GetOAuthResponseParser implements ResponseParser {

    public GetOAuthResponse parseResponse(final HttpResponse httpResponse) throws ResponseParsingException {
        GetOAuthResponse getOAuthResponse;
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            getOAuthResponse = objectMapper.readValue(httpResponse.getEntity().getContent(), GetOAuthResponse.class);
        } catch (IOException e) {
            throw new ResponseParsingException(e);
        }

        return getOAuthResponse;
    }
}
