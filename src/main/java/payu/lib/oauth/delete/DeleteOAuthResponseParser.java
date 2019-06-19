package payu.lib.oauth.delete;

import com.PMI.payu.Backend.domain.oauth.delete.DeleteOAuthResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;

import java.io.IOException;


public class DeleteOAuthResponseParser {

    DeleteOAuthResponse parseResponse(final HttpResponse httpResponse) {
        DeleteOAuthResponse deleteOAuthResponse = new DeleteOAuthResponse();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            deleteOAuthResponse = objectMapper.readValue(httpResponse.getEntity().getContent(), DeleteOAuthResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return deleteOAuthResponse;
    }
}
