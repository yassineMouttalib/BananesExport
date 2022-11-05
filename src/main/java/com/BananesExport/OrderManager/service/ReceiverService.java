package com.BananesExport.OrderManager.service;

import com.BananesExport.OrderManager.model.ReceiverEntity;
import com.BananesExport.OrderManager.repository.ReceiverRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.Optional;

@Service
public class ReceiverService {
    @Autowired
    ReceiverRepository repository;

    public List<ReceiverEntity> getALlReceivers() {
        return repository.findAll();
    }

    public ReceiverEntity getReceiverById(Long id) {
        return repository.findById(id).get();
    }
    public ReceiverEntity addReceiver(ReceiverEntity receiverEntity) {
        if (receiverEntity.getReceiver_id() !=null) {
            Optional<ReceiverEntity> receiverEntityDBOpt = repository.findById(receiverEntity.getReceiver_id());
            if (receiverEntityDBOpt.isPresent()) {
                throw new EntityExistsException("Receiver already existe");
            }
        }
        return repository.save(receiverEntity);
    }
    public ReceiverEntity updateReceiver(ReceiverEntity receiverEntity) {
        Optional<ReceiverEntity> receiverEntityDBOpt = repository.findById(receiverEntity.getReceiver_id());
        if (receiverEntityDBOpt.isEmpty()) {
            return repository.save(receiverEntity);
        } else {
            ReceiverEntity receiverEntityDB = receiverEntityDBOpt.get();
            if (receiverEntity.getNom() != null) receiverEntityDB.setNom(receiverEntity.getNom());
            if (receiverEntity.getAdresse() != null) receiverEntityDB.setAdresse(receiverEntity.getAdresse());
            if (receiverEntity.getCode_postal() != null) receiverEntityDB.setCode_postal(receiverEntity.getCode_postal());
            if (receiverEntity.getVille() != null) receiverEntityDB.setVille(receiverEntity.getVille());
            if (receiverEntity.getPays() != null) receiverEntityDB.setPays(receiverEntity.getPays());

            return repository.save(receiverEntityDB);
        }
    }
    public void deleteReceiver(Long id) {
        repository.deleteById(id);
    }
}
