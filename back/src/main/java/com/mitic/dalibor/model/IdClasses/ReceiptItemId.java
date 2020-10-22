package com.mitic.dalibor.model.IdClasses;

import java.io.Serializable;
import java.util.Objects;

public class ReceiptItemId implements Serializable {
    private Long receiptId;
    private Long item_number;

    public ReceiptItemId() {
    }

    public ReceiptItemId(Long receiptId, Long item_number) {
        this.receiptId = receiptId;
        this.item_number = item_number;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReceiptItemId that = (ReceiptItemId) o;
        return receiptId.equals(that.receiptId) &&
                item_number.equals(that.item_number);
    }

    @Override
    public int hashCode() {
        return Objects.hash(receiptId, item_number);
    }
}
