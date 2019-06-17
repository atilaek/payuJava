package com.PMI.payu.Backend.domain.alu.request;

import lombok.*;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import org.springframework.lang.Nullable;

import java.util.*;

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

    final public List<NameValuePair> getParameters() {
        List<NameValuePair> buyerParameters = new ArrayList<>();

        buyerParameters.add(new BasicNameValuePair("email", email));

        getCustomerIp().ifPresent(customerIp -> buyerParameters.add(new BasicNameValuePair("customerIp", customerIp)));
        getExtCustomerId().ifPresent(extCustomerId -> buyerParameters.add(new BasicNameValuePair("extCustomerId", extCustomerId)));
        getPhone().ifPresent(phone -> buyerParameters.add(new BasicNameValuePair("phone", phone)));
        getFirstName().ifPresent(firstName -> buyerParameters.add(new BasicNameValuePair("firstName", firstName)));
        getLastName().ifPresent(lastName -> buyerParameters.add(new BasicNameValuePair("lastName", lastName)));
        getNin().ifPresent(nin -> buyerParameters.add(new BasicNameValuePair("nin", nin)));
        getLanguage().ifPresent(language -> buyerParameters.add(new BasicNameValuePair("language", language.name())));

        getBuyerDelivery().ifPresent(buyerDelivery -> buyerParameters.addAll(buyerDelivery.getParameters()));

        return buyerParameters;
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
