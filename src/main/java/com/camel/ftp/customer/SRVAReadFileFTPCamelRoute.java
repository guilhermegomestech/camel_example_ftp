package com.camel.ftp.customer;

import org.apache.camel.builder.RouteBuilder;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class SRVAReadFileFTPCamelRoute extends RouteBuilder {

    private static final Logger LOGGER = LoggerFactory.getLogger(SRVAReadFileFTPCamelRoute.class);

    @Value("${ftp.srva.url-read-out}")
    private String ftpServerReadOutWithOptions;
    @Value("${ftp.srva.url-read-in}")
    private String ftpServerReadInWithOptions;


    @Value("${ftp.srva.path-read-in}")
    private String ftpReadIn;


    @Value("${ftp.activate-route}")
    private boolean activateRoute;


    @Override
    public void configure() throws Exception {
        if (activateRoute) {

            //TODO: PEGA DIRETORIO DO USUARIO NO PROPERTIES
//            String homeDir = System.getProperty("user.home");
//            homeDir = homeDir.replace("\\", "/");
//            System.out.println(homeDir);
//
            //TODO: DIRETORIO DE LEITURA DOS ARQUIVOS
            String diretorioLeitura = "file:".concat(ftpReadIn);
            from(diretorioLeitura).log("---- PROCESSING DIR ----")
                    //TODO: DIRETORIO DE ENVIO DOS ARQUIVOS
                    .to(ftpServerReadOutWithOptions).onCompletion()
                    //TODO: PROCESSO QUE REMOVE A PASTA .camel
                    .process(exchange -> {
                        File camelFolder = new File(ftpReadIn.concat("/.camel"));
                        if (camelFolder.exists()) {
                            FileUtils.deleteDirectory(camelFolder);
                        }
                    })
                    .end();
        }
    }
}
