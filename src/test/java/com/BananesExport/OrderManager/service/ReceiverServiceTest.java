package com.BananesExport.OrderManager.service;

import com.BananesExport.OrderManager.model.ReceiverEntity;
import com.BananesExport.OrderManager.repository.ReceiverRepository;
import com.sun.source.tree.AssertTree;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
class ReceiverServiceTest {

    @Mock
    ReceiverRepository receiverRepository;
    @InjectMocks
    ReceiverService receiverService = new ReceiverService();
    List<ReceiverEntity> receiverEntities;
    @BeforeEach
    void setUp() {
        ReceiverEntity receiver1 = new ReceiverEntity(1L, "alain1", "145 rue de la belge", "31000", "toulouse", "France");
        ReceiverEntity receiver2 = new ReceiverEntity(2L, "alain2", "145 rue de paris", "75000", "paris", "France");
        ReceiverEntity receiver3 = new ReceiverEntity(3L, "marc", "145 rue de nanterre", "92000", "Nanterre", "France");
        receiverEntities = Arrays.asList(receiver1, receiver2, receiver3);
    }

    @Test
    void getALlReceivers() {
        int receiverListSize = receiverEntities.size();
        when(receiverRepository.findAll()).thenReturn(receiverEntities);
        assertEquals(receiverListSize, receiverService.getALlReceivers().size());
    }

    @Test
    void getReceiverById() {
        long receiver_id =2L;
        when(receiverRepository.findById(receiver_id)).thenReturn(Optional.of(receiverEntities.get(1)));
        assertEquals(receiver_id, receiverService.getReceiverById(receiver_id).getReceiver_id());
    }

    @Test
    void saveOrUpdateReceiver() {
        ReceiverEntity receiver = receiverEntities.get(0);
        assertNotNull(receiver);
        when(receiverRepository.findById(receiver.getReceiver_id())).thenReturn(Optional.of(receiver));
        Optional<ReceiverEntity> tmp = receiverRepository.findById(receiver.getReceiver_id());
        assertTrue(tmp.isPresent());
        ReceiverEntity receiverupdated = receiverEntities.get(1);
//        receiverupdated.setReceiver_id(receiverEntities.get(0).getReceiver_id());
//        when(receiverRepository.save(receiverupdated)).thenReturn(Optional.of(receiverupdated).get());
        assertNotNull(receiverupdated);
        receiverService.updateReceiver(receiverupdated);
        verify(receiverRepository).save(receiverupdated);
    }

    @Test
    void testSaveOrUpdateReceiver() {
    }

    @Test
    void deleteReceiver() {
    }
}