package payu.lib.auth;

import com.PMI.payu.Backend.domain.auth.AuthResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;

import java.io.IOException;


public class AuthResponseParser {

    public AuthResponse parseResponse(final HttpResponse httpResponse) {
        AuthResponse authResponse = new AuthResponse();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            authResponse = objectMapper.readValue(httpResponse.getEntity().getContent(), AuthResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return authResponse;
    }
}
