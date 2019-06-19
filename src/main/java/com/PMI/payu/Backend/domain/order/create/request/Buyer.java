package com.PMI.payu.Backend.domain.order.create.request;

import lombok.*;
import org.json.JSONObject;
import org.springframework.lang.Nullable;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Buyer {

    @NonNull
    private String email;

    @Nullable
    private String customerIp;
    @Nullable
    private String extCustomerId;
    @Nullable
    private String phone;
    @Nullable
    private String firstName;
    @Nullable
    private String lastName;
    @Nullable
    private String nin;
    @Nullable
    private Language language;
    @Nullable
    private BuyerDelivery buyerDelivery;

    final public JSONObject getJSONObject() {
        Map<String, Object> jsonMap = new HashMap<>();

        jsonMap.put("email", email);

        getCustomerIp().ifPresent(customerIp -> jsonMap.put("customerIp", customerIp));
        getExtCustomerId().ifPresent(extCustomerId -> jsonMap.put("extCustomerId", extCustomerId));
        getPhone().ifPresent(phone -> jsonMap.put("phone", phone));
        getFirstName().ifPresent(firstName -> jsonMap.put("firstName", firstName));
        getLastName().ifPresent(lastName -> jsonMap.put("lastName", lastName));
        getNin().ifPresent(nin -> jsonMap.put("nin", nin));
        getLanguage().ifPresent(language -> jsonMap.put("language", language.name()));
        getBuyerDelivery().ifPresent(buyerDelivery -> jsonMap.put("buyer.delivery", buyerDelivery.getJSONObject()));

        return new JSONObject(jsonMap);
    }

    @Nullable
    public Optional<String> getCustomerIp() {
        return Optional.ofNullable(customerIp);
    }

    @Nullable
    public Optional<String> getExtCustomerId() {
        return Optional.ofNullable(extCustomerId);
    }

    @Nullable
    public Optional<String> getPhone() {
        return Optional.ofNullable(phone);
    }

    @Nullable
    public Optional<String> getFirstName() {
        return Optional.ofNullable(firstName);
    }

    @Nullable
    public Optional<String> getLastName() {
        return Optional.ofNullable(lastName);
    }

    @Nullable
    public Optional<String> getNin() {
        return Optional.ofNullable(nin);
    }

    @Nullable
    public Optional<Language> getLanguage() {
        return Optional.ofNullable(language);
    }

    @Nullable
    public Optional<BuyerDelivery> getBuyerDelivery() {
        return Optional.ofNullable(buyerDelivery);
    }
}
