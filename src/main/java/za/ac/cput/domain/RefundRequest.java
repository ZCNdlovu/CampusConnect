package za.ac.cput.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class RefundRequest {
    @Id
    private Long refundRequestId;
    private Double amount;
    private String reason;
    private LocalDateTime requestDate;
    private LocalDateTime processedDate;
    private String processedBy;
    private String notes;
    private String bankAccountDetails;

    @Enumerated(EnumType.STRING)
    private RefundStatus status;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "requester_id")
    private Student requester;

    protected RefundRequest() {}

    private RefundRequest(Builder builder) {
        this.refundRequestId = builder.refundRequestId;
        this.amount = builder.amount;
        this.reason = builder.reason;
        this.requestDate = builder.requestDate;
        this.processedDate = builder.processedDate;
        this.processedBy = builder.processedBy;
        this.notes = builder.notes;
        this.bankAccountDetails = builder.bankAccountDetails;
        this.status = builder.status;
        this.order = builder.order;
        this.requester = builder.requester;
    }

    // Getters
    public Long getRefundRequestId() { return refundRequestId; }
    public Double getAmount() { return amount; }
    public String getReason() { return reason; }
    public LocalDateTime getRequestDate() { return requestDate; }
    public LocalDateTime getProcessedDate() { return processedDate; }
    public String getProcessedBy() { return processedBy; }
    public String getNotes() { return notes; }
    public String getBankAccountDetails() { return bankAccountDetails; }
    public RefundStatus getStatus() { return status; }
    public Order getOrder() { return order; }
    public Student getRequester() { return requester; }

    public void approve(String processedBy, String notes) {
        this.status = RefundStatus.APPROVED;
        this.processedBy = processedBy;
        this.notes = notes;
        this.processedDate = LocalDateTime.now();
    }

    public void reject(String processedBy, String notes) {
        this.status = RefundStatus.REJECTED;
        this.processedBy = processedBy;
        this.notes = notes;
        this.processedDate = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "RefundRequest{" +
                "refundRequestId=" + refundRequestId +
                ", amount=" + amount +
                ", status=" + status +
                '}';
    }

    public static class Builder {
        private Long refundRequestId;
        private Double amount;
        private String reason;
        private LocalDateTime requestDate = LocalDateTime.now();
        private LocalDateTime processedDate;
        private String processedBy;
        private String notes;
        private String bankAccountDetails;
        private RefundStatus status = RefundStatus.PENDING;
        private Order order;
        private Student requester;

        public Builder setRefundRequestId(Long refundRequestId) {
            this.refundRequestId = refundRequestId;
            return this;
        }

        public Builder setAmount(Double amount) {
            this.amount = amount;
            return this;
        }

        public Builder setReason(String reason) {
            this.reason = reason;
            return this;
        }

        public Builder setRequestDate(LocalDateTime requestDate) {
            this.requestDate = requestDate;
            return this;
        }

        public Builder setProcessedDate(LocalDateTime processedDate) {
            this.processedDate = processedDate;
            return this;
        }

        public Builder setProcessedBy(String processedBy) {
            this.processedBy = processedBy;
            return this;
        }

        public Builder setNotes(String notes) {
            this.notes = notes;
            return this;
        }

        public Builder setBankAccountDetails(String bankAccountDetails) {
            this.bankAccountDetails = bankAccountDetails;
            return this;
        }

        public Builder setStatus(RefundStatus status) {
            this.status = status;
            return this;
        }

        public Builder setOrder(Order order) {
            this.order = order;
            return this;
        }

        public Builder setRequester(Student requester) {
            this.requester = requester;
            return this;
        }

        public Builder copy(RefundRequest refundRequest) {
            this.refundRequestId = refundRequest.refundRequestId;
            this.amount = refundRequest.amount;
            this.reason = refundRequest.reason;
            this.requestDate = refundRequest.requestDate;
            this.processedDate = refundRequest.processedDate;
            this.processedBy = refundRequest.processedBy;
            this.notes = refundRequest.notes;
            this.bankAccountDetails = refundRequest.bankAccountDetails;
            this.status = refundRequest.status;
            this.order = refundRequest.order;
            this.requester = refundRequest.requester;
            return this;
        }

        public RefundRequest build() {
            return new RefundRequest(this);
        }
    }
}