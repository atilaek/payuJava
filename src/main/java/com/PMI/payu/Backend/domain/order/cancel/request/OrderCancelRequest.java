package com.PMI.payu.Backend.domain.order.cancel.request;

import com.PMI.payu.Backend.domain.common.request.Request;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class OrderCancelRequest extends Request {

    final public JSONObject getJSONObject() {
        Map<String, Object> jsonMap = new HashMap<>();

        jsonMap.put("orderId", getOrderId());
        return new JSONObject(jsonMap);
    }
}
