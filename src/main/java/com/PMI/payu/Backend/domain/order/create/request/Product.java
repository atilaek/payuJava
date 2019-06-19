package com.PMI.payu.Backend.domain.order.create.request;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.json.JSONObject;
import org.springframework.lang.Nullable;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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

    @Nullable
    public Optional<String> getVirtual() {
        return Optional.ofNullable(virtual);
    }

    @Nullable
    public Optional<LocalDateTime> getListingDate() {
        return Optional.ofNullable(listingDate);
    }

}
