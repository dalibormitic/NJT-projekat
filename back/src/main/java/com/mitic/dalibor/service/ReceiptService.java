package com.mitic.dalibor.service;

import com.mitic.dalibor.model.Receipt;
import com.mitic.dalibor.repository.ReceiptRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceiptService {
    private final ReceiptRepository receiptRepository;

    @Autowired
    public ReceiptService(ReceiptRepository receiptRepository) {
        this.receiptRepository = receiptRepository;
    }

    public List<Receipt> findAll(){
        return this.receiptRepository.findAll();
    }

    public Receipt save(Receipt receipt){
        return this.receiptRepository.save(receipt);
    }
}
