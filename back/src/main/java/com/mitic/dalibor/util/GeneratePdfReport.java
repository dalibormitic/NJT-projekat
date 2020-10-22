package com.mitic.dalibor.util;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mitic.dalibor.model.Bill;
import com.mitic.dalibor.model.Receipt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.math.BigDecimal;
import java.util.List;

public class GeneratePdfReport {
    private static final Logger logger = LoggerFactory.getLogger(GeneratePdfReport.class);

    public static ByteArrayInputStream createReport(List<Bill> bills, List<Receipt> receipts) {
        double billAmount = 0;
        double receiptAmount = 0;
        double total = 0;

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        try {
            PdfPTable billTable = new PdfPTable(3);
            billTable.setWidthPercentage(70);
            billTable.setWidths(new int[]{2, 3, 3});
            Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
            PdfPCell hcell;
            hcell = new PdfPCell(new Phrase("Bill Id", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            billTable.addCell(hcell);
            hcell = new PdfPCell(new Phrase("Bill Date", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            billTable.addCell(hcell);
            hcell = new PdfPCell(new Phrase("Bill Amount", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            billTable.addCell(hcell);
            for (Bill bill : bills) {
                PdfPCell cell;
                cell = new PdfPCell(new Phrase(bill.getBillId().toString()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                billTable.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(bill.getBill_date())));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                billTable.addCell(cell);

                cell = new PdfPCell(new Phrase(bill.getBill_amount()  + " RSD"));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setPaddingRight(5);
                billTable.addCell(cell);

                billAmount += bill.getBill_amount().doubleValue();
            }

            hcell = new PdfPCell(new Phrase("Receipt Id", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            billTable.addCell(hcell);
            hcell = new PdfPCell(new Phrase("Receipt Date", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            billTable.addCell(hcell);
            hcell = new PdfPCell(new Phrase("Receipt Amount", headFont));
            hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
            billTable.addCell(hcell);

            for (Receipt receipt : receipts) {
                PdfPCell cell;
                cell = new PdfPCell(new Phrase(receipt.getReceiptId().toString()));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                billTable.addCell(cell);

                cell = new PdfPCell(new Phrase(String.valueOf(receipt.getReceipt_date())));
                cell.setPaddingLeft(5);
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                billTable.addCell(cell);

                cell = new PdfPCell(new Phrase(receipt.getAmount() + " RSD"));
                cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setPaddingRight(5);
                billTable.addCell(cell);

                receiptAmount += receipt.getAmount().doubleValue();
            }

            total = billAmount - receiptAmount;

            PdfWriter.getInstance(document, out);
            document.open();
            document.add(billTable);
            Paragraph p = new Paragraph("Ukupna zarada: " + String.format("%.2f", total)  + " RSD");
            p.setAlignment(Element.ALIGN_CENTER);
            document.add(p);
            document.addTitle("Izvestaj");
            document.close();
        } catch (DocumentException ex) {
            logger.error("Error occurred: {0}", ex);
        }
        return new ByteArrayInputStream(out.toByteArray());
    }
}
