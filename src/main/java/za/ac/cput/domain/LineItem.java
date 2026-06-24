package za.ac.cput.domain;

import jakarta.persistence.*;

@Entity
public class LineItem {
    @Id
    private Long lineItemId;
    private String description;
    private int quantity;
    private double unitPrice;
    private double totalPrice;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    protected LineItem() {}

    private LineItem(Builder builder) {
        this.lineItemId = builder.lineItemId;
        this.description = builder.description;
        this.quantity = builder.quantity;
        this.unitPrice = builder.unitPrice;
        this.totalPrice = builder.totalPrice;
        this.product = builder.product;
    }

    // Getters
    public Long getLineItemId() { return lineItemId; }
    public String getDescription() { return description; }
    public int getQuantity() { return quantity; }
    public double getUnitPrice() { return unitPrice; }
    public double getTotalPrice() { return totalPrice; }
    public Product getProduct() { return product; }

    public double calculateTotal() {
        this.totalPrice = this.unitPrice * this.quantity;
        return this.totalPrice;
    }

    @Override
    public String toString() {
        return "LineItem{" +
                "lineItemId=" + lineItemId +
                ", description='" + description + '\'' +
                ", quantity=" + quantity +
                ", totalPrice=" + totalPrice +
                '}';
    }

    public static class Builder {
        private Long lineItemId;
        private String description;
        private int quantity;
        private double unitPrice;
        private double totalPrice;
        private Product product;

        public Builder setLineItemId(Long lineItemId) {
            this.lineItemId = lineItemId;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder setUnitPrice(double unitPrice) {
            this.unitPrice = unitPrice;
            return this;
        }

        public Builder setTotalPrice(double totalPrice) {
            this.totalPrice = totalPrice;
            return this;
        }

        public Builder setProduct(Product product) {
            this.product = product;
            return this;
        }

        public Builder copy(LineItem lineItem) {
            this.lineItemId = lineItem.lineItemId;
            this.description = lineItem.description;
            this.quantity = lineItem.quantity;
            this.unitPrice = lineItem.unitPrice;
            this.totalPrice = lineItem.totalPrice;
            this.product = lineItem.product;
            return this;
        }

        public LineItem build() {
            return new LineItem(this);
        }
    }
}