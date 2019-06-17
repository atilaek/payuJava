package payu.basis.ipn;

import payu.lib.common.authentication.AuthenticationService;
import payu.lib.common.authentication.SignatureCalculator;
import payu.lib.common.server.ApiHttpHandler;
import payu.lib.ipn.IpnAuthenticationService;
import payu.lib.ipn.IpnResponseBuilder;

public class IpnHttpServerBuilder {

    private static final int SERVER_PORT = 8000;
    private static final String IPN_ENDPOINT = "/ipn";

    public static IpnHttpServer createServer(IpnRequestProcessor ipnRequestProcessor, AuthenticationService authenticationService) {

        IpnAuthenticationService ipnAuthenticationService = new IpnAuthenticationService(authenticationService);
        ApiHttpHandler apiHttpHandler = new ApiHttpHandler(
                ipnRequestProcessor,
                new IpnResponseBuilder(ipnAuthenticationService),
                ipnAuthenticationService
        );
        return new IpnHttpServer(IPN_ENDPOINT, SERVER_PORT, apiHttpHandler);
    }

    public static void main(String[] args) {
        IpnHttpServer httpServer = IpnHttpServerBuilder.createServer(
                new IpnRequestProcessor(),
                new AuthenticationService(new SignatureCalculator())
        );
        httpServer.start();
    }
}
