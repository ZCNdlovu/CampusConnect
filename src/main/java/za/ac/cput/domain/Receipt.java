package za.ac.cput.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Receipt {
    @Id
    private Long receiptId;
    private String receiptNumber;
    private LocalDateTime receiptDate;
    private double amount;
    private String paymentReference;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "payment_id")
    private Payment payment;

    protected Receipt() {}

    private Receipt(Builder builder) {
        this.receiptId = builder.receiptId;
        this.receiptNumber = builder.receiptNumber;
        this.receiptDate = builder.receiptDate;
        this.amount = builder.amount;
        this.paymentReference = builder.paymentReference;
        this.order = builder.order;
        this.payment = builder.payment;
    }

    // Getters
    public Long getReceiptId() { return receiptId; }
    public String getReceiptNumber() { return receiptNumber; }
    public LocalDateTime getReceiptDate() { return receiptDate; }
    public double getAmount() { return amount; }
    public String getPaymentReference() { return paymentReference; }
    public Order getOrder() { return order; }
    public Payment getPayment() { return payment; }

    @Override
    public String toString() {
        return "Receipt{" +
                "receiptId=" + receiptId +
                ", receiptNumber='" + receiptNumber + '\'' +
                ", amount=" + amount +
                '}';
    }

    public static class Builder {
        private Long receiptId;
        private String receiptNumber;
        private LocalDateTime receiptDate = LocalDateTime.now();
        private double amount;
        private String paymentReference;
        private Order order;
        private Payment payment;

        public Builder setReceiptId(Long receiptId) {
            this.receiptId = receiptId;
            return this;
        }

        public Builder setReceiptNumber(String receiptNumber) {
            this.receiptNumber = receiptNumber;
            return this;
        }

        public Builder setReceiptDate(LocalDateTime receiptDate) {
            this.receiptDate = receiptDate;
            return this;
        }

        public Builder setAmount(double amount) {
            this.amount = amount;
            return this;
        }

        public Builder setPaymentReference(String paymentReference) {
            this.paymentReference = paymentReference;
            return this;
        }

        public Builder setOrder(Order order) {
            this.order = order;
            return this;
        }

        public Builder setPayment(Payment payment) {
            this.payment = payment;
            return this;
        }

        public Builder copy(Receipt receipt) {
            this.receiptId = receipt.receiptId;
            this.receiptNumber = receipt.receiptNumber;
            this.receiptDate = receipt.receiptDate;
            this.amount = receipt.amount;
            this.paymentReference = receipt.paymentReference;
            this.order = receipt.order;
            this.payment = receipt.payment;
            return this;
        }

        public Receipt build() {
            return new Receipt(this);
        }
    }
}