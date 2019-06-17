package payu.lib.ios;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.w3c.dom.NodeList;
import payu.lib.common.client.ResponseParser;
import payu.lib.common.client.InvalidXmlResponseParsingException;
import payu.lib.common.client.XmlResponseParser;

import java.util.List;

public class IosResponseParser implements ResponseParser {

    private final XmlResponseParser xmlResponseParser;

    public IosResponseParser(XmlResponseParser xmlResponseParser) {
        this.xmlResponseParser = xmlResponseParser;
    }

    public List<NameValuePair> parseResponse(final HttpResponse httpResponse) throws InvalidXmlResponseParsingException {

        final NodeList firstLevelChildren = xmlResponseParser.getNodeList(httpResponse, "Request");

        return xmlResponseParser.getParametersFromNodeList(firstLevelChildren);
    }
}
