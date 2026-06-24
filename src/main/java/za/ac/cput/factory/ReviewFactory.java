package za.ac.cput.factory;

import za.ac.cput.domain.Review;
import za.ac.cput.util.Helper;
import za.ac.cput.util.IdGenerator;

import java.time.LocalDateTime;

public class ReviewFactory {

    private static IdGenerator idGenerator = new IdGenerator();

    public static Review createReview(Long reviewerId, Long revieweeId,
                                      Long productId, int rating,
                                      String comment) {

        // Validate required fields
        Helper.requireNonNull(reviewerId, "Reviewer ID");
        Helper.requireNonNull(revieweeId, "Reviewee ID");
        Helper.requireNonNull(productId, "Product ID");
        Helper.requireValidRating(rating);
        Helper.requireNotEmptyOrNull(comment, "Comment");

        // Generate ID
        Long reviewId = idGenerator.generateNumericId("REVIEW");

        return new Review.Builder()
                .setReviewId(reviewId)
                .setReviewerId(reviewerId)
                .setRevieweeId(revieweeId)
                .setProductId(productId)
                .setRating(rating)
                .setComment(comment)
                .setDate(LocalDateTime.now())
                .setIsFlagged(false)
                .build();
    }

    /**
     * Validates the review before creation
     */
    public static Review createValidatedReview(Long reviewerId, Long revieweeId,
                                               Long productId, int rating,
                                               String comment) {

        Review review = createReview(reviewerId, revieweeId, productId, rating, comment);

        if (!review.validateReview()) {
            throw new IllegalArgumentException("Review validation failed");
        }

        return review;
    }
}
