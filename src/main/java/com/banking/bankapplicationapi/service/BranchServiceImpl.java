package com.banking.bankapplicationapi.service;

import com.banking.bankapplicationapi.entity.Bank;
import com.banking.bankapplicationapi.entity.Branch;
import com.banking.bankapplicationapi.exception.BankDetailNotFound;
import com.banking.bankapplicationapi.exception.BranchDetailNotFound;
import com.banking.bankapplicationapi.model.BankTO;
import com.banking.bankapplicationapi.model.BranchRequest;
import com.banking.bankapplicationapi.model.BranchTO;
import com.banking.bankapplicationapi.repository.BranchRepository;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@Slf4j
public class BranchServiceImpl implements BranchService {

    @Autowired
    private BranchRepository branchRepository;

    @Override
    public List<BranchTO> getAllBranch() throws BranchDetailNotFound {
        log.info("Inside the BranchServiceImpl");

        List<Branch> bond = branchRepository.findAll();
        log.info("list of Branch,Branches:{}", bond);

        if (CollectionUtils.isEmpty(bond)) {
            log.info("Bank Details not found");
            throw new BranchDetailNotFound("Bank Details not found");
        }
        List<BranchTO> branchTo = bond.stream().map(brand -> {
            BranchTO dao = new BranchTO();
            dao.setBranchName(brand.getBranchName());
            dao.setBranchAddress(brand.getBranchAddress());
            return dao;
        }).collect(Collectors.toList());
        return branchTo;
    }

    @Override
    public BranchTO getBranchById(int branchId) throws BranchDetailNotFound {

        log.info("Inside the BranchServiceImplV1.getBranchById, branchId:{}", branchId);

        Optional<Branch> branch2 = branchRepository.findById(branchId);
        log.info("Branch details for the branch Id branchId:{}", branch2.get());

        if (!branch2.isPresent()) {
            log.info("Branch details not found for the branch Id:{}", branchId);
            throw new BranchDetailNotFound("Branch details not found");
        }
        Branch ban1 = branch2.get();

        BranchTO bankTO = new BranchTO();
        bankTO.setBranchName(ban1.getBranchName());
        bankTO.setBranchAddress(ban1.getBranchAddress());

        log.info("End of BranchServiceImplV1.getBranchById");
        return bankTO;
    }

    @Override
    public BranchTO getBranchByName(String branchName) throws BranchDetailNotFound {
        return null;
    }

    @Override
    public BranchTO saveBranch(BranchRequest branchRequest) throws BranchDetailNotFound
    {
        log.info("Inside the branch request , branchRequest:{}", branchRequest);
        //Bank ban = new Bank();
        //ban.setBankCode();


        Branch branchResponse = new Branch();
        branchResponse.setBranchId(branchRequest.getBranchId());
        branchResponse.setBranchName(branchRequest.getBranchName());
        branchResponse.setBranchAddress(branchRequest.getBranchAddress());
        Bank ban = new Bank();
        ban.setBankCode(branchRequest.getCodeBank());
        branchResponse.setBank(ban);


        Branch newBranch = branchRepository.save(branchResponse);

        if (newBranch == null) {
            log.info("Branch Details Not Found");
            throw new BranchDetailNotFound();
        }
        BranchTO branchTO3 = new BranchTO();
        branchTO3.setBranchName(newBranch.getBranchName());
        branchTO3.setBranchAddress(newBranch.getBranchAddress());

        log.info("End of BranchServiceImplV1.saveBranch");
        return branchTO3;
    }

    @Override
    public BranchTO saveAllBranch(BranchRequest branchRequest) throws BranchDetailNotFound {
        log.info("Inside the branch request , branchRequest:{}", branchRequest);
        //Bank ban = new Bank();
        //ban.setBankCode();


        Branch branchResponse = new Branch();
        branchResponse.setBranchId(branchRequest.getBranchId());
        branchResponse.setBranchName(branchRequest.getBranchName());
        branchResponse.setBranchAddress(branchRequest.getBranchAddress());
        Bank ban = new Bank();
        ban.setBankCode(branchRequest.getCodeBank());
        branchResponse.setBank(ban);


        Branch newBranch = branchRepository.save(branchResponse);

        if (newBranch == null) {
            log.info("Branch Details Not Found");
            throw new BranchDetailNotFound();
        }
        BranchTO branchTO3 = new BranchTO();
        branchTO3.setBranchName(newBranch.getBranchName());
        branchTO3.setBranchAddress(newBranch.getBranchAddress());

        log.info("End of BranchServiceImplV1.saveBranch");
        return branchTO3;
    }

    @Override
    public String DeleteBranch(int branchId) throws BranchDetailNotFound {
        log.info("Inside the bank request , bankRequest:{}", branchId);

        String string = null;

        if (branchId <= 0) {
            log.info("Invalid Branch Id", branchId);
            throw new BranchDetailNotFound("Invalid Bank Code");
        }

        branchRepository.deleteById(branchId);
        return "Bank Details has been Deleted Successfully";
    }
}
