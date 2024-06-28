package com.banking.bankapplicationapi.controller;

import com.banking.bankapplicationapi.exception.LoanDetailNotFound;
import com.banking.bankapplicationapi.model.LoanRequest;
import com.banking.bankapplicationapi.model.LoanTO;
import com.banking.bankapplicationapi.service.LoanService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("api/v1/loans")
public class LoanController {

    @Autowired
    public LoanService loanService;
    @GetMapping
    public ResponseEntity<List<LoanTO>> getLoans() {
        log.info("Inside the LoanControllerV1.getLoans");
        List<LoanTO> loanTos = null;

        try {
            loanTos = loanService.getAllLoans();
            log.info("List of Loan, Loanes:{}", loanTos);
        } catch (LoanDetailNotFound ln) {
            log.error("Loan details not found", ln);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ln1) {
            log.error("Exception while getting the data for get all Loan");
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        log.info("End of LoanControllerV1.getLoan");
        return new ResponseEntity<>(loanTos, HttpStatus.OK);
    }

    @GetMapping("/{Id}")
    public ResponseEntity<LoanTO> getLoanById(@PathVariable("Id") int loanId) {
        log.info("Inside the LoanController.getLoanById");

        if (loanId <= 0) {
            log.info("Invalid Account Number, accountNumber:{}", loanId);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        LoanTO loanTO4 ;
        try {
            loanTO4 = loanService.getLoanById(loanId);
            log.info("Loan details for the Loan Id, Loan Id:{}", loanId);

            if (loanTO4 == null) {
                log.info("No data found for the account number, accountNo:{}", loanId);
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
        } catch (Exception ln2) {
            log.error("Exception while getting the Loan details by Loan Id", ln2);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        log.info("End of LoanControllerV1.getLoanById");
        return new ResponseEntity<>(loanTO4, HttpStatus.OK);
    }

    @GetMapping("/byType")
    public ResponseEntity<LoanTO> getLoanByType(@RequestParam("ByType") String loanType) {
        log.info("Inside the LoanControllerV1.getLoanByName");
        LoanTO loanTO = null;

        try {
            loanTO = loanService.getLoanByType(loanType);
            log.info("Loan details for the Loan Id, Loan Id:{}", loanType);
        } catch (LoanDetailNotFound ln1) {
            log.error("Loan details not found", ln1);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception ln2) {
            log.error("Exception while getting the Loan details by Loan Id", ln2);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        log.info("End of LoanControllerV1.getLoanById");
        return new ResponseEntity<>(loanTO, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<LoanTO> saveLoans(@RequestBody LoanRequest loanRequest)
    {
        log.info("Inside the LoanController.saveLoans");
        LoanTO loanTO3 ;

        if(loanRequest == null)
        {
            log.info("Invalid REQUEST loanRequest:{}",loanRequest);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try
        {
            loanTO3 = loanService.saveLoans(loanRequest);
            log.info("Loan details for the Loan , Loan Id:{}", loanRequest);
        }
        catch (LoanDetailNotFound ln1)
        {
            log.error("Loan details not found", ln1);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception ln2)
        {
            log.error("Exception while getting the Loan details by Loan Id", ln2);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        log.info("End of LoanController.saveLoans");
        return new ResponseEntity<>(loanTO3, HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<LoanTO> saveAllLoans(@RequestBody LoanRequest loanRequest)
    {
        log.info("Inside the LoanController.saveLoans");
        LoanTO loanTO3 ;

        if(loanRequest == null)
        {
            log.info("Invalid REQUEST bankRequest:{}",loanRequest);
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        try
        {
            loanTO3 = loanService.saveAllLoans(loanRequest);
            log.info("Loan details for the Loan , Loan Id:{}", loanRequest);
        }
        catch (LoanDetailNotFound ln1)
        {
            log.error("Loan details not found", ln1);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        catch (Exception ln2)
        {
            log.error("Exception while getting the Loan details by Loan Id", ln2);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        log.info("End of LoanController.saveLoans");
        return new ResponseEntity<>(loanTO3, HttpStatus.OK);
    }

}
