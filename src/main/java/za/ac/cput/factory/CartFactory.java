package za.ac.cput.factory;

import za.ac.cput.domain.Cart;
import za.ac.cput.util.Helper;
import za.ac.cput.util.IdGenerator;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class CartFactory {

    private static IdGenerator idGenerator = new IdGenerator();

    public static Cart createCart(Long studentId) {

        // Validate required fields
        Helper.requireNonNull(studentId, "Student ID");

        // Generate ID
        Long cartId = idGenerator.generateNumericId("CART");

        return new Cart.Builder()
                .setCartId(cartId)
                .setStudentId(studentId)
                .setCreatedAt(LocalDateTime.now())
                .setUpdatedAt(LocalDateTime.now())
                .setTotalAmount(0.0)
                .setItems(new ArrayList<>())
                .build();
    }
}
