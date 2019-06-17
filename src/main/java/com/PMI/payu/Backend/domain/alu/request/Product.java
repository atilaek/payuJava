package com.PMI.payu.Backend.domain.alu.request;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.util.*;

@Getter
@Setter
@RequiredArgsConstructor
public class Product {

    @NonNull
    private String name;
    @NonNull
    private int unitPrice;
    @NonNull
    private int quantity;

    @Nullable
    private String virtual;
    @Nullable
    private LocalDateTime listingDate;

    final public JSONObject getJSONObject() {
        Map<String, Object> jsonMap = new HashMap<>();

        jsonMap.put("name", name);
        jsonMap.put("unitPrice", unitPrice);
        jsonMap.put("quantity", quantity);

        getVirtual().ifPresent(order_virtual -> jsonMap.put("virtual", virtual));
        getListingDate().ifPresent(order_listingDate -> jsonMap.put("listingDate", listingDate.toString()));

        return new JSONObject(jsonMap);
    }

    final public List<NameValuePair> getParameters() {
        List<NameValuePair> productParameters = new ArrayList<>();

        productParameters.add(new BasicNameValuePair("name", name));
        productParameters.add(new BasicNameValuePair("unitPrice", String.valueOf(unitPrice)));
        productParameters.add(new BasicNameValuePair("quantity", String.valueOf(quantity)));

        getVirtual().ifPresent(order_virtual -> productParameters.add(new BasicNameValuePair("virtual", virtual)));
        getListingDate().ifPresent(order_listingDate -> productParameters.add(new BasicNameValuePair("listingDate", listingDate.toString())));

        return productParameters;
    }

    @Nullable
    public Optional<String> getVirtual() {
        return Optional.ofNullable(virtual);
    }

    @Nullable
    public Optional<LocalDateTime> getListingDate() {
        return Optional.ofNullable(listingDate);
    }

}
