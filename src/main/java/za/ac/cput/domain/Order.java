package za.ac.cput.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {
    @Id
    private Long orderId;
    private Double totalAmount;
    private LocalDateTime orderDate;
    private LocalDateTime paymentDate;
    private LocalDateTime deliveryDate;
    private LocalDateTime completionDate;
    private String deliveryAddress;
    private String trackingNumber;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Enumerated(EnumType.STRING)
    private PaymentMethod paymentMethod;

    @Enumerated(EnumType.STRING)
    private PaymentStatus paymentStatus;

    @Enumerated(EnumType.STRING)
    private DeliveryMethod deliveryMethod;

    @Enumerated(EnumType.STRING)
    private CollectionPoint collectionPoint;

    @Enumerated(EnumType.STRING)
    private DeliveryEligibility deliveryEligibility;

    @ManyToOne
    @JoinColumn(name = "buyer_id")
    private Student buyer;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Student seller;

    @ManyToMany
    @JoinTable(
            name = "order_products",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "product_id")
    )
    private List<Product> products;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<Transaction> transactions;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<Review> reviews;

    @OneToOne(mappedBy = "order", cascade = CascadeType.ALL)
    private RefundRequest refundRequest;

    protected Order() {}

    private Order(Builder builder) {
        this.orderId = builder.orderId;
        this.totalAmount = builder.totalAmount;
        this.orderDate = builder.orderDate;
        this.paymentDate = builder.paymentDate;
        this.deliveryDate = builder.deliveryDate;
        this.completionDate = builder.completionDate;
        this.deliveryAddress = builder.deliveryAddress;
        this.trackingNumber = builder.trackingNumber;
        this.status = builder.status;
        this.paymentMethod = builder.paymentMethod;
        this.paymentStatus = builder.paymentStatus;
        this.deliveryMethod = builder.deliveryMethod;
        this.collectionPoint = builder.collectionPoint;
        this.deliveryEligibility = builder.deliveryEligibility;
        this.buyer = builder.buyer;
        this.seller = builder.seller;
        this.products = builder.products;
        this.transactions = builder.transactions;
        this.reviews = builder.reviews;
        this.refundRequest = builder.refundRequest;
    }

    // Getters
    public Long getOrderId() { return orderId; }
    public Double getTotalAmount() { return totalAmount; }
    public LocalDateTime getOrderDate() { return orderDate; }
    public LocalDateTime getPaymentDate() { return paymentDate; }
    public LocalDateTime getDeliveryDate() { return deliveryDate; }
    public LocalDateTime getCompletionDate() { return completionDate; }
    public String getDeliveryAddress() { return deliveryAddress; }
    public String getTrackingNumber() { return trackingNumber; }
    public OrderStatus getStatus() { return status; }
    public PaymentMethod getPaymentMethod() { return paymentMethod; }
    public PaymentStatus getPaymentStatus() { return paymentStatus; }
    public DeliveryMethod getDeliveryMethod() { return deliveryMethod; }
    public CollectionPoint getCollectionPoint() { return collectionPoint; }
    public DeliveryEligibility getDeliveryEligibility() { return deliveryEligibility; }
    public Student getBuyer() { return buyer; }
    public Student getSeller() { return seller; }
    public List<Product> getProducts() { return products; }
    public List<Transaction> getTransactions() { return transactions; }
    public List<Review> getReviews() { return reviews; }
    public RefundRequest getRefundRequest() { return refundRequest; }

    public void setProducts(List<Product> products) { this.products = products; }

    public double calculateTotal() {
        return products.stream()
                .mapToDouble(Product::getPrice)
                .sum();
    }

    public void updateStatus(OrderStatus newStatus) {
        this.status = newStatus;
        if (newStatus == OrderStatus.COMPLETED) {
            this.completionDate = LocalDateTime.now();
        }
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", totalAmount=" + totalAmount +
                ", status=" + status +
                '}';
    }

    public static class Builder {
        private Long orderId;
        private Double totalAmount;
        private LocalDateTime orderDate = LocalDateTime.now();
        private LocalDateTime paymentDate;
        private LocalDateTime deliveryDate;
        private LocalDateTime completionDate;
        private String deliveryAddress;
        private String trackingNumber;
        private OrderStatus status = OrderStatus.PENDING_PAYMENT;
        private PaymentMethod paymentMethod;
        private PaymentStatus paymentStatus = PaymentStatus.PENDING;
        private DeliveryMethod deliveryMethod;
        private CollectionPoint collectionPoint;
        private DeliveryEligibility deliveryEligibility;
        private Student buyer;
        private Student seller;
        private List<Product> products;
        private List<Transaction> transactions;
        private List<Review> reviews;
        private RefundRequest refundRequest;

        public Builder setOrderId(Long orderId) {
            this.orderId = orderId;
            return this;
        }

        public Builder setTotalAmount(Double totalAmount) {
            this.totalAmount = totalAmount;
            return this;
        }

        public Builder setOrderDate(LocalDateTime orderDate) {
            this.orderDate = orderDate;
            return this;
        }

        public Builder setPaymentDate(LocalDateTime paymentDate) {
            this.paymentDate = paymentDate;
            return this;
        }

        public Builder setDeliveryDate(LocalDateTime deliveryDate) {
            this.deliveryDate = deliveryDate;
            return this;
        }

        public Builder setCompletionDate(LocalDateTime completionDate) {
            this.completionDate = completionDate;
            return this;
        }

        public Builder setDeliveryAddress(String deliveryAddress) {
            this.deliveryAddress = deliveryAddress;
            return this;
        }

        public Builder setTrackingNumber(String trackingNumber) {
            this.trackingNumber = trackingNumber;
            return this;
        }

        public Builder setStatus(OrderStatus status) {
            this.status = status;
            return this;
        }

        public Builder setPaymentMethod(PaymentMethod paymentMethod) {
            this.paymentMethod = paymentMethod;
            return this;
        }

        public Builder setPaymentStatus(PaymentStatus paymentStatus) {
            this.paymentStatus = paymentStatus;
            return this;
        }

        public Builder setDeliveryMethod(DeliveryMethod deliveryMethod) {
            this.deliveryMethod = deliveryMethod;
            return this;
        }

        public Builder setCollectionPoint(CollectionPoint collectionPoint) {
            this.collectionPoint = collectionPoint;
            return this;
        }

        public Builder setDeliveryEligibility(DeliveryEligibility deliveryEligibility) {
            this.deliveryEligibility = deliveryEligibility;
            return this;
        }

        public Builder setBuyer(Student buyer) {
            this.buyer = buyer;
            return this;
        }

        public Builder setSeller(Student seller) {
            this.seller = seller;
            return this;
        }

        public Builder setProducts(List<Product> products) {
            this.products = products;
            return this;
        }

        public Builder setTransactions(List<Transaction> transactions) {
            this.transactions = transactions;
            return this;
        }

        public Builder setReviews(List<Review> reviews) {
            this.reviews = reviews;
            return this;
        }

        public Builder setRefundRequest(RefundRequest refundRequest) {
            this.refundRequest = refundRequest;
            return this;
        }

        public Builder copy(Order order) {
            this.orderId = order.orderId;
            this.totalAmount = order.totalAmount;
            this.orderDate = order.orderDate;
            this.paymentDate = order.paymentDate;
            this.deliveryDate = order.deliveryDate;
            this.completionDate = order.completionDate;
            this.deliveryAddress = order.deliveryAddress;
            this.trackingNumber = order.trackingNumber;
            this.status = order.status;
            this.paymentMethod = order.paymentMethod;
            this.paymentStatus = order.paymentStatus;
            this.deliveryMethod = order.deliveryMethod;
            this.collectionPoint = order.collectionPoint;
            this.deliveryEligibility = order.deliveryEligibility;
            this.buyer = order.buyer;
            this.seller = order.seller;
            this.products = order.products;
            this.transactions = order.transactions;
            this.reviews = order.reviews;
            this.refundRequest = order.refundRequest;
            return this;
        }

        public Order build() {
            return new Order(this);
        }
    }
}