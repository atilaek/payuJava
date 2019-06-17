package com.PMI.payu.Backend.domain.alu.request;

import lombok.*;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.lang.Nullable;

import java.util.*;

@Getter
@Setter
@RequiredArgsConstructor
@AllArgsConstructor
public class Order {

    @NonNull
    private final String customerIp;
    @NonNull
    private final String merchantPosId;
    @NonNull
    private final String description;
    @NonNull
    private final Currency currencyCode;
    @NonNull
    private final List<Product> products;

    @Nullable
    private String extOrderId;
    @Nullable
    private String notifyUrl;
    @Nullable
    private String validityTime;
    @Nullable
    private String additionalDescription;
    @Nullable
    private CardOnFile cardOnFile;
    @Nullable
    private String continueUrl;
    @Nullable
    private Buyer buyer;
    @Nullable
    private PayMethod payMethods;
    @Nullable
    private McpData mcpData;

    final public JSONObject getJSONObject() {
        Map<String, Object> jsonMap = new HashMap<>();

        jsonMap.put("customerIp", customerIp);
        jsonMap.put("merchantPosId", merchantPosId);
        jsonMap.put("description", description);
        jsonMap.put("currencyCode", currencyCode.getCurrencyCode());

        JSONArray JSONArrayProducts = new JSONArray();
        int totalAmount = 0;
        for (Product product : products) {
            totalAmount += product.getQuantity() * product.getUnitPrice();
            JSONArrayProducts.put(product.getJSONObject());
        }
        jsonMap.put("products", JSONArrayProducts);
        jsonMap.put("totalAmount", totalAmount);


        getExtOrderId().ifPresent(extOrderId -> jsonMap.put("extOrderId", extOrderId));
        getNotifyUrl().ifPresent(notifyUrl -> jsonMap.put("notifyUrl", notifyUrl));
        getValidityTime().ifPresent(validityTime -> jsonMap.put("validityTime", validityTime));
        getAdditionalDescription().ifPresent(additionalDescription -> jsonMap.put("additionalDescription", additionalDescription));
        getCardOnFile().ifPresent(cardOnFile -> jsonMap.put("cardOnFile", cardOnFile.name()));
        getContinueUrl().ifPresent(continueUrl -> jsonMap.put("continueUrl", continueUrl));

        getBuyer().ifPresent(buyer -> jsonMap.put("buyer", buyer.getJSONObject()));
        getPayMethods().ifPresent(payMethods -> jsonMap.put("payMethods", payMethods.getJSONObject()));
        getMcpData().ifPresent(mcpData -> jsonMap.put("mcpData", mcpData.getJSONObject()));

        return new JSONObject(jsonMap);

    }

    final public List<NameValuePair> getParameters() {
        List<NameValuePair> orderParameters = new ArrayList<>();

        orderParameters.add(new BasicNameValuePair("customerIp", customerIp));
        orderParameters.add(new BasicNameValuePair("merchantPosId", merchantPosId));
        orderParameters.add(new BasicNameValuePair("description", description));
        orderParameters.add(new BasicNameValuePair("currencyCode", currencyCode.getCurrencyCode()));

        int totalAmount = 0;
        for (Product product : products) {
            totalAmount += product.getQuantity() * product.getUnitPrice();
            orderParameters.addAll(product.getParameters());
        }
        orderParameters.add(new BasicNameValuePair("totalAmount", String.valueOf(totalAmount)));

        getExtOrderId().ifPresent(extOrderId -> orderParameters.add(new BasicNameValuePair("extOrderId", extOrderId)));
        getNotifyUrl().ifPresent(notifyUrl -> orderParameters.add(new BasicNameValuePair("notifyUrl", notifyUrl)));
        getValidityTime().ifPresent(validityTime -> orderParameters.add(new BasicNameValuePair("validityTime", validityTime)));
        getAdditionalDescription().ifPresent(additionalDescription -> orderParameters.add(new BasicNameValuePair("additionalDescription", additionalDescription)));
        getCardOnFile().ifPresent(cardOnFile -> orderParameters.add(new BasicNameValuePair("cardOnFile", cardOnFile.name())));
        getContinueUrl().ifPresent(continueUrl -> orderParameters.add(new BasicNameValuePair("continueUrl", continueUrl)));

        getBuyer().ifPresent(buyer -> orderParameters.addAll(buyer.getParameters()));
        getPayMethods().ifPresent(payMethods -> orderParameters.addAll(payMethods.getParameters()));
        getMcpData().ifPresent(mcpData -> orderParameters.addAll(mcpData.getParameters()));

        return orderParameters;
    }

    @Nullable
    public Optional<String> getExtOrderId() {
        return Optional.ofNullable(extOrderId);
    }

    @Nullable
    public Optional<String> getNotifyUrl() {
        return Optional.ofNullable(notifyUrl);
    }

    @Nullable
    public Optional<String> getValidityTime() {
        return Optional.ofNullable(validityTime);
    }

    @Nullable
    public Optional<String> getAdditionalDescription() {
        return Optional.ofNullable(additionalDescription);
    }

    @Nullable
    public Optional<CardOnFile> getCardOnFile() {
        return Optional.ofNullable(cardOnFile);
    }

    @Nullable
    public Optional<String> getContinueUrl() {
        return Optional.ofNullable(continueUrl);
    }

    @Nullable
    public Optional<Buyer> getBuyer() {
        return Optional.ofNullable(buyer);
    }

    @Nullable
    public Optional<PayMethod> getPayMethods() {
        return Optional.ofNullable(payMethods);
    }

    @Nullable
    public Optional<McpData> getMcpData() {
        return Optional.ofNullable(mcpData);
    }
}

