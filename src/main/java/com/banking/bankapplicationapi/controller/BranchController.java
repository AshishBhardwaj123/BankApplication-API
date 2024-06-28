package com.banking.bankapplicationapi.controller;

import com.banking.bankapplicationapi.exception.BankDetailNotFound;
import com.banking.bankapplicationapi.exception.BranchDetailNotFound;
import com.banking.bankapplicationapi.model.BankRequest;
import com.banking.bankapplicationapi.model.BankTO;
import com.banking.bankapplicationapi.model.BranchRequest;
import com.banking.bankapplicationapi.model.BranchTO;
import com.banking.bankapplicationapi.service.BankServiceV1;
import com.banking.bankapplicationapi.service.BranchService;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/branches")
public class BranchController {
    @Autowired
    private BranchService branchService;

    @GetMapping
    public ResponseEntity<List<BranchTO>> getBranch() {
        log.info("Inside the BankControllerV1.getBanks");
        List<BranchTO> branchTos = null;

        try
        {
            branchTos = branchService.getAllBranch();
            log.info("List of branch, branches:{}", branchTos);
        }
        catch (BranchDetailNotFound br)
        {
            log.error("Branch details not found", br);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception br1)
        {
            log.error("Exception while getting the data for get all branch");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        log.info("End of BankControllerV1.getBanks");
        return new ResponseEntity<>(branchTos, HttpStatus.OK);
    }

    @GetMapping("/{BranchById}")
    public ResponseEntity<BranchTO> getBranchById(@PathVariable("BranchById") int branchId){
        log.info("Inside the BankControllerV1.getBranchById");
        BranchTO branchTO2 = null;

        try {
            branchTO2 = branchService.getBranchById(branchId);
            log.info("Branch details for the branch Id, branch Id:{}", branchTO2);
        } catch (BranchDetailNotFound bd1){
            log.error("Branch details not found", bd1);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception bd2){
            log.error("Exception while getting the branch details by branch Id", bd2);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        log.info("End of BankControllerV1.getBankById");
        return new ResponseEntity<>(branchTO2, HttpStatus.OK);
    }

    @GetMapping("/BranchByName")
    public ResponseEntity<BranchTO> getBranchByName(@RequestParam("BranchByName") String branchName){
        log.info("Inside the BankControllerV1.getBranchByName");
        BranchTO branchTO3 = null;

        try {
            branchTO3 = branchService.getBranchByName(branchName);
            log.info("Branch details for the branch Id, branch Id:{}", branchTO3);
        } catch (BranchDetailNotFound bd1){
            log.error("Branch details not found", bd1);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }catch (Exception bd2){
            log.error("Exception while getting the branch details by branch Id", bd2);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        log.info("End of BankControllerV1.getBankById");
        return new ResponseEntity<>(branchTO3, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<BranchTO> SaveBranch(@RequestBody BranchRequest branchRequest) {
        log.info("Inside the BankControllerV1.getBankByName");
        BranchTO branchTO3 ;

        if(branchRequest == null)
        {
            log.info("Invalid REQUEST bankRequest:{}",branchRequest);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try
        {
            branchTO3 = branchService.saveBranch(branchRequest);
            log.info("Bank details for the bank code, bank code:{}", branchRequest);
        } catch (BranchDetailNotFound ex1) {
            log.error("Bank details not found", ex1);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            log.error("Exception while getting the bank details by bank code", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        log.info("End of BankControllerV1.getBankById");
        return new ResponseEntity<>(branchTO3, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<BranchTO> SaveAllBranch(@RequestBody BranchRequest branchRequest) {
        log.info("Inside the BankControllerV1.getBankByName");
        BranchTO branchTO3 ;

        if(branchRequest == null)
        {
            log.info("Invalid REQUEST bankRequest:{}",branchRequest);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try
        {
            branchTO3 = branchService.saveAllBranch(branchRequest);
            log.info("Bank details for the bank code, bank code:{}", branchRequest);
        } catch (BranchDetailNotFound ex1) {
            log.error("Bank details not found", ex1);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ex) {
            log.error("Exception while getting the bank details by bank code", ex);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        log.info("End of BankControllerV1.getBankById");
        return new ResponseEntity<>(branchTO3, HttpStatus.OK);
    }

    @DeleteMapping
    public ResponseEntity<String> DeleteBranch(@RequestParam("Delete") int id) {
        log.info("Inside the BankControllerV1.getBankByName");
        String res = null;

        if(id <= 0)
        {
            log.info("Invalid REQUEST delteBankRequest:{}",id);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try {
              res = branchService.DeleteBranch(id);
            log.info("Bank details for the bank code, bank code:{}", id);
        } catch (BranchDetailNotFound ex1) {
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


