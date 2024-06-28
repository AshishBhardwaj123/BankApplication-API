package com.banking.bankapplicationapi.service;

import com.banking.bankapplicationapi.entity.Bank;
import com.banking.bankapplicationapi.entity.Branch;
import com.banking.bankapplicationapi.exception.BankDetailNotFound;
import com.banking.bankapplicationapi.model.BankRequest;
import com.banking.bankapplicationapi.model.BankTO;
import com.banking.bankapplicationapi.model.BranchTO;
import com.banking.bankapplicationapi.repository.BankRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

//import static java.util.stream.Nodes.collect;

@Service
@Slf4j
public class BankServiceImpl implements BankServiceV1 {

    @Autowired
    private BankRepository bankrepository;

    @Override
    public List<BankTO> getAllBanks() throws BankDetailNotFound {
        log.info("Inside the BankServiceImpl");

        List<Bank> banks = bankrepository.findAll();
        log.info("list of Bank,Banks:{}", banks);

        if (CollectionUtils.isEmpty(banks)) {
            log.info("Bank Details not found");
            throw new BankDetailNotFound("Bank Details not found");
        }
        List<BankTO> bankTo = banks.stream().map(bank -> {
            BankTO dao = new BankTO();
            dao.setBankName(bank.getBankName());
            dao.setBankAddress(bank.getBankAddress());

            Set<Branch> branches = bank.getBranchSet();
            if (!CollectionUtils.isEmpty(branches)) {
                List<BranchTO> branchTO = branches.stream().map(branch -> {
                    BranchTO bdao = new BranchTO();
                    bdao.setBranchName(branch.getBranchName());
                    bdao.setBranchAddress(branch.getBranchAddress());
                    return bdao;
                }).collect(Collectors.toList());
                dao.setBranches(branchTO);
            }
            return dao;
        }).collect(Collectors.toList());
        return bankTo;
    }

    @Override
    public BankTO getBankByCode(int bankCode) throws BankDetailNotFound {

        log.info("Inside the BankServiceImplV1.getBankByCode, bankCode:{}", bankCode);

        if (bankCode <= 0) {
            log.info("Invalid bank code");
            throw new BankDetailNotFound("Invalid Bank Code");
        }

        Optional<Bank> bank = bankrepository.findById(bankCode);
        log.info("Bank details for the bank code, bankCode:{}", bank.get());

        if (!bank.isPresent()) {
            log.info("Bank details not found for the bank code:{}", bankCode);
            throw new BankDetailNotFound("Bank details not found");
        }
        Bank bank1 = bank.get();

        BankTO bankTO = new BankTO();
        bankTO.setBankName(bank1.getBankName());
        bankTO.setBankAddress(bank1.getBankAddress());

        Set<Branch> branches = bank1.getBranchSet();
        if (!CollectionUtils.isEmpty(branches)) {
            List<BranchTO> branchTOS = branches.stream().map(branch -> {
                BranchTO branchTO = new BranchTO();
                branchTO.setBranchName(branch.getBranchName());
                branchTO.setBranchAddress(branch.getBranchAddress());
                return branchTO;
            }).collect(Collectors.toList());
            bankTO.setBranches(branchTOS);
        }

        log.info("End of BankServiceImplV1.getBankByCode");
        return bankTO;
    }

    @Override
    public BankTO getBankByName(String bankName) throws BankDetailNotFound {
        log.info("Inside the BankServiceImplV1.getBankByCode, bankCode:{}", bankName);

        Optional<Bank> bank3 = bankrepository.findByBankName(bankName);
        log.info("Bank details for the bank code, bankCode:{}", bank3.get());

        if (!bank3.isPresent()) {
            log.info("Bank details not found for the bank code:{}", bankName);
            throw new BankDetailNotFound("Bank details not found");
        }
        Bank bank1 = bank3.get();

        BankTO bankTO3 = new BankTO();
        bankTO3.setBankName(bank1.getBankName());
        bankTO3.setBankAddress(bank1.getBankAddress());

        Set<Branch> branches = bank1.getBranchSet();
        if (!CollectionUtils.isEmpty(branches)) {
            List<BranchTO> branchTOS = branches.stream().map(branch -> {
                BranchTO branchTO = new BranchTO();
                branchTO.setBranchName(branch.getBranchName());
                branchTO.setBranchAddress(branch.getBranchAddress());
                return branchTO;
            }).collect(Collectors.toList());
            bankTO3.setBranches(branchTOS);
        }

        log.info("End of BankServiceImplV1.getBankByCode");
        return bankTO3;
    }

    @Override
    public BankTO saveBank(BankRequest bankRequest) throws BankDetailNotFound {

        log.info("Inside the bank request , bankRequest:{}", bankRequest);

        Bank bankResponse = new Bank();
        bankResponse.setBankName(bankRequest.getBankName());
        bankResponse.setBankAddress(bankRequest.getBankAddress());

        Bank bank = bankrepository.save(bankResponse);

        if (bank == null) {
            log.info("Bank Details Not Found");
            throw new BankDetailNotFound();
        }
        BankTO bankTO3 = new BankTO();
        bankTO3.setBankName(bank.getBankName());
        bankTO3.setBankAddress(bank.getBankAddress());

        Set<Branch> branches = bank.getBranchSet();
        if (!CollectionUtils.isEmpty(branches)) {
            List<BranchTO> branchTOS = branches.stream().map(branch -> {
                BranchTO branchTO = new BranchTO();
                branchTO.setBranchName(branch.getBranchName());
                branchTO.setBranchAddress(branch.getBranchAddress());
                return branchTO;
            }).collect(Collectors.toList());
            bankTO3.setBranches(branchTOS);
        }

        log.info("End of BankServiceImplV1.getBankByCode");
        return bankTO3;
    }

    @Override
    public BankTO saveAllBank(BankRequest saveBank) throws BankDetailNotFound {

        log.info("Inside the bank request , bankRequest:{}", saveBank);

        Bank bankResponse2 = new Bank();
        bankResponse2.setBankCode(saveBank.getBankCode());
        bankResponse2.setBankName(saveBank.getBankName());
        bankResponse2.setBankAddress(saveBank.getBankAddress());

        Bank bank2 = bankrepository.save(bankResponse2);

        if (bank2 == null) {
            log.info("Bank Details Not Found");
            throw new BankDetailNotFound();
        }
        BankTO bankTO3 = new BankTO();
        bankTO3.setBankName(bank2.getBankName());
        bankTO3.setBankAddress(bank2.getBankAddress());

        Set<Branch> branches = bank2.getBranchSet();
        if (!CollectionUtils.isEmpty(branches)) {
            List<BranchTO> branchTOS = branches.stream().map(branch -> {
                BranchTO branchTO = new BranchTO();
                branchTO.setBranchName(branch.getBranchName());
                branchTO.setBranchAddress(branch.getBranchAddress());
                return branchTO;
            }).collect(Collectors.toList());
            bankTO3.setBranches(branchTOS);
        }

        log.info("End of BankServiceImplV1.getBankByCode");
        return bankTO3;
    }

    @Override
    public String DeleteBank(int deleteBank) throws BankDetailNotFound {
        log.info("Inside the bank request , bankRequest:{}", deleteBank);

        if (deleteBank <= 0) {
            log.info("Invalid Bank Code", deleteBank);
            throw new BankDetailNotFound("Invalid Bank Code");
        }

        bankrepository.deleteById(deleteBank);
        return "Bank Details has been Deleted Successfully";
    }
}


