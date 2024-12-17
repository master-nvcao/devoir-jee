package com.my.devoir.miscroservicecommandes.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RefreshScope
public class ConfigTestController {

    @Value("${mes-config-ms.commandes-last}")
    private int commandesLast;

    @GetMapping("/config-last")
    public String getCommandesLast() {
        return "Property value: " + commandesLast;
    }

}
