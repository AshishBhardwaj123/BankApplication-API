package com.banking.bankapplicationapi.controller;

import com.banking.bankapplicationapi.exception.BankDetailNotFound;
import com.banking.bankapplicationapi.model.BankRequest;
import com.banking.bankapplicationapi.model.BankTO;
import com.banking.bankapplicationapi.service.BankServiceV1;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@Slf4j
@RestController
@RequestMapping("api/v1/banks")

public class BankController {
    @Autowired
    private BankServiceV1 bankService;

    @GetMapping
   // @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<BankTO>> getBanks() {
        log.info("Inside the BankControllerV1.getBanks");
        List<BankTO> bankTos = null;

        try {
            bankTos = bankService.getAllBanks();
            log.info("List of banks, banks:{}", bankTos);
        } catch (BankDetailNotFound ex) {
            log.error("Bank details not found", ex);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex1) {
            log.error("Exception while getting the data for get all banks");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        log.info("End of BankControllerV1.getBanks");
        return new ResponseEntity<>(bankTos, HttpStatus.OK);
    }

    @GetMapping("/{code}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<BankTO> getBankById(@PathVariable("code") int bankCode) {
        log.info("Inside the BankControllerV1.getBankById");
        BankTO bankTO = null;

        try {
            bankTO = bankService.getBankByCode(bankCode);
            log.info("Bank details for the bank code, bank code:{}", bankCode);
        } catch (BankDetailNotFound ex1) {
            log.error("Bank details not found", ex1);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            log.error("Exception while getting the bank details by bank code", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        log.info("End of BankControllerV1.getBankById");
        return new ResponseEntity<>(bankTO, HttpStatus.OK);
    }

    @GetMapping("/BankByName")
    public ResponseEntity<BankTO> getBankByName(@RequestParam("BankByName") String bankName) {
        log.info("Inside the BankControllerV1.getBankByName");
        BankTO bankTO3 = null;

        try {
            bankTO3 = bankService.getBankByName(bankName);
            log.info("Bank details for the bank code, bank code:{}", bankName);
        } catch (BankDetailNotFound ex1) {
            log.error("Bank details not found", ex1);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            log.error("Exception while getting the bank details by bank code", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        log.info("End of BankControllerV1.getBankById");
        return new ResponseEntity<>(bankTO3, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BankTO> SaveBank(@RequestBody BankRequest bankRequest) {
        log.info("Inside the BankControllerV1.getBankByName");
        BankTO bankTO3;

        if(bankRequest == null)
        {
            log.info("Invalid REQUEST bankRequest:{}",bankRequest);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            bankTO3 = bankService.saveBank(bankRequest);
            log.info("Bank details for the bank code, bank code:{}", bankRequest);
        } catch (BankDetailNotFound ex1) {
            log.error("Bank details not found", ex1);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            log.error("Exception while getting the bank details by bank code", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        log.info("End of BankControllerV1.getBankById");
        return new ResponseEntity<>(bankTO3, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<BankTO> SaveAllBank(@RequestBody BankRequest saveBank) {
        log.info("Inside the BankControllerV1.getBankByName");
        BankTO bankTO3;

        if(saveBank == null)
        {
            log.info("Invalid REQUEST bankRequest:{}",saveBank);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
            bankTO3 = bankService.saveAllBank(saveBank);
            log.info("Bank details for the bank code, bank code:{}", saveBank);
        } catch (BankDetailNotFound ex1) {
            log.error("Bank details not found", ex1);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            log.error("Exception while getting the bank details by bank code", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        log.info("End of BankControllerV1.getBankById");
        return new ResponseEntity<>(bankTO3, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> DeleteBank(@RequestParam("Delete") int code) {
        log.info("Inside the BankControllerV1.getBankByName");
        String res = null;

        if(code <= 0)
        {
            log.info("Invalid REQUEST delteBankRequest:{}",code);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
              res = bankService.DeleteBank(code);
            log.info("Bank details for the bank code, bank code:{}", code);
        } catch (BankDetailNotFound ex1) {
            log.error("Bank details not found", ex1);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            log.error("Exception while getting the bank details by bank code", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        log.info("End of BankControllerV1.getBankById");
        return new ResponseEntity<String>(res, HttpStatus.OK);
    }
}