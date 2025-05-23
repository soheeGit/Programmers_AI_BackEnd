package org.example.bootlog.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class PurchaseService {
    public void purchase(String name) {
        log.info("purchase info {}", name);
        log.debug("purchase debug {}", name);
    }
}