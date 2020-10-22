package com.mitic.dalibor.controller;

import com.mitic.dalibor.dto.SupplierDto;
import com.mitic.dalibor.model.Supplier;
import com.mitic.dalibor.service.SupplierService;
import com.mitic.dalibor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins="*")
public class SupplierController {
    private final SupplierService supplierService;
    private final UserService userService;

    @Autowired
    public SupplierController(SupplierService supplierService, UserService userService) {
        this.supplierService = supplierService;
        this.userService = userService;
    }

    @GetMapping("/suppliers")
    public List<SupplierDto> getSuppliers(){
        List<Supplier> suppliers = this.supplierService.findAll();
        List<SupplierDto> supplierDtos = new ArrayList<>();
        for (Supplier supplier:suppliers ) {
            userService.findById(supplier.getCreated_by()).ifPresent(user -> supplierDtos.add(new SupplierDto(supplier.getSupplierId(), supplier.getSupplier_name(), supplier.getSupplier_address(), user.getUsername())));
        }
        return supplierDtos;
    }

    @PostMapping("/suppliers")
    public ResponseEntity<Boolean> addSupplier(@RequestBody Supplier supplier){
        System.out.println(supplier);
        Supplier supplierFromDB = this.supplierService.findBySupplierName(supplier.getSupplier_name());
        if(supplierFromDB == null) {
            supplierService.save(supplier);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    @PatchMapping("/suppliers/{id}")
    public ResponseEntity<Boolean> updateSupplier(@PathVariable long id, @RequestBody Supplier supplier) {
        Supplier supplierFromDB = supplierService.findById(id).orElse(null);
        if(supplierFromDB != null){
            supplierFromDB.setSupplier_name(supplier.getSupplier_name());
            supplierFromDB.setSupplier_address(supplier.getSupplier_address());
            this.supplierService.save(supplierFromDB);
            return new ResponseEntity<>(true, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(false, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/suppliers/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable long id) {
        supplierService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
