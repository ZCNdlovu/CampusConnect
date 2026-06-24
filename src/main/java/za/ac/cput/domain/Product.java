package za.ac.cput.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Product {
    @Id
    private Long productId;
    private String title;
    private String description;
    private Double price;
    private String condition;
    private LocalDateTime listingDate;
    private LocalDateTime lastUpdated;
    private boolean isVerified;
    private int viewCount;
    private String deliveryAddress;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Enumerated(EnumType.STRING)
    private ProductStatus status;

    @Enumerated(EnumType.STRING)
    private DeliveryMethod preferredDeliveryMethod;

    @Enumerated(EnumType.STRING)
    private CollectionPoint collectionPoint;

    @ElementCollection
    private List<String> images;

    @ManyToOne
    @JoinColumn(name = "seller_id")
    private Student seller;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    private List<Review> reviews;

    @ManyToMany(mappedBy = "products")
    private List<Order> orders;

    protected Product() {}

    private Product(Builder builder) {
        this.productId = builder.productId;
        this.title = builder.title;
        this.description = builder.description;
        this.price = builder.price;
        this.condition = builder.condition;
        this.listingDate = builder.listingDate;
        this.lastUpdated = builder.lastUpdated;
        this.isVerified = builder.isVerified;
        this.viewCount = builder.viewCount;
        this.deliveryAddress = builder.deliveryAddress;
        this.category = builder.category;
        this.status = builder.status;
        this.preferredDeliveryMethod = builder.preferredDeliveryMethod;
        this.collectionPoint = builder.collectionPoint;
        this.images = builder.images;
        this.seller = builder.seller;
        this.reviews = builder.reviews;
        this.orders = builder.orders;
    }

    // Getters
    public Long getProductId() { return productId; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public Double getPrice() { return price; }
    public String getCondition() { return condition; }
    public LocalDateTime getListingDate() { return listingDate; }
    public LocalDateTime getLastUpdated() { return lastUpdated; }
    public boolean isVerified() { return isVerified; }
    public int getViewCount() { return viewCount; }
    public String getDeliveryAddress() { return deliveryAddress; }
    public Category getCategory() { return category; }
    public ProductStatus getStatus() { return status; }
    public DeliveryMethod getPreferredDeliveryMethod() { return preferredDeliveryMethod; }
    public CollectionPoint getCollectionPoint() { return collectionPoint; }
    public List<String> getImages() { return images; }
    public Student getSeller() { return seller; }
    public List<Review> getReviews() { return reviews; }
    public List<Order> getOrders() { return orders; }

    public void setSeller(Student seller) { this.seller = seller; }
    public void setStatus(ProductStatus status) { this.status = status; }

    public void incrementViewCount() {
        this.viewCount++;
    }

    public void markAsVerified() {
        this.isVerified = true;
        this.lastUpdated = LocalDateTime.now();
    }

    public boolean isAvailable() {
        return this.status == ProductStatus.AVAILABLE;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productId=" + productId +
                ", title='" + title + '\'' +
                ", price=" + price +
                ", category=" + category +
                ", status=" + status +
                '}';
    }

    public static class Builder {
        private Long productId;
        private String title;
        private String description;
        private Double price;
        private String condition;
        private LocalDateTime listingDate = LocalDateTime.now();
        private LocalDateTime lastUpdated = LocalDateTime.now();
        private boolean isVerified = false;
        private int viewCount = 0;
        private String deliveryAddress;
        private Category category;
        private ProductStatus status = ProductStatus.AVAILABLE;
        private DeliveryMethod preferredDeliveryMethod;
        private CollectionPoint collectionPoint;
        private List<String> images;
        private Student seller;
        private List<Review> reviews;
        private List<Order> orders;

        public Builder setProductId(Long productId) {
            this.productId = productId;
            return this;
        }

        public Builder setTitle(String title) {
            this.title = title;
            return this;
        }

        public Builder setDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder setPrice(Double price) {
            this.price = price;
            return this;
        }

        public Builder setCondition(String condition) {
            this.condition = condition;
            return this;
        }

        public Builder setListingDate(LocalDateTime listingDate) {
            this.listingDate = listingDate;
            return this;
        }

        public Builder setLastUpdated(LocalDateTime lastUpdated) {
            this.lastUpdated = lastUpdated;
            return this;
        }

        public Builder setIsVerified(boolean isVerified) {
            this.isVerified = isVerified;
            return this;
        }

        public Builder setViewCount(int viewCount) {
            this.viewCount = viewCount;
            return this;
        }

        public Builder setDeliveryAddress(String deliveryAddress) {
            this.deliveryAddress = deliveryAddress;
            return this;
        }

        public Builder setCategory(Category category) {
            this.category = category;
            return this;
        }

        public Builder setStatus(ProductStatus status) {
            this.status = status;
            return this;
        }

        public Builder setPreferredDeliveryMethod(DeliveryMethod preferredDeliveryMethod) {
            this.preferredDeliveryMethod = preferredDeliveryMethod;
            return this;
        }

        public Builder setCollectionPoint(CollectionPoint collectionPoint) {
            this.collectionPoint = collectionPoint;
            return this;
        }

        public Builder setImages(List<String> images) {
            this.images = images;
            return this;
        }

        public Builder setSeller(Student seller) {
            this.seller = seller;
            return this;
        }

        public Builder setReviews(List<Review> reviews) {
            this.reviews = reviews;
            return this;
        }

        public Builder setOrders(List<Order> orders) {
            this.orders = orders;
            return this;
        }

        public Builder copy(Product product) {
            this.productId = product.productId;
            this.title = product.title;
            this.description = product.description;
            this.price = product.price;
            this.condition = product.condition;
            this.listingDate = product.listingDate;
            this.lastUpdated = product.lastUpdated;
            this.isVerified = product.isVerified;
            this.viewCount = product.viewCount;
            this.deliveryAddress = product.deliveryAddress;
            this.category = product.category;
            this.status = product.status;
            this.preferredDeliveryMethod = product.preferredDeliveryMethod;
            this.collectionPoint = product.collectionPoint;
            this.images = product.images;
            this.seller = product.seller;
            this.reviews = product.reviews;
            this.orders = product.orders;
            return this;
        }

        public Product build() {
            return new Product(this);
        }
    }
}