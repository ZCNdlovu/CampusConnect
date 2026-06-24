package za.ac.cput.domain;

import jakarta.persistence.*;

@Entity
public class WishlistItem {
    @Id
    private Long wishlistItemId;
    private String notes;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    protected WishlistItem() {}

    private WishlistItem(Builder builder) {
        this.wishlistItemId = builder.wishlistItemId;
        this.notes = builder.notes;
        this.product = builder.product;
    }

    // Getters
    public Long getWishlistItemId() { return wishlistItemId; }
    public String getNotes() { return notes; }
    public Product getProduct() { return product; }

    @Override
    public String toString() {
        return "WishlistItem{" +
                "wishlistItemId=" + wishlistItemId +
                ", product=" + (product != null ? product.getTitle() : "null") +
                '}';
    }

    public static class Builder {
        private Long wishlistItemId;
        private String notes;
        private Product product;

        public Builder setWishlistItemId(Long wishlistItemId) {
            this.wishlistItemId = wishlistItemId;
            return this;
        }

        public Builder setNotes(String notes) {
            this.notes = notes;
            return this;
        }

        public Builder setProduct(Product product) {
            this.product = product;
            return this;
        }

        public Builder copy(WishlistItem wishlistItem) {
            this.wishlistItemId = wishlistItem.wishlistItemId;
            this.notes = wishlistItem.notes;
            this.product = wishlistItem.product;
            return this;
        }

        public WishlistItem build() {
            return new WishlistItem(this);
        }
    }
}