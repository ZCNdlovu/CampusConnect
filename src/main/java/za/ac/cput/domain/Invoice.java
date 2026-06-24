package za.ac.cput.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Invoice {
    @Id
    private Long invoiceId;
    private String invoiceNumber;
    private LocalDateTime invoiceDate;
    private LocalDateTime dueDate;
    private double subtotal;
    private double taxAmount;
    private double totalAmount;
    private String currency;
    private String notes;
    private boolean isPaid;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "invoice_id")
    private List<LineItem> lineItems;

    protected Invoice() {}

    private Invoice(Builder builder) {
        this.invoiceId = builder.invoiceId;
        this.invoiceNumber = builder.invoiceNumber;
        this.invoiceDate = builder.invoiceDate;
        this.dueDate = builder.dueDate;
        this.subtotal = builder.subtotal;
        this.taxAmount = builder.taxAmount;
        this.totalAmount = builder.totalAmount;
        this.currency = builder.currency;
        this.notes = builder.notes;
        this.isPaid = builder.isPaid;
        this.order = builder.order;
        this.student = builder.student;
        this.lineItems = builder.lineItems;
    }

    // Getters
    public Long getInvoiceId() { return invoiceId; }
    public String getInvoiceNumber() { return invoiceNumber; }
    public LocalDateTime getInvoiceDate() { return invoiceDate; }
    public LocalDateTime getDueDate() { return dueDate; }
    public double getSubtotal() { return subtotal; }
    public double getTaxAmount() { return taxAmount; }
    public double getTotalAmount() { return totalAmount; }
    public String getCurrency() { return currency; }
    public String getNotes() { return notes; }
    public boolean isPaid() { return isPaid; }
    public Order getOrder() { return order; }
    public Student getStudent() { return student; }
    public List<LineItem> getLineItems() { return lineItems; }

    public void markAsPaid() {
        this.isPaid = true;
    }

    public double calculateTotal() {
        this.totalAmount = this.subtotal + this.taxAmount;
        return this.totalAmount;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceId=" + invoiceId +
                ", invoiceNumber='" + invoiceNumber + '\'' +
                ", totalAmount=" + totalAmount +
                ", isPaid=" + isPaid +
                '}';
    }

    public static class Builder {
        private Long invoiceId;
        private String invoiceNumber;
        private LocalDateTime invoiceDate = LocalDateTime.now();
        private LocalDateTime dueDate;
        private double subtotal;
        private double taxAmount;
        private double totalAmount;
        private String currency = "ZAR";
        private String notes;
        private boolean isPaid = false;
        private Order order;
        private Student student;
        private List<LineItem> lineItems;

        public Builder setInvoiceId(Long invoiceId) {
            this.invoiceId = invoiceId;
            return this;
        }

        public Builder setInvoiceNumber(String invoiceNumber) {
            this.invoiceNumber = invoiceNumber;
            return this;
        }

        public Builder setInvoiceDate(LocalDateTime invoiceDate) {
            this.invoiceDate = invoiceDate;
            return this;
        }

        public Builder setDueDate(LocalDateTime dueDate) {
            this.dueDate = dueDate;
            return this;
        }

        public Builder setSubtotal(double subtotal) {
            this.subtotal = subtotal;
            return this;
        }

        public Builder setTaxAmount(double taxAmount) {
            this.taxAmount = taxAmount;
            return this;
        }

        public Builder setTotalAmount(double totalAmount) {
            this.totalAmount = totalAmount;
            return this;
        }

        public Builder setCurrency(String currency) {
            this.currency = currency;
            return this;
        }

        public Builder setNotes(String notes) {
            this.notes = notes;
            return this;
        }

        public Builder setIsPaid(boolean isPaid) {
            this.isPaid = isPaid;
            return this;
        }

        public Builder setOrder(Order order) {
            this.order = order;
            return this;
        }

        public Builder setStudent(Student student) {
            this.student = student;
            return this;
        }

        public Builder setLineItems(List<LineItem> lineItems) {
            this.lineItems = lineItems;
            return this;
        }

        public Builder copy(Invoice invoice) {
            this.invoiceId = invoice.invoiceId;
            this.invoiceNumber = invoice.invoiceNumber;
            this.invoiceDate = invoice.invoiceDate;
            this.dueDate = invoice.dueDate;
            this.subtotal = invoice.subtotal;
            this.taxAmount = invoice.taxAmount;
            this.totalAmount = invoice.totalAmount;
            this.currency = invoice.currency;
            this.notes = invoice.notes;
            this.isPaid = invoice.isPaid;
            this.order = invoice.order;
            this.student = invoice.student;
            this.lineItems = invoice.lineItems;
            return this;
        }

        public Invoice build() {
            return new Invoice(this);
        }
    }
}