package me.izac.groupdebtmanager.controller;

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

import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
class UserControllerTest {

    @Autowired
    ObjectMapper mapper;

    @Autowired
    MockMvc mvc;
    @Test
    @Order(1)
    @DisplayName("Cria um usuário")
    void createUser() throws Exception {
        CreateUserDTO userDTO = CreateUserDTO.builder()
                .firstName("Izac")
                .lastName("Santos")
                .email("myemail@mail.com")
                .password("secret").build();
        UserDTO dto = UserDTO.builder()
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .email(userDTO.getEmail())
                .id(1L).build();

        String body = mapper.writeValueAsString(userDTO);
        String resDto = mapper.writeValueAsString(dto);

        RequestBuilder request = MockMvcRequestBuilders
                .post("/api/users")
                .contentType("application/json")
                .content(body);
        MvcResult result = mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        assertEquals(result.getResponse().getContentAsString(), resDto);
    }

    @Test
    @Order(2)
    @DisplayName("Atualiza um usuário")
    void updateUser() throws Exception {
        CreateUserDTO userDTO = CreateUserDTO.builder()
                .firstName("Izac")
                .lastName("Santos")
                .email("myemail@mail.com")
                .password("secret").build();

        UserCompleteDTO dto = UserCompleteDTO.builder()
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .email(userDTO.getEmail())
                .debtsAsDebtor(List.of())
                .debtsAsCreditor(List.of())
                .groups(List.of())
                .id(1L).build();

        userDTO.setFirstName("Marcelo");
        dto.setFirstName("Marcelo");

        String body = mapper.writeValueAsString(userDTO);
        String resDto = mapper.writeValueAsString(dto);

        RequestBuilder request = MockMvcRequestBuilders
                .put("/api/users/1")
                .contentType("application/json")
                .content(body);

        MvcResult result = mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        assertEquals(resDto, result.getResponse().getContentAsString());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Sql("/scripts/debt.sql")
    @Order(3)
    @DisplayName("Busca por um usuário")
    void getUser() throws Exception {
        UserCompleteDTO dto = UserCompleteDTO.builder()
                .firstName("Joao")
                .lastName("Silva")
                .email("joao@example.com")
                .debtsAsCreditor(List.of(1L, 2L))
                .debtsAsDebtor(List.of())
                .groups(List.of())
                .id(1L).build();


        RequestBuilder request = MockMvcRequestBuilders
                .get("/api/users/1");

        MvcResult result = mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        UserCompleteDTO resDto = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertEquals(dto.getId(), resDto.getId());
        assertEquals(dto.getFirstName(), resDto.getFirstName());
        assertEquals(dto.getLastName(), resDto.getLastName());
        assertEquals(dto.getEmail(), resDto.getEmail());
        assertEquals(new HashSet<>(dto.getDebtsAsCreditor()), new HashSet<>(resDto.getDebtsAsCreditor()));
        assertEquals(dto.getDebtsAsDebtor(), resDto.getDebtsAsDebtor());
        assertEquals(dto.getGroups(), resDto.getGroups());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Sql("/scripts/debt.sql")
    @Order(4)
    @DisplayName("Busca por debtos de um usuário")
    void getDebtsFromUser() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .get("/api/users/5/debts");

        MvcResult result = mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        List<DebtCompleteDTO> debts = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {});

        assertFalse(debts.isEmpty());
        assertEquals(debts.size(), 2);

        int idx = 0;
        for(DebtCompleteDTO dc : debts){
            assertTrue(dc.getDebtors().stream().anyMatch(d -> d == 5L));
        }
    }
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Sql("/scripts/debt.sql")
    @Order(5)
    @DisplayName("Paga um débito")
    void payDebt() throws Exception {
        ListOfIdsDTO listOfIdsDTO = ListOfIdsDTO.builder()
                .ids(List.of(1L, 2L))
                .build();

        List<DebtDebtorDTO> debts = List.of(
                DebtDebtorDTO.builder()
                        .id(1L)
                        .amount(50.0)
                        .creditor(1L)
                        .status(Debt.Status.PAID)
                        .group(1L)
                        .build(),
                DebtDebtorDTO.builder()
                        .id(2L)
                        .amount(1000.0)
                        .creditor(1L)
                        .status(Debt.Status.PAID)
                        .group(2L)
                        .build()
        );

        String listOfIdsDTOStr = mapper.writeValueAsString(listOfIdsDTO);
        String debtCompleteDTOStr = mapper.writeValueAsString(debts);

        RequestBuilder request = MockMvcRequestBuilders
                .put("/api/users/5/debts/pay")
                .contentType("application/json")
                .content(listOfIdsDTOStr);

        MvcResult result = mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertEquals(debtCompleteDTOStr, result.getResponse().getContentAsString());
    }
}