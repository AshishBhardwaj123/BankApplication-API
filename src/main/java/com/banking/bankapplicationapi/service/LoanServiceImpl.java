package com.banking.bankapplicationapi.service;


import com.banking.bankapplicationapi.entity.Branch;
import com.banking.bankapplicationapi.entity.Loan;
import com.banking.bankapplicationapi.exception.LoanDetailNotFound;
import com.banking.bankapplicationapi.model.LoanRequest;
import com.banking.bankapplicationapi.model.LoanTO;
import com.banking.bankapplicationapi.repository.LoanRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class LoanServiceImpl implements LoanService {
    public LoanRepository loanRepository;

    @Override
    public List<LoanTO> getAllLoans() throws LoanDetailNotFound {

        log.info("Inside the LoanServiceImpl");

        List<Loan> loan = loanRepository.findAll();
        log.info("list of Loan,Loans:{}", loan);

        if (CollectionUtils.isEmpty(loan)) {
            log.info("Loan Details not found");
            throw new LoanDetailNotFound("Loan Details not found");
        }
        List<LoanTO> loanTo = loan.stream().map(ln -> {
            LoanTO lnTO = new LoanTO();
            lnTO.setLoanId(ln.getLoanId());
            lnTO.setLoanType(ln.getLoanType());
            return lnTO;
        }).collect(Collectors.toList());
        return loanTo;
    }

    @Override
    public LoanTO getLoanById(int loanId) throws LoanDetailNotFound {

        log.info("Inside the LoanServiceImplV1.getLoanById, LoanId:{}", loanId);

        Optional<Loan> loan2 = loanRepository.findById(loanId);
        log.info("Branch details for the branch Id branchId:{}", loan2.get());

        if (!loan2.isPresent()) {
            log.info("Branch details not found for the branch Id:{}", loanId);
            throw new LoanDetailNotFound("Loan details not found");
        }
        Loan loan1 = loan2.get();

        LoanTO loanTO = new LoanTO();
        loanTO.setLoanId(loan1.getLoanId());
        loanTO.setLoanType(loan1.getLoanType());

        log.info("End of LoanServiceImplV1.getLoanById");
        return loanTO;
    }

    @Override
    public LoanTO getLoanByType(String loanType) throws LoanDetailNotFound {
        log.info("Inside the LoanServiceImplV1.getLoanById, LoanId:{}", loanType);

        Optional<Loan> loan2 = loanRepository.findByLoanType(loanType);
        log.info("Branch details for the branch Id branchId:{}", loan2.get());

        if (!loan2.isPresent()) {
            log.info("Branch details not found for the branch Id:{}", loanType);
            throw new LoanDetailNotFound("Loan details not found");
        }
        Loan loan1 = loan2.get();

        LoanTO loanTO = new LoanTO();
        loanTO.setLoanId(loan1.getLoanId());
        loanTO.setLoanType(loan1.getLoanType());

        log.info("End of LoanServiceImplV1.getLoanById");
        return loanTO;
    }

    @Override
    public LoanTO saveLoans(LoanRequest loanRequest) throws LoanDetailNotFound {

        log.info("Inside the save Loans ,SaveLoans:{} ", loanRequest);

        Loan loanResponse = new Loan();

        loanResponse.setLoanType(loanRequest.getLoanType());
        loanResponse.setAmount(loanRequest.getAmount());

        Branch branch3 = new Branch();
        branch3.setBranchId(loanRequest.getIdBranch());
        loanResponse.setBan(branch3);

        Loan newLoan = loanRepository.save(loanResponse);

        if (newLoan == null) {
            log.info("Loan Details not found");
            throw new LoanDetailNotFound();
        }

        LoanTO loanTo3 = new LoanTO();
        loanTo3.setLoanType(newLoan.getLoanType());
        loanTo3.setAmount(newLoan.getAmount());

        log.info("End of LoanServiceImpl.saveLoans");
        return loanTo3;
    }

    @Override
    public LoanTO saveAllLoans(LoanRequest loanRequest) throws LoanDetailNotFound {
        log.info("Inside the save Loans ,SaveLoans:{} ", loanRequest);

        Loan loanResponse = new Loan();
        loanResponse.setLoanId(loanRequest.getLoanId());
        loanResponse.setLoanType(loanRequest.getLoanType());
        loanResponse.setAmount(loanRequest.getAmount());

        Branch branch3 = new Branch();
        branch3.setBranchId(loanRequest.getIdBranch());
        loanResponse.setBan(branch3);

        Loan newLoan = loanRepository.save(loanResponse);

        if (newLoan == null) {
            log.info("Loan Details not found");
            throw new LoanDetailNotFound();
        }

        LoanTO loanTo3 = new LoanTO();
        loanTo3.setLoanType(newLoan.getLoanType());
        loanTo3.setAmount(newLoan.getAmount());

        log.info("End of LoanServiceImpl.saveLoans");
        return loanTo3;
    }

    @Override
    public String DeleteLoan(int loanId) throws LoanDetailNotFound {
        log.info("Inside the bank request , bankRequest:{}", loanId);

        String loanMessage = null;

        if (loanId <= 0) {
            log.info("Invalid Bank Code", loanId);
            throw new LoanDetailNotFound("Invalid Bank Code");
        }

        loanRepository.deleteById(loanId);
        return "Bank Details has been Deleted Successfully";
    }
}

