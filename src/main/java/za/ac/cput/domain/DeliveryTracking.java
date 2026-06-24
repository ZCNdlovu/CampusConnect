package za.ac.cput.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class DeliveryTracking {
    @Id
    private Long trackingId;
    private String trackingNumber;
    private String currentLocation;
    private String status;
    private LocalDateTime timestamp;
    private String description;
    private boolean isDelivered;

    @ManyToOne
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    protected DeliveryTracking() {}

    private DeliveryTracking(Builder builder) {
        this.trackingId = builder.trackingId;
        this.trackingNumber = builder.trackingNumber;
        this.currentLocation = builder.currentLocation;
        this.status = builder.status;
        this.timestamp = builder.timestamp;
        this.description = builder.description;
        this.isDelivered = builder.isDelivered;
        this.delivery = builder.delivery;
    }

    // Getters
    public Long getTrackingId() { return trackingId; }
    public String getTrackingNumber() { return trackingNumber; }
    public String getCurrentLocation() { return currentLocation; }
    public String getStatus() { return status; }
    public LocalDateTime getTimestamp() { return timestamp; }
    public String getDescription() { return description; }
    public boolean isDelivered() { return isDelivered; }
    public Delivery getDelivery() { return delivery; }

    public void markAsDelivered() {
        this.isDelivered = true;
        this.status = "DELIVERED";
        this.timestamp = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "DeliveryTracking{" +
                "trackingId=" + trackingId +
                ", trackingNumber='" + trackingNumber + '\'' +
                ", status='" + status + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }

    public static class Builder {
        private Long trackingId;
        private String trackingNumber;
        private String currentLocation;
        private String status = "PENDING";
        private LocalDateTime timestamp = LocalDateTime.now();
        private String description;
        private boolean isDelivered = false;
        private Delivery delivery;

        public Builder setTrackingId(Long trackingId) {
            this.trackingId = trackingId;
            return this;
        }

        public Builder setTrackingNumber(String trackingNumber) {
            this.trackingNumber = trackingNumber;
            return this;
        }

        public Builder setCurrentLocation(String currentLocation) {
            this.currentLocation = currentLocation;
            return this;
        }

        public Builder setStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder setTimestamp(LocalDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setIsDelivered(boolean isDelivered) {
            this.isDelivered = isDelivered;
            return this;
        }

        public Builder setDelivery(Delivery delivery) {
            this.delivery = delivery;
            return this;
        }

        public Builder copy(DeliveryTracking tracking) {
            this.trackingId = tracking.trackingId;
            this.trackingNumber = tracking.trackingNumber;
            this.currentLocation = tracking.currentLocation;
            this.status = tracking.status;
            this.timestamp = tracking.timestamp;
            this.description = tracking.description;
            this.isDelivered = tracking.isDelivered;
            this.delivery = tracking.delivery;
            return this;
        }

        public DeliveryTracking build() {
            return new DeliveryTracking(this);
        }
    }
}