package com.PMI.payu.Backend.domain.order.create.request;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.json.JSONObject;
import org.springframework.lang.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
        if (getValue() != null) {
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

    @Nullable
    public Optional<String> getValue() {
        return Optional.ofNullable(value);
    }

    @Nullable
    public Optional<String> getAuthorizationCode() {
        return Optional.ofNullable(authorizationCode);
    }


}
