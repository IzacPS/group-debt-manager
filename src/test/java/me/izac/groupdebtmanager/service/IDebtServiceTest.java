package me.izac.groupdebtmanager.service;

import me.izac.groupdebtmanager.dto.DebtDTO;
import me.izac.groupdebtmanager.repository.DebtRepository;
import me.izac.groupdebtmanager.repository.GroupRepository;
import me.izac.groupdebtmanager.repository.UserRepository;
import me.izac.groupdebtmanager.service.impl.DebtServiceImpl;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class IDebtServiceTest {
    private DebtRepository debtRepository = Mockito.mock(DebtRepository.class);
    private UserRepository userRepository = Mockito.mock(UserRepository.class);
    private GroupRepository groupRepository = Mockito.mock(GroupRepository.class);
    private IDebtService service;

    @BeforeAll
    @Test
    void startTest(){
        service = new DebtServiceImpl(debtRepository, userRepository, groupRepository);
    }

    @Test
    void createDebt() {

    }

    @Test
    void getDebtById() {
    }

    @Test
    void listAllDebtsFromUser() {
    }

    @Test
    void listAllDebtsFromGroup() {
    }

    @Test
    void updateDebtStatus() {
    }

    @Test
    void updateDebt() {
    }
}