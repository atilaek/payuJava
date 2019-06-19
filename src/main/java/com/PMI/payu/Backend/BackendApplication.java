package com.PMI.payu.Backend;

import com.PMI.payu.Backend.domain.common.response.Response;
import com.PMI.payu.Backend.domain.oauth.delete.DeleteOAuthResponse;
import com.PMI.payu.Backend.domain.oauth.get.GetOAuthRequest;
import com.PMI.payu.Backend.domain.oauth.get.GetOAuthResponse;
import com.PMI.payu.Backend.domain.order.cancel.response.OrderCancelResponse;
import com.PMI.payu.Backend.domain.order.create.request.OrderCreateRequest;
import com.PMI.payu.Backend.domain.order.create.request.Product;
import com.PMI.payu.Backend.domain.order.create.response.OrderCreateResponse;
import com.PMI.payu.Backend.domain.order.update.request.OrderStatus;
import com.PMI.payu.Backend.domain.order.update.request.OrderUpdateRequest;
import com.PMI.payu.Backend.domain.order.update.response.OrderUpdateResponse;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import payu.lib.common.client.ApiDeleteClient;
import payu.lib.common.client.ApiHttpClient;
import payu.lib.common.client.ApiPostClient;
import payu.lib.common.client.ApiPutClient;
import payu.lib.common.exceptions.HttpCommunicationException;
import payu.lib.common.exceptions.ResponseParsingException;
import payu.lib.oauth.delete.DeleteOAuthClient;
import payu.lib.oauth.delete.DeleteOAuthResponseParser;
import payu.lib.oauth.get.GetOAuthClient;
import payu.lib.oauth.get.GetOAuthResponseParser;
import payu.lib.order.cancel.OrderCancelClient;
import payu.lib.order.cancel.OrderCancelResponseParser;
import payu.lib.order.create.OrderCreateClient;
import payu.lib.order.create.OrderCreateResponseParser;
import payu.lib.order.update.OrderUpdateClient;
import payu.lib.order.update.OrderUpdateResponseParser;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

@SpringBootApplication
public class BackendApplication {

    private static final String SERVER_SCHEMA = "https";
    private static final int SERVER_PORT = 443;
    private static final String SERVER_HOST = "secure.snd.payu.com";
    // production connection settings
//    private static final String SERVER_SCHEMA = "https";
//    private static final int SERVER_PORT = 443;
//    private static final String SERVER_HOST = "secure.payu.com.tr";

    private static final String MERCHANT_CODE = "357269";
    private static final String MERCHANT_SECRET_KEY = "26cdcf1941547d273da485b5ce5dc3d4";
    // production credentials
//    private static final String MERCHANT_CODE = "???????";
//    private static final String MERCHANT_SECRET_KEY = "??????";

    private static GetOAuthClient getOAuthClient;
    private static DeleteOAuthClient deleteOAuthClient;
    private static OrderCreateClient orderCreateClient;
    private static OrderCancelClient orderCancelClient;
    private static OrderUpdateClient orderUpdateClient;

    public static void main(String[] args) {
        SpringApplication.run(BackendApplication.class, args);
        setUp();
        final GetOAuthRequest getOAuthRequest = new GetOAuthRequest("client_credentials",
                MERCHANT_CODE, MERCHANT_SECRET_KEY);
        final GetOAuthResponse getOAuthResponse = getOAuthToken(getOAuthRequest);
        if (getOAuthResponse != null) System.out.println(getOAuthResponse.toString());
        ////////////////////////////////////////
        List<Product> products = new ArrayList<>();
        products.add(new Product("Wireless Mouse",
                15000, 1));
        products.add(new Product("HDMI cable",
                6000, 1));
        OrderCreateRequest orderCreateRequest = new OrderCreateRequest("123.123.123.123", MERCHANT_CODE,
                "Order description",
                Currency.getInstance("PLN"),
                products);
        OrderCreateResponse orderCreateResponse = callForCreateOrder(orderCreateRequest.getJSONObject(), getOAuthResponse);
        if (orderCreateResponse != null) System.out.println(orderCreateResponse.toString());
        ////////////////////////////////////////
        OrderUpdateRequest orderUpdateRequest
                = new OrderUpdateRequest(orderCreateResponse.getOrderId(),
                OrderStatus.WAITING_FOR_CONFIRMATION);
        OrderUpdateResponse orderUpdateResponse =
                callForOrderUpdate(orderUpdateRequest, getOAuthResponse);
        if (orderUpdateResponse != null) System.out.println(orderUpdateResponse.toString());
        ////////////////////////////////////////
        OrderCancelResponse orderCancelResponse =
                callForOrderCancellation(orderCreateResponse, getOAuthResponse);
        if (orderCancelResponse != null) System.out.println(orderCancelResponse.toString());
        ////////////////////////////////////////
        DeleteOAuthResponse deleteOAuthResponse = deleteOAuthToken(getOAuthResponse);
        if (deleteOAuthResponse != null) System.out.println(deleteOAuthResponse.toString());
    }

    public static GetOAuthResponse getOAuthToken(final GetOAuthRequest getOAuthRequest) {
        GetOAuthResponse getOAuthResponse = null;
        try {
            getOAuthResponse = getOAuthClient.call(getOAuthRequest);
        } catch (HttpCommunicationException e) {
            System.out.println(e.toString());
        } catch (ResponseParsingException e) {
            System.out.println(e.toString());
        }
        return getOAuthResponse;
    }

    public static DeleteOAuthResponse deleteOAuthToken(final GetOAuthResponse getOAuthResponse) {
        DeleteOAuthResponse deleteOAuthResponse = null;
        try {
            deleteOAuthResponse = deleteOAuthClient.call(getOAuthResponse);
        } catch (HttpCommunicationException e) {
            System.out.println(e.toString());
        }
        return deleteOAuthResponse;
    }

    public static OrderCreateResponse callForCreateOrder(final JSONObject jsonobject,
                                                         final GetOAuthResponse getOAuthResponse) {
        OrderCreateResponse createOrderResponse = null;
        try {
            createOrderResponse = orderCreateClient.call(jsonobject, getOAuthResponse);
        } catch (HttpCommunicationException e) {
            System.out.println(e.toString());
        } catch (ResponseParsingException e) {
            System.out.println(e.toString());
        }
        return createOrderResponse;
    }

    public static OrderCancelResponse callForOrderCancellation(final Response response,
                                                               final GetOAuthResponse getOAuthResponse) {
        OrderCancelResponse orderCancelResponse = null;
        try {
            orderCancelResponse = orderCancelClient.call(response, getOAuthResponse);
        } catch (HttpCommunicationException e) {
            System.out.println(e.toString());
        } catch (ResponseParsingException e) {
            System.out.println(e.toString());
        }
        return orderCancelResponse;
    }

    public static OrderUpdateResponse callForOrderUpdate(final OrderUpdateRequest orderUpdateRequest,
                                                         final GetOAuthResponse getOAuthResponse) {
        OrderUpdateResponse orderUpdateResponse = null;
        try {
            orderUpdateResponse = orderUpdateClient.call(orderUpdateRequest, getOAuthResponse);
        } catch (HttpCommunicationException e) {
            System.out.println(e.toString());
        } catch (ResponseParsingException e) {
            System.out.println(e.toString());
        }
        return orderUpdateResponse;
    }

    private static void setUp() {

        final ApiHttpClient apiHttpClient = new ApiHttpClient(SERVER_HOST, SERVER_PORT, SERVER_SCHEMA);
        getOAuthClient = new GetOAuthClient(new ApiPostClient(
                apiHttpClient,
                new GetOAuthResponseParser()
        ));

        deleteOAuthClient = new DeleteOAuthClient(
                apiHttpClient,
                new DeleteOAuthResponseParser()
        );

        orderCreateClient = new OrderCreateClient(new ApiPostClient(
                apiHttpClient,
                new OrderCreateResponseParser()
        ));

        orderCancelClient = new OrderCancelClient(new ApiDeleteClient(
                apiHttpClient,
                new OrderCancelResponseParser()
        ));

        orderUpdateClient = new OrderUpdateClient(new ApiPutClient(
                apiHttpClient,
                new OrderUpdateResponseParser()
        ));
    }
}
