package com.jmontanez.challenge.bcp.common;

import java.text.DecimalFormat;

public class Constants {
    private Constants() { throw new IllegalStateException("Utility class");}
    public static final DecimalFormat decimalFormatD2 = new DecimalFormat("0.00");
    public static final String JWT_VALIDATION_FILTER_HEADER  ="Authorization";
    public static final String JWT_VALIDATION_FILTER_PREFIX ="Bearer ";

    public  enum SwaggerType {
        TITLE("swagger.title"),
        DESCRIPTION("swagger.description"),
        VERSION("swagger.version"),
        CONTACT_NAME("swagger.contact.name"),
        CONTACT_URL("swagger.contact.url"),
        CONTACT_EMAIL("swagger.contact.email");

        String value;

        SwaggerType(String value) {
            this.value = value;
        }

        public String getValue() {
            return this.value;
        }
    }
}
