package com.PMI.payu.Backend.domain.order.update.request;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@RequiredArgsConstructor
public class OrderUpdateRequest {

    @NonNull
    private String orderId;
    @NonNull
    private OrderStatus orderStatus;

    final public JSONObject getJSONObject() {
        Map<String, Object> jsonMap = new HashMap<>();

        jsonMap.put("orderId", orderId);
        jsonMap.put("orderStatus", orderStatus.name());

        return new JSONObject(jsonMap);
    }
}