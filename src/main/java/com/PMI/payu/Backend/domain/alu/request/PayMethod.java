package com.PMI.payu.Backend.domain.alu.request;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import org.springframework.lang.Nullable;

import java.util.*;

@Getter
@Setter
@RequiredArgsConstructor
public class PayMethod {


    @NonNull
    private final MethodType type;

    @Nullable
    private final String value;
    @Nullable
    private final String authorizationCode;


    final public JSONObject getJSONObject() {
        Map<String, Object> jsonMap = new HashMap<>();

        jsonMap.put("type", type);
        if (getValue().isEmpty()) {
            if (getType().equals(MethodType.PBL)
                    || getType().equals(MethodType.CARD_TOKEN)) {
                throw new Error("Payment Method type is PBL/CARD TOKEN, but value is empty!");
            }
        } else {
            getValue().ifPresent(value -> jsonMap.put("value", value));
        }

        getAuthorizationCode().ifPresent(authorizationCode -> jsonMap.put("authorizationCode", authorizationCode));

        return new JSONObject(jsonMap);

    }

    final public List<NameValuePair> getParameters() {
        List<NameValuePair> payMethodParameters = new ArrayList<>();

        payMethodParameters.add(new BasicNameValuePair("type", type.name()));
        if (getValue().isEmpty()) {
            if (getType().equals(MethodType.PBL)
                    || getType().equals(MethodType.CARD_TOKEN)) {
                throw new Error("Payment Method type is PBL/CARD TOKEN, but value is empty!");
            }
        } else {
            getValue().ifPresent(value -> payMethodParameters.add(new BasicNameValuePair("value", value)));
        }
        getAuthorizationCode().ifPresent(authorizationCode -> payMethodParameters.add(new BasicNameValuePair("authorizationCode", authorizationCode)));

        return payMethodParameters;
    }

    @Nullable
    public Optional<String> getValue() {
        return Optional.ofNullable(value);
    }

    @Nullable
    public Optional<String> getAuthorizationCode() {
        return Optional.ofNullable(authorizationCode);
    }


}
