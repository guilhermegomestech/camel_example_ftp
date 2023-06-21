package com.camel.ftp.customer;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class SRVASchedulerService {


    @Scheduled(cron = "0 0/1 * * * *")
    public void executarScrpit() throws IOException {

        //TODO: EXECUTA UMA BAT DE ACORDO COM O CRON DEFINIDO
//        String command = "cmd /c start D:/atc/suporte/servidor/wildfly-10.1.0.Final-orion/bin/standalone.bat";
//        Runtime.getRuntime().exec(command);
    }
}
