package za.ac.cput.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Wishlist {
    @Id
    private Long wishlistId;
    private String name;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private boolean isDefault;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "wishlist_id")
    private List<WishlistItem> items = new ArrayList<>();

    protected Wishlist() {}

    private Wishlist(Builder builder) {
        this.wishlistId = builder.wishlistId;
        this.name = builder.name;
        this.createdAt = builder.createdAt;
        this.updatedAt = builder.updatedAt;
        this.isDefault = builder.isDefault;
        this.student = builder.student;
        this.items = builder.items;
    }

    // Getters
    public Long getWishlistId() { return wishlistId; }
    public String getName() { return name; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public boolean isDefault() { return isDefault; }
    public Student getStudent() { return student; }
    public List<WishlistItem> getItems() { return items; }

    public void addItem(WishlistItem item) {
        this.items.add(item);
        this.updatedAt = LocalDateTime.now();
    }

    public void removeItem(WishlistItem item) {
        this.items.remove(item);
        this.updatedAt = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Wishlist{" +
                "wishlistId=" + wishlistId +
                ", name='" + name + '\'' +
                ", itemsCount=" + items.size() +
                '}';
    }

    public static class Builder {
        private Long wishlistId;
        private String name;
        private LocalDateTime createdAt = LocalDateTime.now();
        private LocalDateTime updatedAt = LocalDateTime.now();
        private boolean isDefault = false;
        private Student student;
        private List<WishlistItem> items = new ArrayList<>();

        public Builder setWishlistId(Long wishlistId) {
            this.wishlistId = wishlistId;
            return this;
        }

        public Builder setName(String name) {
            this.name = name;
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

        public Builder setIsDefault(boolean isDefault) {
            this.isDefault = isDefault;
            return this;
        }

        public Builder setStudent(Student student) {
            this.student = student;
            return this;
        }

        public Builder setItems(List<WishlistItem> items) {
            this.items = items;
            return this;
        }

        public Builder addItem(WishlistItem item) {
            if (this.items == null) {
                this.items = new ArrayList<>();
            }
            this.items.add(item);
            return this;
        }

        public Builder copy(Wishlist wishlist) {
            this.wishlistId = wishlist.wishlistId;
            this.name = wishlist.name;
            this.createdAt = wishlist.createdAt;
            this.updatedAt = wishlist.updatedAt;
            this.isDefault = wishlist.isDefault;
            this.student = wishlist.student;
            this.items = wishlist.items;
            return this;
        }

        public Wishlist build() {
            return new Wishlist(this);
        }
    }
}