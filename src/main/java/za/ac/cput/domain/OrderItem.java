package za.ac.cput.domain;

import jakarta.persistence.*;

@Entity
public class OrderItem {
    @Id
    private Long orderItemId;
    private int quantity;
    private double priceAtPurchase;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    protected OrderItem() {}

    private OrderItem(Builder builder) {
        this.orderItemId = builder.orderItemId;
        this.quantity = builder.quantity;
        this.priceAtPurchase = builder.priceAtPurchase;
        this.order = builder.order;
        this.product = builder.product;
    }

    // Getters
    public Long getOrderItemId() { return orderItemId; }
    public int getQuantity() { return quantity; }
    public double getPriceAtPurchase() { return priceAtPurchase; }
    public Order getOrder() { return order; }
    public Product getProduct() { return product; }

    public double getSubtotal() {
        return priceAtPurchase * quantity;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "orderItemId=" + orderItemId +
                ", quantity=" + quantity +
                ", subtotal=" + getSubtotal() +
                '}';
    }

    public static class Builder {
        private Long orderItemId;
        private int quantity;
        private double priceAtPurchase;
        private Order order;
        private Product product;

        public Builder setOrderItemId(Long orderItemId) {
            this.orderItemId = orderItemId;
            return this;
        }

        public Builder setQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder setPriceAtPurchase(double priceAtPurchase) {
            this.priceAtPurchase = priceAtPurchase;
            return this;
        }

        public Builder setOrder(Order order) {
            this.order = order;
            return this;
        }

        public Builder setProduct(Product product) {
            this.product = product;
            return this;
        }

        public Builder copy(OrderItem orderItem) {
            this.orderItemId = orderItem.orderItemId;
            this.quantity = orderItem.quantity;
            this.priceAtPurchase = orderItem.priceAtPurchase;
            this.order = orderItem.order;
            this.product = orderItem.product;
            return this;
        }

        public OrderItem build() {
            return new OrderItem(this);
        }
    }
}