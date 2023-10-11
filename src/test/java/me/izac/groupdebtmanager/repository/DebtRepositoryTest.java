package me.izac.groupdebtmanager.repository;

import me.izac.groupdebtmanager.model.Debt;
import me.izac.groupdebtmanager.model.Group;
import me.izac.groupdebtmanager.model.User;
import org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;

import java.time.Instant;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
@DataJpaTest
@ActiveProfiles("test")
public class DebtRepositoryTest {

    @Autowired DebtRepository debtRepository;
    @Autowired UserRepository userRepository;
    @Autowired GroupRepository groupRepository;

    @Test
    @Sql("/scripts/debt.sql")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("Find all debts of an user")
    void findAllDebtsFromUser(){
        List<Debt> debts = debtRepository.findAllDebtsFromUser(4L);
        assertFalse(debts.isEmpty());
        assertEquals(debts.size(), 3);
    }
    @Test
    @Sql("/scripts/debt.sql")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("Find all debts of a group")
    void findAllDebtsFromGroup(){
        List<Debt> debts = debtRepository.findAllDebtsFromGroup(1L);
        assertFalse(debts.isEmpty());
        assertEquals(debts.size(), 7);
    }

    @Test
    @DisplayName("Save debt on dabatase")
    void createDebt(){
        User user = User.builder()
                .id(1L)
                .firstName("Izac")
                .lastName("Santos")
                .debts(Set.of())
                .groups(Set.of())
                .password("secret")
                .email("mymail@mail.com")
                .build();
        userRepository.save(user);

        Group group = Group.builder()
                .id(1L)
                .name("Group First")
                .description("The First Group")
                .users(Set.of())
                .debts(Set.of())
                .build();
        groupRepository.save(group);

        Debt debt = Debt.builder()
                .id(1L)
                .amount(20)
                .amountPerUser(10)
                .date(Date.from(Instant.now()))
                .debtor(user)
                .group(group)
                .description("A freaking awesome debt!")
                .status(Debt.Status.PENDING)
                .build();

        Debt retDebt = debtRepository.save(debt);
        assertEquals(retDebt.getId(), debt.getId());
        assertEquals(retDebt.getDebtor().getId(), user.getId());
        assertEquals(retDebt.getGroup().getId(), group.getId());
    }
    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Sql("/scripts/debt.sql")
    @DisplayName("Find debt by id")
    void getDebtById(){
        //(ID, AMOUNT, AMOUNT_PER_USER,      DATE      , DEBTOR_ID, GROUP_ID,     DESCRIPTION     , STATUS)
        //(7 ,   60  ,         15     ,  CURRENT_DATE  ,    4     ,    2    , 'just another debts', 'PAID'),
        Optional<Debt> debt = debtRepository.findById(7L);

        assertTrue(debt.isPresent());
        assertEquals(debt.get().getId(), 7L);
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @Sql("/scripts/debt.sql")
    @DisplayName("Find update debt status")
    void updateDebt(){
        Debt debt = debtRepository.findById(9L).get();
        debt.setStatus(Debt.Status.PAID);
        Debt retDebt = debtRepository.save(debt);
        assertEquals(retDebt, debt);
    }
}
