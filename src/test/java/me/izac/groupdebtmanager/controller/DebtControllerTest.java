package me.izac.groupdebtmanager.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.izac.groupdebtmanager.dto.*;
import me.izac.groupdebtmanager.model.Debt;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
class DebtControllerTest {
    @Autowired
    ObjectMapper mapper;
    @Autowired
    MockMvc mvc;

    @Test
    @Sql("/scripts/usergroup.sql")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("Cria novo débito")
    @Order(1)
    void createDebt() throws Exception {
        CreateDebtDTO createDebtDTO = CreateDebtDTO.builder()
                .date(Date.from(Instant.now()))
                .groupId(1L)
                .creditor(2L)
                .description("Just one debt")
                .amount(100.0).build();

        DebtDTO debtDTO = DebtDTO.builder()
                .group(1L)
                .amount(100.0)
                .creditor(2L)
                .status(Debt.Status.PENDING)
                .id(1L)
                .build();

        String createDebtDTOStr = mapper.writeValueAsString(createDebtDTO);
        String debtDTOStr = mapper.writeValueAsString(debtDTO);

        RequestBuilder request = MockMvcRequestBuilders
                .post("/api/debts")
                .contentType("application/json")
                .content(createDebtDTOStr);

        MvcResult result = mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertEquals(result.getResponse().getContentAsString(), debtDTOStr);
    }

    @Test
    @DisplayName("Atualiza débito")
    @Order(3)
    void updateDebt() throws Exception {
        CreateDebtDTO createDebtDTO = CreateDebtDTO.builder()
                .date(Date.from(Instant.now()))
                .groupId(1L)
                .creditor(2L)
                .description("Just one debt")
                .amount(200.0).build();

        DebtCompleteDTO debtDTO = DebtCompleteDTO.builder()
                .group(1L)
                .amount(200.0)
                .creditor(2L)
                .debtors(List.of())
                .status(Debt.Status.PENDING)
                .id(1L)
                .build();

        String createDebtDTOStr = mapper.writeValueAsString(createDebtDTO);
        String debtDTOStr = mapper.writeValueAsString(debtDTO);

        RequestBuilder request = MockMvcRequestBuilders
                .put("/api/debts/1")
                .contentType("application/json")
                .content(createDebtDTOStr);

        MvcResult result = mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertEquals(result.getResponse().getContentAsString(), debtDTOStr);
    }

    @Test
    @Order(2)
    @DisplayName("Busca por um débito")
    void getDebt() throws Exception {
        DebtCompleteDTO debtDTO = DebtCompleteDTO.builder()
                .group(1L)
                .amount(100.0)
                .creditor(2L)
                .status(Debt.Status.PENDING)
                .debtors(List.of())
                .id(1L)
                .build();

        String debtDTOStr = mapper.writeValueAsString(debtDTO);

        RequestBuilder request = MockMvcRequestBuilders
                .get("/api/debts/1");

        MvcResult result = mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertEquals(result.getResponse().getContentAsString(), debtDTOStr);
    }

    @Test
    @Order(4)
    @DisplayName("Divide um débito entre usuários")
    void divideDebt() throws Exception {
        ListOfIdsDTO listOfIdsDTO = ListOfIdsDTO.builder()
                .ids(List.of(3L, 4L))
                .build();

        DebtCompleteDTO debtCompleteDTO = DebtCompleteDTO.builder()
                .debtors(List.of(3L, 4L))
                .amount(200.0)
                .group(1L)
                .creditor(2L)
                .status(Debt.Status.PENDING)
                .id(1L)
                .build();

        String listOfIdsDTOStr = mapper.writeValueAsString(listOfIdsDTO);

        RequestBuilder request = MockMvcRequestBuilders
                .post("/api/debts/1/divide")
                .contentType("application/json")
                .content(listOfIdsDTOStr);

        MvcResult result = mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

         DebtCompleteDTO responseDTO = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
         });
         assertEquals(debtCompleteDTO.getId(), responseDTO.getId());
        assertEquals(debtCompleteDTO.getCreditor(), responseDTO.getCreditor());
        assertEquals(debtCompleteDTO.getStatus(), responseDTO.getStatus());
        assertEquals(debtCompleteDTO.getGroup(), responseDTO.getGroup());
        assertEquals(debtCompleteDTO.getAmount(), responseDTO.getAmount());
        assertEquals(new HashSet<>(debtCompleteDTO.getDebtors()), new HashSet<>(responseDTO.getDebtors()));

    }

}