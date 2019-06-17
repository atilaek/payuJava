package com.PMI.payu.Backend;

import org.apache.http.NameValuePair;

import java.util.List;

public class Utils {

    public static RuntimeException unsuccessfulResponseThrower(final String requestType, final String error,
                                                               final String errorDescription){
        throw new RuntimeException(requestType.toUpperCase() +" response ERROR!" + "\n"
                + "Error:" + error + ", Error_Description:" + errorDescription );
    }

}
