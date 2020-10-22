package com.mitic.dalibor.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.mitic.dalibor.dto.BillDto;
import com.mitic.dalibor.dto.BillItemDto;
import com.mitic.dalibor.model.Bill;
import com.mitic.dalibor.model.BillItem;
import com.mitic.dalibor.model.Product;
import com.mitic.dalibor.model.User;
import com.mitic.dalibor.service.BillItemService;
import com.mitic.dalibor.service.BillService;
import com.mitic.dalibor.service.ProductService;
import com.mitic.dalibor.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class BillController {
    private final BillService billService;
    private final BillItemService billItemService;
    private final UserService userService;
    private final ProductService productService;

    @Autowired
    public BillController(BillService billService, BillItemService billItemService, UserService userService, ProductService productService) {
        this.billService = billService;
        this.billItemService = billItemService;
        this.userService = userService;
        this.productService = productService;
    }

    @GetMapping("/bills")
    public List<BillDto> getBills() {
        List<Bill> bills = billService.findAll();
        List<BillDto> billDtos = new ArrayList<>();
        User user;
        for (Bill bill : bills) {
            user = userService.findById(bill.getSigned_by()).orElse(null);
            List<BillItem> billItems = billItemService.findByBillId(bill.getBillId());
            List<BillItemDto> billItemDtos = new ArrayList<>();
            for (BillItem item : billItems) {
                Product product = productService.findById(item.getArticle_id()).orElse(null);
                BillItemDto billItemDto = new BillItemDto(item.getItem_number(), item.getBill_id(), item.getQuantity(), item.getPrice(), product.getProduct_name());
                billItemDtos.add(billItemDto);
            }
            BillDto billDto = new BillDto(bill.getBillId(), bill.getBill_date(), bill.getBill_amount(), bill.isProcessed(), user.getUsername());
            billDto.setBillItems(billItemDtos);
            billDtos.add(billDto);
        }
        return billDtos;
    }

    @GetMapping("/bills/items/{id}")
    public List<BillItem> getBillItems(@PathVariable Long id) {
        List<BillItem> billItems = billItemService.findByBillId(id);
        return billItems;
    }

    @GetMapping("/bills/items")
    public List<BillItem> getAllBillItems() {
        List<BillItem> billItems = billItemService.findAll();
        return billItems;
    }

    @PostMapping("/bills")
    public ResponseEntity<Boolean> saveBill(@RequestBody ObjectNode json) throws IOException {
        JsonNode bill = json.get("bill");
        JsonNode billItems = json.get("billItems");

        Long item_num = 1L;

        ObjectMapper mapper = new ObjectMapper();
        Bill billConv = mapper.convertValue(bill, Bill.class);
        ObjectReader reader = mapper.readerFor(new TypeReference<List<BillItem>>() {
        });

        Bill billToSave = billService.save(billConv);
        List<BillItem> items = reader.readValue(billItems);

        for (BillItem item : items) {
            item.setBill_id(billToSave.getBillId());
            item.setItem_number(item_num);
            billItemService.save(item);
            item_num++;
        }
        return new ResponseEntity<>(true, HttpStatus.OK);
    }

}
