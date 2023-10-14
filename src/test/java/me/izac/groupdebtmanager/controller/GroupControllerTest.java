package me.izac.groupdebtmanager.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.izac.groupdebtmanager.dto.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
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
class GroupControllerTest {
    @Autowired
    ObjectMapper mapper;
    @Autowired
    MockMvc mvc;

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("Cria um novo grupo")
    void createGroup() throws Exception {
        CreateGroupDTO groupDTO = CreateGroupDTO.builder()
                .name("Group First")
                .description("First Group").build();

        GroupDTO dto = GroupDTO.builder()
                .name("Group First")
                .id(1L)
                .description("First Group").build();

        String resDto = mapper.writeValueAsString(dto);
        String body = mapper.writeValueAsString(groupDTO);

        RequestBuilder request = MockMvcRequestBuilders
                .post("/api/groups")
                .contentType("application/json")
                .content(body);

        MvcResult result = mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        assertEquals(result.getResponse().getContentAsString(), resDto);

    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Sql("/scripts/usergroup.sql")
    @DisplayName("Atualiza um grupo")
    void updateGroup() throws Exception {
        CreateGroupDTO groupDTO = CreateGroupDTO.builder()
                .name("Group First")
                .description("First Group").build();

        GroupCompleteDTO dto = GroupCompleteDTO.builder()
                .name(groupDTO.getName())
                .description(groupDTO.getDescription())
                .users(List.of())
                .debts(List.of())
                .id(1L).build();

        groupDTO.setName("Group Second");
        dto.setName("Group Second");

        String body = mapper.writeValueAsString(groupDTO);
        String resDto = mapper.writeValueAsString(dto);

        RequestBuilder request = MockMvcRequestBuilders
                .put("/api/groups/1")
                .contentType("application/json")
                .content(body);

        MvcResult result = mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();

        assertEquals(result.getResponse().getContentAsString(), resDto);
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Sql("/scripts/usergroup.sql")
    @DisplayName("Busca por um grupo")
    void getGroup() throws Exception {
        GroupCompleteDTO gpDTO = GroupCompleteDTO.builder()
                .name("Familia Silva")
                .description("Despesas da familia Silva")
                .users(List.of(3L, 4L, 5L))
                .debts(List.of())
                .id(2L)
                .build();

        RequestBuilder request = MockMvcRequestBuilders
                .get("/api/groups/2");

        MvcResult result = mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();

        GroupCompleteDTO group = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertEquals(gpDTO.getId(), group.getId());
        assertEquals(gpDTO.getName(), group.getName());
        assertEquals(gpDTO.getDescription(), group.getDescription());
        assertEquals(new HashSet<>(gpDTO.getUsers()), new HashSet<>(group.getUsers()));
        assertEquals(gpDTO.getDebts(), group.getDebts());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Sql("/scripts/usergroupnorel.sql")
    @DisplayName("Adicionar um usuário a um grupo")
    void addUserToGroup() throws Exception {
        ListOfIdsDTO users = ListOfIdsDTO.builder().ids(List.of(1L, 3L, 5L)).build();

        String usersStr = mapper.writeValueAsString(users);

        RequestBuilder request = MockMvcRequestBuilders
                .post("/api/groups/2/users")
                .contentType("application/json")
                .content(usersStr);

        GroupCompleteDTO gpDto = GroupCompleteDTO.builder()
                .id(2L)
                .users(List.of(1L, 3L, 5L))
                .debts(List.of())
                .name("Familia Silva")
                .description("Despesas da familia Silva").build();

        MvcResult result = mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        GroupCompleteDTO resGcDTO = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertEquals(gpDto.getId(), resGcDTO.getId());
        assertEquals(gpDto.getName(), resGcDTO.getName());
        assertEquals(gpDto.getDescription(), resGcDTO.getDescription());
        assertEquals(new HashSet<>(gpDto.getUsers()), new HashSet<>(resGcDTO.getUsers()));
        assertEquals(gpDto.getDebts(), resGcDTO.getDebts());

    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Sql("/scripts/debt.sql")
    @DisplayName("Exclui um usuário de um grupo")
    void deleteUserToGroup() throws Exception {
        ListOfIdsDTO users = ListOfIdsDTO.builder().ids(List.of(1L, 6L)).build();

        String usersStr = mapper.writeValueAsString(users);

        RequestBuilder request = MockMvcRequestBuilders
                .delete("/api/groups/2/users")
                .contentType("application/json")
                .content(usersStr);

        assertDoesNotThrow(() -> {
            mvc.perform(request)
                    .andExpect(MockMvcResultMatchers.status().isOk()).andReturn();
        });
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Sql("/scripts/debt.sql")
    @DisplayName("Busca pelos debts de um grupo")
    void getDebtsFromGroup() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .get("/api/groups/1/debts");

        MvcResult result = mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        List<DebtCompleteDTO> debts = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertFalse(debts.isEmpty());
        assertEquals(debts.size(), 2);
    }


    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Sql("/scripts/usergroup.sql")
    @DisplayName("Busca por usuários em um grupo")
    void getAllUsersOfGroup() throws Exception {
        RequestBuilder request = MockMvcRequestBuilders
                .get("/api/groups/1/users");

        MvcResult result = mvc.perform(request)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
        List<UserCompleteDTO> debts = mapper.readValue(result.getResponse().getContentAsString(), new TypeReference<>() {
        });

        assertFalse(debts.isEmpty());
        assertEquals(debts.size(), 4);
    }
}