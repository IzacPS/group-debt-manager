package me.izac.groupdebtmanager.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import me.izac.groupdebtmanager.dto.CreateGroupDTO;
import me.izac.groupdebtmanager.dto.CreateUserDTO;
import me.izac.groupdebtmanager.dto.GroupDTO;
import me.izac.groupdebtmanager.dto.UserDTO;
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

import java.io.UnsupportedEncodingException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@ActiveProfiles("test")
class GroupControllerTest {
    @Autowired GroupController groupController;
    @Autowired
    ObjectMapper mapper;
    @Autowired
    MockMvc mvc;

    @Test
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
    @Sql("/scripts/debt.sql")
    void updateGroup() throws Exception {
        CreateGroupDTO groupDTO = CreateGroupDTO.builder()
                .name("Group First")
                .description("First Group").build();

        GroupDTO dto = GroupDTO.builder()
                .name(groupDTO.getName())
                .description(groupDTO.getDescription())
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
    @Sql("/scripts/debt.sql")
    void getGroup() {
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Sql("/scripts/debt.sql")
    void addUserToGroup() {
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Sql("/scripts/debt.sql")
    void deleteUserToGroup() {
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Sql("/scripts/debt.sql")
    void getDebtsFromGroup() {
    }
}