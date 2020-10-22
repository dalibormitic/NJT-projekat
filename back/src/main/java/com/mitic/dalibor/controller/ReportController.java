package com.mitic.dalibor.controller;

import com.mitic.dalibor.model.Bill;
import com.mitic.dalibor.model.Receipt;
import com.mitic.dalibor.service.BillService;
import com.mitic.dalibor.service.ReceiptService;
import com.mitic.dalibor.util.GeneratePdfReport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
public class ReportController {
    private final BillService billService;
    private final ReceiptService receiptService;

    @Autowired
    public ReportController(BillService billService, ReceiptService receiptService) {
        this.billService = billService;
        this.receiptService = receiptService;
    }

    @GetMapping(value = "/pdfreport", produces = MediaType.APPLICATION_PDF_VALUE)
    public ResponseEntity<InputStreamResource> getReport() {
        List<Bill> bills = billService.findAll();
        List<Receipt> receipts = receiptService.findAll();
        ByteArrayInputStream bis = GeneratePdfReport.createReport(bills, receipts);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=report.pdf");

        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}
