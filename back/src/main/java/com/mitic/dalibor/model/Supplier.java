package com.mitic.dalibor.model;

import javax.persistence.*;

@Entity(name = "Supplier")
@Table(name = "dobavljac")
public class Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long supplierId;
    @Column(name = "supplier_name")
    private String supplierName;
    private String supplier_address;
    private Long created_by;

    public Supplier() {
    }

    public Supplier(String supplier_name, String supplierAddress, Long createdBy) {
        this.supplierName = supplier_name;
        this.supplier_address = supplierAddress;
        this.created_by = createdBy;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplier_name() {
        return supplierName;
    }

    public void setSupplier_name(String supplier_name) {
        this.supplierName = supplier_name;
    }

    public String getSupplier_address() {
        return supplier_address;
    }

    public void setSupplier_address(String supplierAddress) {
        this.supplier_address = supplierAddress;
    }

    public Long getCreated_by() {
        return created_by;
    }

    public void setCreated_by(Long createdBy) {
        this.created_by = createdBy;
    }

    @Override
    public String toString() {
        return "Supplier{" +
                "supplierId=" + supplierId +
                ", supplierName='" + supplierName + '\'' +
                ", supplier_address='" + supplier_address + '\'' +
                ", created_by=" + created_by +
                '}';
    }
}
