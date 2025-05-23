package org.example.bootlog.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.example.bootlog.model.dto.PurchaseDTO;
import org.example.bootlog.model.entity.Purchase;
import org.example.bootlog.service.PurchaseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

//import java.util.logging.Logger;


@RestController
@Slf4j
@RequiredArgsConstructor
//@Log // 기본 로거
public class PurchaseController {

    //    private final Logger logger = Logger.getLogger(PurchaseController.class.getName());
//    private final Logger logger = LoggerFactory.getLogger(PurchaseController.class);
    private final PurchaseService purchaseService;

    @PostMapping
    public Purchase purchase(@RequestBody PurchaseDTO purchaseDTO) {
        log.debug("purchase 시작");
        System.out.println("purchase");
//        logger.info("purchase");
        log.info("purchase %s".formatted(purchaseDTO.name()));
        log.info("purchase {}", purchaseDTO.name());
        log.info("purchase {}", purchaseDTO);
        // verbose - debug - info - warn - error - critical
        // finest - fine - info - warning - severe
        log.debug("purchase 트랜잭션");
        try {
            purchaseService.purchase(purchaseDTO.name());
            throw new RuntimeException("purchase error");
        } catch (Exception e) {
            System.err.println(e.getMessage());
//            logger.severe(e.getMessage());
//            logger.error(e.getMessage());
            log.error(e.getMessage());
        }
        log.debug("purchase 종료");
        Purchase purchaseResponse = new Purchase(purchaseDTO.name());
        log.debug("{}", purchaseResponse);
        return purchaseResponse;
    }
}