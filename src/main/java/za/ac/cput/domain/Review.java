package za.ac.cput.domain;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Review {
    @Id
    private Long reviewId;
    private int rating;
    private String comment;
    private LocalDateTime reviewDate;
    private LocalDateTime lastUpdated;
    private boolean isVerifiedPurchase;

    @ManyToOne
    @JoinColumn(name = "reviewer_id")
    private Student reviewer;

    @ManyToOne
    @JoinColumn(name = "reviewee_id")
    private Student reviewee;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    protected Review() {}

    private Review(Builder builder) {
        this.reviewId = builder.reviewId;
        this.rating = builder.rating;
        this.comment = builder.comment;
        this.reviewDate = builder.reviewDate;
        this.lastUpdated = builder.lastUpdated;
        this.isVerifiedPurchase = builder.isVerifiedPurchase;
        this.reviewer = builder.reviewer;
        this.reviewee = builder.reviewee;
        this.product = builder.product;
        this.order = builder.order;
    }

    // Getters
    public Long getReviewId() { return reviewId; }
    public int getRating() { return rating; }
    public String getComment() { return comment; }
    public LocalDateTime getReviewDate() { return reviewDate; }
    public LocalDateTime getLastUpdated() { return lastUpdated; }
    public boolean isVerifiedPurchase() { return isVerifiedPurchase; }
    public Student getReviewer() { return reviewer; }
    public Student getReviewee() { return reviewee; }
    public Product getProduct() { return product; }
    public Order getOrder() { return order; }

    public void updateReview(String newComment, int newRating) {
        this.comment = newComment;
        this.rating = newRating;
        this.lastUpdated = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "Review{" +
                "reviewId=" + reviewId +
                ", rating=" + rating +
                '}';
    }

    public static class Builder {
        private Long reviewId;
        private int rating;
        private String comment;
        private LocalDateTime reviewDate = LocalDateTime.now();
        private LocalDateTime lastUpdated = LocalDateTime.now();
        private boolean isVerifiedPurchase = false;
        private Student reviewer;
        private Student reviewee;
        private Product product;
        private Order order;

        public Builder setReviewId(Long reviewId) {
            this.reviewId = reviewId;
            return this;
        }

        public Builder setRating(int rating) {
            this.rating = rating;
            return this;
        }

        public Builder setComment(String comment) {
            this.comment = comment;
            return this;
        }

        public Builder setReviewDate(LocalDateTime reviewDate) {
            this.reviewDate = reviewDate;
            return this;
        }

        public Builder setLastUpdated(LocalDateTime lastUpdated) {
            this.lastUpdated = lastUpdated;
            return this;
        }

        public Builder setIsVerifiedPurchase(boolean isVerifiedPurchase) {
            this.isVerifiedPurchase = isVerifiedPurchase;
            return this;
        }

        public Builder setReviewer(Student reviewer) {
            this.reviewer = reviewer;
            return this;
        }

        public Builder setReviewee(Student reviewee) {
            this.reviewee = reviewee;
            return this;
        }

        public Builder setProduct(Product product) {
            this.product = product;
            return this;
        }

        public Builder setOrder(Order order) {
            this.order = order;
            return this;
        }

        public Builder copy(Review review) {
            this.reviewId = review.reviewId;
            this.rating = review.rating;
            this.comment = review.comment;
            this.reviewDate = review.reviewDate;
            this.lastUpdated = review.lastUpdated;
            this.isVerifiedPurchase = review.isVerifiedPurchase;
            this.reviewer = review.reviewer;
            this.reviewee = review.reviewee;
            this.product = review.product;
            this.order = review.order;
            return this;
        }

        public Review build() {
            return new Review(this);
        }
    }
}