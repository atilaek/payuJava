package com.PMI.payu.Backend.domain.alu.request;

import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

    final public List<NameValuePair> getParameters() {
        List<NameValuePair> mcpDataParameters = new ArrayList<>();

        mcpDataParameters.add(new BasicNameValuePair("mcpCurrency", mcpCurrency));
        mcpDataParameters.add(new BasicNameValuePair("mcpAmount", mcpAmount));
        mcpDataParameters.add(new BasicNameValuePair("mcpRate", mcpRate));
        mcpDataParameters.add(new BasicNameValuePair("mcpFxTableId", mcpFxTableId));
        mcpDataParameters.add(new BasicNameValuePair("mcpPartnerId", mcpPartnerId));

        return mcpDataParameters;
    }
}
