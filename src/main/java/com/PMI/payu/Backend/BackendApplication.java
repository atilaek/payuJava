package com.PMI.payu.Backend;

import com.PMI.payu.Backend.domain.alu.request.Order;
import com.PMI.payu.Backend.domain.alu.request.Product;
import com.PMI.payu.Backend.domain.alu.response.AluResponse;
import com.PMI.payu.Backend.domain.auth.AuthRequest;
import com.PMI.payu.Backend.domain.auth.AuthResponse;
import org.apache.http.NameValuePair;
import org.joda.money.Money;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import payu.basis.idn.IdnRequestParametersBuilder;
import payu.basis.idn.IdnResponseInterpreter;
import payu.basis.ios.IosRequestParametersBuilder;
import payu.basis.ios.IosResponseInterpreter;
import payu.basis.ipn.IpnHttpServer;
import payu.basis.ipn.IpnHttpServerBuilder;
import payu.basis.ipn.IpnRequestProcessor;
import payu.basis.irn.IrnRequestParametersBuilder;
import payu.basis.irn.IrnResponseInterpreter;
import payu.lib.alu.AluAuthenticationService;
import payu.lib.alu.AluClient;
import payu.lib.alu.AluResponseParser;
import payu.lib.auth.AuthResponseParser;
import payu.lib.common.authentication.AuthenticationService;
import payu.lib.common.authentication.InvalidSignatureException;
import payu.lib.common.authentication.SignatureCalculator;
import payu.lib.common.client.*;
import payu.lib.idn.IdnAuthenticationService;
import payu.lib.idn.IdnClient;
import payu.lib.idn.IdnResponseParser;
import payu.lib.ios.IosAuthenticationService;
import payu.lib.ios.IosClient;
import payu.lib.ios.IosResponseParser;
import payu.lib.irn.IrnAuthenticationService;
import payu.lib.irn.IrnClient;
import payu.lib.irn.IrnResponseParser;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;
import java.util.UUID;

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

	private static payu.lib.auth.AuthClient authClient;
	private static AluClient aluClient;
	private static IdnClient idnClient;
	private static IrnClient irnClient;
	private static IosClient iosClient;


	private static IosRequestParametersBuilder iosRequestParametersBuilder;
	private static IosResponseInterpreter iosResponseInterpreter;

	private static IdnRequestParametersBuilder idnRequestParametersBuilder;
	private static IdnResponseInterpreter idnResponseInterpreter;

	private static IrnRequestParametersBuilder irnRequestParametersBuilder;
	private static IrnResponseInterpreter irnResponseInterpreter;

	private static IpnHttpServer ipnHttpServer;
	private static IpnRequestProcessor ipnRequestProcessor;



	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);

		setUp();

		try {
////////////////////////////////////////////


			AuthRequest authReq = new AuthRequest("client_credentials",
					MERCHANT_CODE, MERCHANT_SECRET_KEY);

            AuthResponse authResponse = callAuth(authReq.getParameters());





            ////////////////////////////////////////


			Order myOrder = null;
			List<Product> products= new ArrayList<>();
			products.add(new Product("Wireless Mouse",
					15000,1));
			products.add(new Product("HDMI cable",
					6000,1));
			myOrder = new Order("123.123.123.123", MERCHANT_CODE,
					"Order description",
					Currency.getInstance("PLN"),
					products);
/*
			final List<NameValuePair> requestParams =
                    aluCommunicator.buildAluRequestParameters(myOrder, myProducts,
                            myBilling, myCard,
                            Optional.of(myDelivery), Optional.empty(),
							Optional.empty());
*/
			String myOrderReference = UUID.randomUUID().toString().substring(0, 17);


			//ALU automatic Live Update  -  redirected to payu platform’s payment page,
			setExpectedIpn(myOrderReference);
            AluResponse aluResponse = callAlu(myOrder.getJSONObject(), authResponse);
			aluResponse.toString();
			waitForIpn();
			callIos(myOrderReference);


			////////////////////////////////////////



			//ALU - Automatic Live Update (ALU) - place transactions directly in the PayU system
			//IPN - Instant Payment Notification (IPN) - data about a transaction that has been successfully authorized and approved.
			//IOS - Instant Request Status (IOS) - the status of an order.
			//IDN - Instant Delivery Notification (IDN) - automatic delivery confirmations
			//IRN - Instant Reverse/Refund Notification (IRN) -

/*
			String orderReference = UUID.randomUUID().toString().substring(0, 17);
			Money amount = Money.of(CurrencyUnit.of("TRY"), 196);
			String payuOrderReference = "";


			//ALU automatic Live Update  -  redirected to payu platform’s payment page,
			setExpectedIpn(orderReference);
			Map.Entry<String, Money> refNoAmountPair = callAlu(orderReference, amount);
			amount = refNoAmountPair.getValue();
			payuOrderReference = refNoAmountPair.getKey();
			waitForIpn();
			callIos(orderReference);


			//Instant Delivery Notification - Request Confirmation
			setExpectedIpn(orderReference);
			callIdn(payuOrderReference, amount);
			waitForIpn();
			callIos(orderReference);


			//Instant Refund Notification
			setExpectedIpn(orderReference);
			callIrn(payuOrderReference, amount);
			waitForIpn();
			callIos(orderReference);
*/
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			ipnHttpServer.stop();
		}
	}

	public static AuthResponse callAuth(final List<NameValuePair> authRequestParameters) throws CommunicationException, InvalidXmlResponseParsingException, InvalidSignatureException {
		System.out.println(authRequestParameters);
		final AuthResponse authResponse = authClient.call(authRequestParameters);
		return authResponse;
	}

    public static AluResponse callAlu(final JSONObject jsonobject,
                                      final AuthResponse authResponse) throws CommunicationException, InvalidXmlResponseParsingException, InvalidSignatureException {
        System.out.println(jsonobject);
        final AluResponse aluResponse = aluClient.call(jsonobject, authResponse);
        return aluResponse;
    }

    private static RuntimeException unsuccessfulResponseThrower(final String requestType, final List<NameValuePair> responseParameters) {
		String values = "";
		responseParameters.forEach(pair -> values.concat(pair.getName() + ":" + pair.getValue() + "\n"));
		throw new RuntimeException(requestType.toUpperCase() + " response ERROR!" + "\n" + values);
	}

	private static void setExpectedIpn(String orderReference) {
		ipnRequestProcessor.setExpectedIpn(orderReference);
	}

	private static void waitForIpn() {
		ipnRequestProcessor.waitForIpn();
	}

	private static void callIdn(String payuOrderReference, Money amount) throws CommunicationException, InvalidXmlResponseParsingException, InvalidSignatureException {

		final List<NameValuePair> idnRequestParameters = idnRequestParametersBuilder.build(payuOrderReference, amount);

		final List<NameValuePair> idnResponseParameters = idnClient.call(idnRequestParameters);

		idnResponseInterpreter.interpretResponseParameters(idnResponseParameters);
		if (!idnResponseInterpreter.isSuccess(idnResponseParameters)) {
            unsuccessfulResponseThrower("IDN" , idnResponseParameters);
		}
	}

	private static void callIrn(String payuOrderReference, Money amount) throws CommunicationException, InvalidXmlResponseParsingException, InvalidSignatureException {
		final List<NameValuePair> irnRequestParameters = irnRequestParametersBuilder.build(payuOrderReference, amount);

		final List<NameValuePair> irnResponseParameters = irnClient.call(irnRequestParameters);

		irnResponseInterpreter.interpretResponseParameters(irnResponseParameters);
		if (!irnResponseInterpreter.isSuccess(irnResponseParameters)) {
            unsuccessfulResponseThrower("IRN" , irnResponseParameters);
        }
	}

	private static void callIos(String orderReference) throws CommunicationException, InvalidXmlResponseParsingException, InvalidSignatureException {
		final List<NameValuePair> iosRequestParameters = iosRequestParametersBuilder.build(orderReference);

		final List<NameValuePair> iosResponseParameters = iosClient.call(iosRequestParameters);

		iosResponseInterpreter.interpretResponseParameters(iosResponseParameters);
		if (!iosResponseInterpreter.isSuccess(iosResponseParameters)) {
            unsuccessfulResponseThrower("IOS" , iosResponseParameters);
        }
	}

	private static void setUp() {

		final ApiHttpClient apiHttpClient = new ApiHttpClient(SERVER_HOST, SERVER_PORT, SERVER_SCHEMA);
		final AuthenticationService authenticationService = new AuthenticationService(
				new SignatureCalculator(),
				MERCHANT_SECRET_KEY
		);
		final XmlResponseParser xmlResponseParser = new XmlResponseParser();

		authClient = new payu.lib.auth.AuthClient(
				apiHttpClient,
				new AuthResponseParser()
		);

		aluClient = new AluClient(new ApiClient(
				apiHttpClient,
				new AluAuthenticationService(authenticationService),
				new AluResponseParser()
		));

		iosClient = new IosClient(new ApiClient(
				apiHttpClient,
				new IosAuthenticationService(authenticationService),
				new IosResponseParser(xmlResponseParser)
		));

		idnClient = new IdnClient(new ApiClient(
				apiHttpClient,
				new IdnAuthenticationService(authenticationService),
				new IdnResponseParser(xmlResponseParser)
		));

		irnClient = new IrnClient(new ApiClient(
				apiHttpClient,
				new IrnAuthenticationService(authenticationService),
				new IrnResponseParser(xmlResponseParser)
		));

		iosRequestParametersBuilder = new IosRequestParametersBuilder(MERCHANT_CODE);
		iosResponseInterpreter = new IosResponseInterpreter();

		idnRequestParametersBuilder = new IdnRequestParametersBuilder(MERCHANT_CODE);
		idnResponseInterpreter = new IdnResponseInterpreter();

		irnRequestParametersBuilder = new IrnRequestParametersBuilder(MERCHANT_CODE);
		irnResponseInterpreter = new IrnResponseInterpreter();

		ipnRequestProcessor = new IpnRequestProcessor();
		ipnHttpServer = IpnHttpServerBuilder.createServer(ipnRequestProcessor, authenticationService);
		ipnHttpServer.start();

	}
}
