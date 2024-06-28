package com.banking.bankapplicationapi.controller;

import com.banking.bankapplicationapi.exception.BankDetailNotFound;
import com.banking.bankapplicationapi.model.BankTO;
import com.banking.bankapplicationapi.model.BranchTO;
import com.banking.bankapplicationapi.service.BankServiceV1;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.shaded.org.hamcrest.Matchers;

import java.util.*;

import static org.mockito.ArgumentMatchers.matches;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class )
@SpringBootTest
@AutoConfigureMockMvc
public class TestBankController {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private BankServiceV1 bankService;

    @Test
    public void testGetAllBanks() throws Exception
    {
        List<BankTO> bankDTOList = new ArrayList<>();
        BankTO bankDTO = new BankTO();
        bankDTO.setBankName("SBI");
        bankDTO.setBankAddress("Kapsi,Nagpur");
        List<BranchTO> branchDTOList = new ArrayList<>();
        BranchTO branchDTO = new BranchTO();
        branchDTO.setBranchName("kapsi Branch");
        branchDTO.setBranchAddress("Kapsi Khurd,Nagpur");
        branchDTOList.add(branchDTO);
        bankDTO.setBranches(branchDTOList);
        bankDTOList.add(bankDTO);

        when(bankService.getAllBanks()).thenReturn(bankDTOList);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/v1/banks")
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(status().isOk());
        //mockMvc.perform(requestBuilder).andExpect(jsonPath("$", Matchers.hasSize(1)))
        //        .andDo(print());
    }

    @Test
    public void testBankDetailNotFoundException() throws Exception
    {
        when(bankService.getAllBanks()).thenThrow(new  BankDetailNotFound("Bank Details Not Found"));

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/v1/banks")
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(status().isNotFound());
    }

     @Test
    public void testGetAllBankWithException() throws Exception
    {
        when(bankService.getAllBanks()).thenThrow(new NullPointerException());

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/api/v1/banks")
                .contentType(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder).andExpect(status().isInternalServerError());
    }
}
