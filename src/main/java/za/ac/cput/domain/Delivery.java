package za.ac.cput.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Delivery {
    @Id
    private Long deliveryId;
    private String deliveryAddress;
    private String trackingNumber;
    private LocalDateTime deliveryDate;
    private LocalDateTime estimatedDeliveryDate;
    private String deliveryNotes;
    private boolean isDelivered;

    @Enumerated(EnumType.STRING)
    private DeliveryMethod deliveryMethod;

    @Enumerated(EnumType.STRING)
    private CollectionPoint collectionPoint;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    protected Delivery() {}

    private Delivery(Builder builder) {
        this.deliveryId = builder.deliveryId;
        this.deliveryAddress = builder.deliveryAddress;
        this.trackingNumber = builder.trackingNumber;
        this.deliveryDate = builder.deliveryDate;
        this.estimatedDeliveryDate = builder.estimatedDeliveryDate;
        this.deliveryNotes = builder.deliveryNotes;
        this.isDelivered = builder.isDelivered;
        this.deliveryMethod = builder.deliveryMethod;
        this.collectionPoint = builder.collectionPoint;
        this.student = builder.student;
        this.order = builder.order;
    }

    // Getters
    public Long getDeliveryId() { return deliveryId; }
    public String getDeliveryAddress() { return deliveryAddress; }
    public String getTrackingNumber() { return trackingNumber; }
    public LocalDateTime getDeliveryDate() { return deliveryDate; }
    public LocalDateTime getEstimatedDeliveryDate() { return estimatedDeliveryDate; }
    public String getDeliveryNotes() { return deliveryNotes; }
    public boolean isDelivered() { return isDelivered; }
    public DeliveryMethod getDeliveryMethod() { return deliveryMethod; }
    public CollectionPoint getCollectionPoint() { return collectionPoint; }
    public Student getStudent() { return student; }
    public Order getOrder() { return order; }

    public void markAsDelivered() {
        this.isDelivered = true;
        this.deliveryDate = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Delivery{" +
                "deliveryId=" + deliveryId +
                ", trackingNumber='" + trackingNumber + '\'' +
                ", isDelivered=" + isDelivered +
                ", deliveryMethod=" + deliveryMethod +
                '}';
    }

    public static class Builder {
        private Long deliveryId;
        private String deliveryAddress;
        private String trackingNumber;
        private LocalDateTime deliveryDate;
        private LocalDateTime estimatedDeliveryDate;
        private String deliveryNotes;
        private boolean isDelivered = false;
        private DeliveryMethod deliveryMethod;
        private CollectionPoint collectionPoint;
        private Student student;
        private Order order;

        public Builder setDeliveryId(Long deliveryId) {
            this.deliveryId = deliveryId;
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

        public Builder setDeliveryDate(LocalDateTime deliveryDate) {
            this.deliveryDate = deliveryDate;
            return this;
        }

        public Builder setEstimatedDeliveryDate(LocalDateTime estimatedDeliveryDate) {
            this.estimatedDeliveryDate = estimatedDeliveryDate;
            return this;
        }

        public Builder setDeliveryNotes(String deliveryNotes) {
            this.deliveryNotes = deliveryNotes;
            return this;
        }

        public Builder setIsDelivered(boolean isDelivered) {
            this.isDelivered = isDelivered;
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

        public Builder setStudent(Student student) {
            this.student = student;
            return this;
        }

        public Builder setOrder(Order order) {
            this.order = order;
            return this;
        }

        public Builder copy(Delivery delivery) {
            this.deliveryId = delivery.deliveryId;
            this.deliveryAddress = delivery.deliveryAddress;
            this.trackingNumber = delivery.trackingNumber;
            this.deliveryDate = delivery.deliveryDate;
            this.estimatedDeliveryDate = delivery.estimatedDeliveryDate;
            this.deliveryNotes = delivery.deliveryNotes;
            this.isDelivered = delivery.isDelivered;
            this.deliveryMethod = delivery.deliveryMethod;
            this.collectionPoint = delivery.collectionPoint;
            this.student = delivery.student;
            this.order = delivery.order;
            return this;
        }

        public Delivery build() {
            return new Delivery(this);
        }
    }
}