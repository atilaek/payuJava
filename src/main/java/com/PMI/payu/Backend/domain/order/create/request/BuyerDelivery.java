package com.PMI.payu.Backend.domain.order.create.request;

import lombok.*;
import org.json.JSONObject;
import org.springframework.lang.Nullable;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Optional;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class BuyerDelivery {

    @NonNull
    private final String street;
    @NonNull
    private final String postalCode;
    @NonNull
    private final String city;
    @NonNull
    private final Locale.IsoCountryCode countryCode;
    @NonNull
    private final String recipientName;

    @Nullable
    private String postalBox;
    @Nullable
    private String state;
    @Nullable
    private String name;
    @Nullable
    private String recipientEmail;
    @Nullable
    private String recipientPhone;

    final public JSONObject getJSONObject() {
        Map<String, Object> jsonMap = new HashMap<>();

        jsonMap.put("street", street);
        jsonMap.put("postalCode", postalCode);
        jsonMap.put("city", city);
        jsonMap.put("countryCode", countryCode.name());
        jsonMap.put("recipientName", recipientName);

        getPostalBox().ifPresent(postalBox -> jsonMap.put("postalBox", postalBox));
        getState().ifPresent(state -> jsonMap.put("state", state));
        getName().ifPresent(name -> jsonMap.put("name", name));
        getRecipientEmail().ifPresent(recipientEmail -> jsonMap.put("recipientEmail", recipientEmail));
        getRecipientPhone().ifPresent(recipientPhone -> jsonMap.put("recipientPhone", recipientPhone));

        return new JSONObject(jsonMap);
    }

    @Nullable
    public Optional<String> getPostalBox() {
        return Optional.ofNullable(postalBox);
    }

    @Nullable
    public Optional<String> getState() {
        return Optional.ofNullable(state);
    }

    @Nullable
    public Optional<String> getName() {
        return Optional.ofNullable(name);
    }

    @Nullable
    public Optional<String> getRecipientEmail() {
        return Optional.ofNullable(recipientEmail);
    }

    @Nullable
    public Optional<String> getRecipientPhone() {
        return Optional.ofNullable(recipientPhone);
    }

}
