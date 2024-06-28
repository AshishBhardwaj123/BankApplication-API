package com.banking.bankapplicationapi.service;

import com.banking.bankapplicationapi.entity.Bank;
import com.banking.bankapplicationapi.entity.Branch;
import com.banking.bankapplicationapi.exception.BankDetailNotFound;
import com.banking.bankapplicationapi.model.BankRequest;
import com.banking.bankapplicationapi.model.BankTO;
import com.banking.bankapplicationapi.model.BranchRequest;
import com.banking.bankapplicationapi.model.BranchTO;
import com.banking.bankapplicationapi.repository.BankRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class BankServiceImplTest {

    @Mock
    private BankRepository bankRepository;

    @InjectMocks
    private BankServiceImpl bankService;

    @Test // Test for returning input of getAllBanks
    public void testGetAllBanks() throws BankDetailNotFound {

        List<Bank> banks = new ArrayList<>();
        Bank bank = new Bank();
        bank.setBankName("HDFC");
        bank.setBankAddress("SitaBuldi");
        banks.add(bank);

        when(bankRepository.findAll()).thenReturn(banks);

        List<BankTO> bankTOS = bankService.getAllBanks();
        assertEquals(1, bankTOS.size());
    }

    @Test(expected = BankDetailNotFound.class) // Test for checking null for null
    public void testGetAllBanksEmptyBank() throws BankDetailNotFound {

        List<Bank> banks = null;

        when(bankRepository.findAll()).thenReturn(banks);

        List<BankTO> bankTOS = bankService.getAllBanks();
        assertNull(bankTOS);
    }

    @Test // TO cover the rest of impl method of getAllBanks
    public void testGetAllBanksWithBranch() throws BankDetailNotFound {

        List<Bank> banks = new ArrayList<>();
        Bank bank = new Bank();
        bank.setBankName("HDFC");
        bank.setBankAddress("SitaBuldi");
        banks.add(bank);

        Set<Branch> branchList = new HashSet<>();
        Branch branch = new Branch();
        branch.setBranchId(1);
        branch.setBranchName("Gittikhadan");
        branch.setBranchAddress("Gittikhadan, Nagpur");
        branchList.add(branch);
        bank.setBranchSet(branchList);

        when(bankRepository.findAll()).thenReturn(banks);

        List<BankTO> bankTOS = bankService.getAllBanks();
        assertEquals(1, bankTOS.size());
    }

    @Test // Test for returning input of getBankById
    public void testGetBankById() throws BankDetailNotFound {

        Bank bank = new Bank();
        bank.setBankCode(34);
        bank.setBankName("HDFC");
        bank.setBankAddress("SitaBuldi");
        BankTO bank1 = new BankTO();
        bank1.setBankName(bank.getBankName());
        bank1.setBankAddress(bank.getBankAddress());

        when(bankRepository.findById(anyInt())).thenReturn(Optional.of(bank));

        BankTO bankTOS = bankService.getBankByCode(34);
        assertEquals(bank1.getBankName(), bankTOS.getBankName());
    }

    @Test
    public void testSaveBank() throws BankDetailNotFound
    {
       //List<Bank> bankList = new ArrayList<>();
       Bank bank2 = new Bank();
       bank2.setBankCode(108);
       bank2.setBankName("IndusInd");
       bank2.setBankAddress("Gorewada");

        Set<Branch> branchList= new HashSet<>();
        Branch branch= new Branch();
        branch.setBranchId(1);
        branch.setBranchName("Gittikhadan");
        branch.setBranchAddress("Gittikhadan, Nagpur");
        branchList.add(branch);
        bank2.setBranchSet(branchList);

        BankRequest bankRequest= new BankRequest();
        bankRequest.setBankCode(108);
        bankRequest.setBankName("IndusInd");
        bankRequest.setBankAddress("Gorewada");

        Set<BranchRequest> branchRequests= new HashSet<>();
        BranchRequest branchRequest= new BranchRequest();
        branchRequest.setBranchName("Gittikhadan");
        branchRequest.setBranchAddress("Gittikhadan, Nagpur");
        branchRequests.add(branchRequest);
        //bankRequest.;

        when(bankRepository.save(any())).thenReturn(bank2);
        BankTO bankTO= bankService.saveBank(bankRequest);
        assertEquals("IndusInd",bankTO.getBankName());
    }
}



