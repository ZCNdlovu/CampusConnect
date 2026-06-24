package za.ac.cput.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Payment {
    @Id
    private Long paymentId;
    private Double amount;
    private String paymentReference;
    private LocalDateTime paymentDate;
    private String cardLastFour;
    private String cardType;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private PaymentStatus status;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    protected Payment() {}

    private Payment(Builder builder) {
        this.paymentId = builder.paymentId;
        this.amount = builder.amount;
        this.paymentReference = builder.paymentReference;
        this.paymentDate = builder.paymentDate;
        this.cardLastFour = builder.cardLastFour;
        this.cardType = builder.cardType;
        this.paymentMethod = builder.paymentMethod;
        this.status = builder.status;
        this.student = builder.student;
        this.order = builder.order;
    }

    // Getters
    public Long getPaymentId() { return paymentId; }
    public Double getAmount() { return amount; }
    public String getPaymentReference() { return paymentReference; }
    public LocalDateTime getPaymentDate() { return paymentDate; }
    public String getCardLastFour() { return cardLastFour; }
    public String getCardType() { return cardType; }
    public PaymentMethod getPaymentMethod() { return paymentMethod; }
    public PaymentStatus getStatus() { return status; }
    public Student getStudent() { return student; }
    public Order getOrder() { return order; }

    public void markAsCompleted() {
        this.status = PaymentStatus.COMPLETED;
        this.paymentDate = LocalDateTime.now();
    }

    public void markAsFailed() {
        this.status = PaymentStatus.FAILED;
    }

    @Override
    public String toString() {
        return "Payment{" +
                "paymentId=" + paymentId +
                ", amount=" + amount +
                ", paymentMethod=" + paymentMethod +
                ", status=" + status +
                '}';
    }

    public static class Builder {
        private Long paymentId;
        private Double amount;
        private String paymentReference;
        private LocalDateTime paymentDate = LocalDateTime.now();
        private String cardLastFour;
        private String cardType;
        private PaymentMethod paymentMethod;
        private PaymentStatus status = PaymentStatus.PENDING;
        private Student student;
        private Order order;

        public Builder setPaymentId(Long paymentId) {
            this.paymentId = paymentId;
            return this;
        }

        public Builder setAmount(Double amount) {
            this.amount = amount;
            return this;
        }

        public Builder setPaymentReference(String paymentReference) {
            this.paymentReference = paymentReference;
            return this;
        }

        public Builder setPaymentDate(LocalDateTime paymentDate) {
            this.paymentDate = paymentDate;
            return this;
        }

        public Builder setCardLastFour(String cardLastFour) {
            this.cardLastFour = cardLastFour;
            return this;
        }

        public Builder setCardType(String cardType) {
            this.cardType = cardType;
            return this;
        }

        public Builder setPaymentMethod(PaymentMethod paymentMethod) {
            this.paymentMethod = paymentMethod;
            return this;
        }

        public Builder setStatus(PaymentStatus status) {
            this.status = status;
            return this;
        }

        public Builder setStudent(Student student) {
            this.student = student;
            return this;
        }

        public Builder setOrder(Order order) {
            this.order = order;
            return this;
        }

        public Builder copy(Payment payment) {
            this.paymentId = payment.paymentId;
            this.amount = payment.amount;
            this.paymentReference = payment.paymentReference;
            this.paymentDate = payment.paymentDate;
            this.cardLastFour = payment.cardLastFour;
            this.cardType = payment.cardType;
            this.paymentMethod = payment.paymentMethod;
            this.status = payment.status;
            this.student = payment.student;
            this.order = payment.order;
            return this;
        }

        public Payment build() {
            return new Payment(this);
        }
    }
}