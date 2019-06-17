package com.PMI.payu.Backend.domain.alu.request;

public enum Language {
    Polish("pl"),
    English("en"),
    Czech("cs"),
    Bulgarian("bg"),
    Danish("da"),
    German("de"),
    Greek("el"),
    Spanish("es"),
    Estonian("et"),
    Finnish("fi"),
    French("fr"),
    Croatian("hr"),
    Hungarian("hu"),
    Italian("it"),
    Lithuanian("lt"),
    Latvian("lv"),
    Portuguese("pt"),
    Romanian("ro"),
    Russian("ru"),
    Slovak("sk"),
    Slovenian("sl"),
    Serbian("sr"),
    Swedish("sv"),
    Turkish("tr"),
    Ukrainian("uk");

    private String languageCode;

    Language(final String languageCode) {
        this.languageCode = languageCode;

    }
}
