package com.mitic.dalibor.dto;

public class SupplierDto {
    private Long supplierId;
    private String supplier_name;
    private String supplier_address;
    private String created_by;

    public SupplierDto() {
    }

    public SupplierDto(Long supplierId, String supplier_name, String supplier_address, String created_by) {
        this.supplierId = supplierId;
        this.supplier_name = supplier_name;
        this.supplier_address = supplier_address;
        this.created_by = created_by;
    }

    public Long getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(Long supplierId) {
        this.supplierId = supplierId;
    }

    public String getSupplier_name() {
        return supplier_name;
    }

    public void setSupplier_name(String supplier_name) {
        this.supplier_name = supplier_name;
    }

    public String getSupplier_address() {
        return supplier_address;
    }

    public void setSupplier_address(String supplier_address) {
        this.supplier_address = supplier_address;
    }

    public String getCreated_by() {
        return created_by;
    }

    public void setCreated_by(String created_by) {
        this.created_by = created_by;
    }
}
