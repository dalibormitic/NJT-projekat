package com.mitic.dalibor.service;

import com.mitic.dalibor.model.Supplier;
import com.mitic.dalibor.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SupplierService {
    private final SupplierRepository supplierRepository;

    @Autowired
    public SupplierService(SupplierRepository supplierRepository) {
        this.supplierRepository = supplierRepository;
    }

    public List<Supplier> findAll() {
        return this.supplierRepository.findAll();
    }

    public void save(Supplier supplier) {
        this.supplierRepository.save(supplier);
    }

    public Optional<Supplier> findById(Long id){
        return this.supplierRepository.findById(id);
    }

    public Supplier findBySupplierName(String name){
        return this.supplierRepository.findBySupplierName(name);
    }

    public void deleteById(Long id){
        this.supplierRepository.deleteById(id);
    }
}
