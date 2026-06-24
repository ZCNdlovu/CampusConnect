package za.ac.cput.factory;

import za.ac.cput.domain.CartItem;
import za.ac.cput.util.Helper;
import za.ac.cput.util.IdGenerator;

public class CartItemFactory {

    private static IdGenerator idGenerator = new IdGenerator();

    public static CartItem createCartItem(Long cartId, Long productId,
                                          int quantity, double priceAtAdd) {

        // Validate required fields
        Helper.requireNonNull(cartId, "Cart ID");
        Helper.requireNonNull(productId, "Product ID");
        Helper.requireValidQuantity(quantity);
        Helper.requirePositive(priceAtAdd, "Price");

        // Generate ID
        Long cartItemId = idGenerator.generateNumericId("CART_ITEM");

        return new CartItem.Builder()
                .setCartItemId(cartItemId)
                .setCartId(cartId)
                .setProductId(productId)
                .setQuantity(quantity)
                .setPriceAtAdd(priceAtAdd)
                .build();
    }
}
