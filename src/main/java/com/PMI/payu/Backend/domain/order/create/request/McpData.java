package com.PMI.payu.Backend.domain.order.create.request;

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
public class McpData {

    @NonNull
    private final String mcpCurrency;
    @NonNull
    private final String mcpAmount;
    @NonNull
    private final String mcpRate;
    @NonNull
    private final String mcpFxTableId;
    @NonNull
    private final String mcpPartnerId;


    final public JSONObject getJSONObject() {
        Map<String, Object> jsonMap = new HashMap<>();

        jsonMap.put("mcpCurrency", mcpCurrency);
        jsonMap.put("mcpAmount", mcpAmount);
        jsonMap.put("mcpRate", mcpRate);
        jsonMap.put("mcpFxTableId", mcpFxTableId);
        jsonMap.put("mcpPartnerId", mcpPartnerId);

        return new JSONObject(jsonMap);
    }

}
