package za.ac.cput.factory;

import za.ac.cput.domain.WishlistItem;
import za.ac.cput.util.Helper;
import za.ac.cput.util.IdGenerator;

import java.time.LocalDateTime;

public class WishlistItemFactory {

    private static IdGenerator idGenerator = new IdGenerator();

    public static WishlistItem createWishlistItem(Long wishlistId, Long productId,
                                                  boolean notifyOnPriceDrop,
                                                  Double targetPrice) {

        // Validate required fields
        Helper.requireNonNull(wishlistId, "Wishlist ID");
        Helper.requireNonNull(productId, "Product ID");

        // Validate target price if notification is enabled
        if (notifyOnPriceDrop && (targetPrice == null || targetPrice <= 0)) {
            throw new IllegalArgumentException("Target price must be specified and positive for price drop notification");
        }

        // Generate ID
        Long wishlistItemId = idGenerator.generateNumericId("WISHLIST_ITEM");

        return new WishlistItem.Builder()
                .setWishlistItemId(wishlistItemId)
                .setWishlistId(wishlistId)
                .setProductId(productId)
                .setDateAdded(LocalDateTime.now())
                .setNotifyOnPriceDrop(notifyOnPriceDrop)
                .setTargetPrice(targetPrice)
                .build();
    }

    /**
     * Creates a wishlist item without price drop notification
     */
    public static WishlistItem createSimpleWishlistItem(Long wishlistId, Long productId) {
        return createWishlistItem(wishlistId, productId, false, null);
    }
}
