package za.ac.cput.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Cart {
    @Id
    private Long cartId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private double totalAmount;

    @OneToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "cart_id")
    private List<CartItem> items = new ArrayList<>();

    protected Cart() {}

    private Cart(Builder builder) {
        this.cartId = builder.cartId;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
        this.totalAmount = builder.totalAmount;
        this.student = builder.student;
        this.items = builder.items;
    }

    // Getters
    public Long getCartId() { return cartId; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public double getTotalAmount() { return totalAmount; }
    public Student getStudent() { return student; }
    public List<CartItem> getItems() { return items; }

    public void addItem(CartItem item) {
        this.items.add(item);
        recalculateTotal();
        this.updatedAt = LocalDateTime.now();
    }

    public void removeItem(CartItem item) {
        this.items.remove(item);
        recalculateTotal();
        this.updatedAt = LocalDateTime.now();
    }

    public void clearCart() {
        this.items.clear();
        this.totalAmount = 0.0;
        this.updatedAt = LocalDateTime.now();
    }

    private void recalculateTotal() {
        this.totalAmount = items.stream()
                .mapToDouble(item -> item.getPriceAtAdd() * item.getQuantity())
                .sum();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }

    @Override
    public String toString() {
        return "Cart{" +
                "cartId=" + cartId +
                ", totalAmount=" + totalAmount +
                ", itemsCount=" + items.size() +
                '}';
    }

    public static class Builder {
        private Long cartId;
        private LocalDateTime createdAt = LocalDateTime.now();
        private LocalDateTime updatedAt = LocalDateTime.now();
        private double totalAmount = 0.0;
        private Student student;
        private List<CartItem> items = new ArrayList<>();

        public Builder setCartId(Long cartId) {
            this.cartId = cartId;
            return this;
        }

        public Builder setCreatedAt(LocalDateTime createdAt) {
            this.createdAt = createdAt;
            return this;
        }

        public Builder setUpdatedAt(LocalDateTime updatedAt) {
            this.updatedAt = updatedAt;
            return this;
        }

        public Builder setTotalAmount(double totalAmount) {
            this.totalAmount = totalAmount;
            return this;
        }

        public Builder setStudent(Student student) {
            this.student = student;
            return this;
        }

        public Builder setItems(List<CartItem> items) {
            this.items = items;
            return this;
        }

        public Builder addItem(CartItem item) {
            if (this.items == null) {
                this.items = new ArrayList<>();
            }
            this.items.add(item);
            return this;
        }

        public Builder copy(Cart cart) {
            this.cartId = cart.cartId;
            this.createdAt = cart.createdAt;
            this.updatedAt = cart.updatedAt;
            this.totalAmount = cart.totalAmount;
            this.student = cart.student;
            this.items = cart.items;
            return this;
        }

        public Cart build() {
            return new Cart(this);
        }
    }
}