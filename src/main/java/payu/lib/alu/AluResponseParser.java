package payu.lib.alu;

import com.PMI.payu.Backend.domain.alu.response.AluResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpResponse;
import payu.lib.common.client.ResponseParser;

import java.io.IOException;

public class AluResponseParser implements ResponseParser {

    public AluResponse parseResponse(final HttpResponse httpResponse) {
        AluResponse aluResponse = new AluResponse();
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            aluResponse = objectMapper.readValue(httpResponse.getEntity().getContent(), AluResponse.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return aluResponse;
    }
}
