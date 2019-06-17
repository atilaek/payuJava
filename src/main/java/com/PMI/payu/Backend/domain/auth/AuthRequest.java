package com.PMI.payu.Backend.domain.auth;

import lombok.*;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Getter
@Setter
@AllArgsConstructor
public class AuthRequest {

    @NonNull
    private final String grant_type;
    @NonNull
    private final String client_id;
    @NonNull
    private final String client_secret;


    final public JSONObject getJSONObject() {
        Map<String, Object> jsonMap = new HashMap<>();

        jsonMap.put("grant_type", grant_type);
        jsonMap.put("client_id", client_id);
        jsonMap.put("client_secret", client_secret);

        return new JSONObject(jsonMap);
    }



    final public List<NameValuePair> getParameters() {
        List<NameValuePair> antiFraudParameters = new ArrayList<>();

        antiFraudParameters.add(new BasicNameValuePair("grant_type", grant_type));
        antiFraudParameters.add(new BasicNameValuePair("client_id", client_id));
        antiFraudParameters.add(new BasicNameValuePair("client_secret", client_secret));

        return antiFraudParameters;
    }
}

