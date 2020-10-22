package com.mitic.dalibor.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mitic.dalibor.dto.ReceiptDto;
import com.mitic.dalibor.dto.ReceiptItemDto;
import com.mitic.dalibor.model.*;
import com.mitic.dalibor.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ReceiptController {
    private final ReceiptService receiptService;
    private final ReceiptItemService receiptItemService;
    private final UserService userService;
    private final SupplierService supplierService;
    private final ProductService productService;

    @Autowired
    public ReceiptController(ReceiptService receiptService, ReceiptItemService receiptItemService, UserService userService, SupplierService supplierService, ProductService productService) {
        this.receiptService = receiptService;
        this.receiptItemService = receiptItemService;
        this.userService = userService;
        this.supplierService = supplierService;
        this.productService = productService;
    }

    @GetMapping("/receipts")
    public List<ReceiptDto> getAllReceipts(){
        List<Receipt> receipts = this.receiptService.findAll();
        List<ReceiptDto> receiptDtos = new ArrayList<>();
        User signed;
        Supplier supplied;
        for (Receipt receipt : receipts ) {
            signed = userService.findById(receipt.getSigned_by()).orElse(null);
            supplied = supplierService.findById(receipt.getSupplied_by()).orElse(null);
            List<ReceiptItem> receiptItems = receiptItemService.findByReceiptId(receipt.getReceiptId());
            List<ReceiptItemDto> receiptItemDtos = new ArrayList<>();
            for (ReceiptItem item : receiptItems) {
                Product product = productService.findById(item.getArticle_id()).orElse(null);
                ReceiptItemDto receiptItemDto = new ReceiptItemDto(item.getItem_number(), item.getReceiptId(), item.getQuantity(), item.getPrice(), product.getProduct_name());
                receiptItemDtos.add(receiptItemDto);
            }
            ReceiptDto receiptDto = new ReceiptDto(receipt.getReceiptId(), receipt.getReceipt_date(), receipt.getAmount(), supplied.getSupplier_name(), signed.getUsername());
            receiptDto.setReceiptItems(receiptItemDtos);
            receiptDtos.add(receiptDto);
        }
        return receiptDtos;
    }

    @GetMapping("/receipts/items")
    public List<ReceiptItem> getAllReceiptItems(){
        return this.receiptItemService.findAll();
    }

    @GetMapping("/receipts/items/{id}")
    public List<ReceiptItem> getBillItems(@PathVariable Long id) {
        List<ReceiptItem> items = receiptItemService.findByReceiptId(id);
        return items;
    }

    @PostMapping("/receipts")
    public ResponseEntity<Boolean> saveReceipt(@RequestBody ObjectNode json) throws IOException {
        JsonNode receipt = json.get("receipt");
        JsonNode receiptItems = json.get("receiptItems");

        Long item_num = 1L;

        ObjectMapper mapper = new ObjectMapper();
        Receipt receiptConv = mapper.convertValue(receipt, Receipt.class);
        ObjectReader reader = mapper.readerFor(new TypeReference<List<ReceiptItem>>() {
        });

        Receipt receiptToSave = receiptService.save(receiptConv);
        List<ReceiptItem> items = reader.readValue(receiptItems);

        for (ReceiptItem item : items) {
            item.setReceiptId(receiptToSave.getReceiptId());
            item.setItem_number(item_num);
            receiptItemService.save(item);
            item_num++;
        }

        return new ResponseEntity<>(true, HttpStatus.OK);
    }
}
