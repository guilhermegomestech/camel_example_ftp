package com.camel.ftp.customer;

import org.apache.camel.Exchange;
import org.apache.camel.component.file.GenericFileMessage;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringWriter;

public class CamelUtil {

    public static SimpleFileRequest createSimpleFileRequest(Exchange exchange) throws IOException {
        final InputStream is = exchange.getIn().getBody(InputStream.class);
        StringWriter writer = new StringWriter();
        IOUtils.copy(is, writer, "ISO-8859-1");
        String theString = writer.toString();

        GenericFileMessage file = (GenericFileMessage) exchange.getIn();
        SimpleFileRequest simpleFileRequest = new SimpleFileRequest();
        simpleFileRequest.setFile(theString);
        simpleFileRequest.setFileName(file.getGenericFile().getFileName());
        return simpleFileRequest;
    }

}
