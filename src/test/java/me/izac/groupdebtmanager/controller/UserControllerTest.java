package me.izac.groupdebtmanager.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.izac.groupdebtmanager.GroupDebtManagerApplication;
import me.izac.groupdebtmanager.dto.CreateUserDTO;
import me.izac.groupdebtmanager.dto.DebtDTO;
import me.izac.groupdebtmanager.dto.UserDTO;
import me.izac.groupdebtmanager.model.Debt;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

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
    void updateUser() throws Exception {
        CreateUserDTO userDTO = CreateUserDTO.builder()
                .firstName("Izac")
                .lastName("Santos")
                .email("myemail@mail.com")
                .password("secret").build();

        UserDTO dto = UserDTO.builder()
                .lastName(userDTO.getLastName())
                .email(userDTO.getEmail())
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

        assertEquals(result.getResponse().getContentAsString(), resDto);
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Sql("/scripts/debt.sql")
    void getUser() throws Exception {
        UserDTO dto = UserDTO.builder()
                .firstName("Lucas")
                .lastName("Souza")
                .email("lucasmail@mail.com")
                .id(1L).build();

        String resDto = mapper.writeValueAsString(dto);

        RequestBuilder request = MockMvcRequestBuilders
                .get("/api/users/1");

        MvcResult result = mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        assertEquals(result.getResponse().getContentAsString(), resDto);
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Sql("/scripts/debt.sql")
    void getDebtsFromUser() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .get("/api/users/4/debts");

        MvcResult result = mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        List<DebtDTO> debts = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {});

        assertFalse(debts.isEmpty());
        assertEquals(debts.size(), 3);
        List<Long> debtIds = List.of(4L, 7L, 11L);

        for(Long id : debtIds){
            assertTrue(debts.stream().anyMatch((debtDTO -> debtDTO.getId() == id)));
        }
    }
}