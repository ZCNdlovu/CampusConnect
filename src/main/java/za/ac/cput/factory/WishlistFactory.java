package za.ac.cput.factory;

import za.ac.cput.domain.Wishlist;
import za.ac.cput.util.Helper;
import za.ac.cput.util.IdGenerator;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class WishlistFactory {

    private static IdGenerator idGenerator = new IdGenerator();

    public static Wishlist createWishlist(Long studentId, String name) {

        // Validate required fields
        Helper.requireNonNull(studentId, "Student ID");
        Helper.requireNotEmptyOrNull(name, "Wishlist Name");

        // Generate ID
        Long wishlistId = idGenerator.generateNumericId("WISHLIST");

        return new Wishlist.Builder()
                .setWishlistId(wishlistId)
                .setStudentId(studentId)
                .setCreatedAt(LocalDateTime.now())
                .setName(name)
                .setItems(new ArrayList<>())
                .build();
    }
}
