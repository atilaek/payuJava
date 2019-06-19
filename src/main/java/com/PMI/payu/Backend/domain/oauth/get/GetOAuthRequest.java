package com.PMI.payu.Backend.domain.oauth.get;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class GetOAuthRequest {

    @NonNull
    private final String grant_type;
    @NonNull
    private final String client_id;
    @NonNull
    private final String client_secret;

    final public List<NameValuePair> getParameters() {
        List<NameValuePair> antiFraudParameters = new ArrayList<>();

        antiFraudParameters.add(new BasicNameValuePair("grant_type", grant_type));
        antiFraudParameters.add(new BasicNameValuePair("client_id", client_id));
        antiFraudParameters.add(new BasicNameValuePair("client_secret", client_secret));

        return antiFraudParameters;
    }
}

