package com.mitic.dalibor.service;

import com.mitic.dalibor.model.BillItem;
import com.mitic.dalibor.repository.BillItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillItemService {
    private final BillItemRepository billItemRepository;

    @Autowired
    public BillItemService(BillItemRepository billItemRepository) {
        this.billItemRepository = billItemRepository;
    }

    public List<BillItem> findAll(){
        return this.billItemRepository.findAll();
    }

    public List<BillItem> findByBillId(Long id){
        return this.billItemRepository.findByBillId(id);
    }

    public void save(BillItem item){
        this.billItemRepository.save(item);
    }
}
