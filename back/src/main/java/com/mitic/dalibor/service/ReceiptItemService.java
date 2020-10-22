package com.mitic.dalibor.service;

import com.mitic.dalibor.model.ReceiptItem;
import com.mitic.dalibor.repository.ReceiptItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReceiptItemService {
    private ReceiptItemRepository receiptItemRepository;

    @Autowired
    public ReceiptItemService(ReceiptItemRepository receiptItemRepository) {
        this.receiptItemRepository = receiptItemRepository;
    }

    public List<ReceiptItem> findAll(){
        return this.receiptItemRepository.findAll();
    }

    public List<ReceiptItem> findByReceiptId(Long id){
        return this.receiptItemRepository.findByReceiptId(id);
    }

    public void save(ReceiptItem item){
        this.receiptItemRepository.save(item);
    }
}
