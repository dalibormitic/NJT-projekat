package com.mitic.dalibor.service;

import com.mitic.dalibor.model.Bill;
import com.mitic.dalibor.repository.BillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BillService {
    private final BillRepository billRepository;

    @Autowired
    public BillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }

    public Bill save(Bill bill) {
        return this.billRepository.save(bill);
    }

    public List<Bill> findAll() {
        return this.billRepository.findAll();
    }

    public void deleteById(long id) {
        this.billRepository.deleteById(id);
    }


}
