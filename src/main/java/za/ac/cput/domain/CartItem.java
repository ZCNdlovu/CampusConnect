package za.ac.cput.domain;

import jakarta.persistence.*;

@Entity
public class CartItem {
    @Id
    private Long cartItemId;
    private int quantity;
    private double priceAtAdd;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    protected CartItem() {}

    private CartItem(Builder builder) {
        this.cartItemId = builder.cartItemId;
        this.quantity = builder.quantity;
        this.priceAtAdd = builder.priceAtAdd;
        this.product = builder.product;
    }

    // Getters
    public Long getCartItemId() { return cartItemId; }
    public int getQuantity() { return quantity; }
    public double getPriceAtAdd() { return priceAtAdd; }
    public Product getProduct() { return product; }

    public double getSubtotal() {
        return priceAtAdd * quantity;
    }

    @Override
    public String toString() {
        return "CartItem{" +
                "cartItemId=" + cartItemId +
                ", quantity=" + quantity +
                ", subtotal=" + getSubtotal() +
                '}';
    }

    public static class Builder {
        private Long cartItemId;
        private int quantity;
        private double priceAtAdd;
        private Product product;

        public Builder setCartItemId(Long cartItemId) {
            this.cartItemId = cartItemId;
            return this;
        }

        public Builder setQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public Builder setPriceAtAdd(double priceAtAdd) {
            this.priceAtAdd = priceAtAdd;
            return this;
        }

        public Builder setProduct(Product product) {
            this.product = product;
            return this;
        }

        public Builder copy(CartItem cartItem) {
            this.cartItemId = cartItem.cartItemId;
            this.quantity = cartItem.quantity;
            this.priceAtAdd = cartItem.priceAtAdd;
            this.product = cartItem.product;
            return this;
        }

        public CartItem build() {
            return new CartItem(this);
        }
    }
}