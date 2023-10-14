package me.izac.groupdebtmanager.repository;

import me.izac.groupdebtmanager.model.Debt;
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

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@ExtendWith(MockitoExtension.class)
@DataJpaTest
@ActiveProfiles("test")
class DebtRepositoryTest {
    @Autowired
    DebtRepository debtRepository;

    @Test
    @Sql("/scripts/debt.sql")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("Find all debts by debtor's id")
    void findAllByDebtorsId() {
        List<Debt> debts = debtRepository.findAllByDebtorsId(2L);
        assertFalse(debts.isEmpty());
        assertEquals(debts.size(), 2);
    }

    @Test
    @Sql("/scripts/debt.sql")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("Find all debts by debtor's email")
    void findAllByDebtorsEmail() {
        List<Debt> debts = debtRepository.findAllByDebtorsId(2L);
        assertFalse(debts.isEmpty());
        assertEquals(debts.size(), 2);
    }

    @Test
    @Sql("/scripts/debt.sql")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("Find all debts by creditor's id")
    void findAllByCreditorId() {
        List<Debt> debts = debtRepository.findAllByDebtorsId(2L);
        assertFalse(debts.isEmpty());
        assertEquals(debts.size(), 2);
    }

    @Test
    @Sql("/scripts/debt.sql")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("Find all debts by creditor's email")
    void findAllByCreditorEmail() {
        List<Debt> debts = debtRepository.findAllByCreditorId(1L);
        assertFalse(debts.isEmpty());
        assertEquals(debts.size(), 2);
    }

    @Test
    @Sql("/scripts/debt.sql")
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("Find all debts by group id")
    void findAllByGroupId() {
        List<Debt> debts = debtRepository.findAllByCreditorEmail("joao@example.com");
        assertFalse(debts.isEmpty());
        assertEquals(debts.size(), 2);
    }
}