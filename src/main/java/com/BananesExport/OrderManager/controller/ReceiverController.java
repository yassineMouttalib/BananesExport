package com.BananesExport.OrderManager.controller;

import com.BananesExport.OrderManager.model.ReceiverEntity;
import com.BananesExport.OrderManager.service.ReceiverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReceiverController {
    @Autowired
    ReceiverService service;

    @GetMapping("/receivers")
    public List<ReceiverEntity> getAllReceivers(){
        return service.getALlReceivers();
    }

    @PostMapping("/addReceiver")
    public ResponseEntity addReceiver(@RequestBody ReceiverEntity receiverEntity) {
        ReceiverEntity receiver;
        try {
            receiver = service.addReceiver(receiverEntity);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(receiver,HttpStatus.CREATED);
    }

    @PutMapping("/updateReceiver")
    public ResponseEntity updateReceiver(@RequestBody ReceiverEntity receiverEntity) {
        ReceiverEntity receiver;
        try {
            receiver = service.updateReceiver(receiverEntity);
        } catch (Exception e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity(receiver, HttpStatus.OK);
    }

    @GetMapping("/deleteReceiver/{id}")
    public ResponseEntity deleteReceiver(@PathVariable("id") Long id) {
        try {
            service.deleteReceiver(id);
        } catch (Exception e) {
            return new ResponseEntity("Error : "+e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity("receiver deleted with Id : "+ id, HttpStatus.OK);
    }

}
