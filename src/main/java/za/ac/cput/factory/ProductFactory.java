package za.ac.cput.factory;

import za.ac.cput.domain.*;
import za.ac.cput.util.Helper;
import za.ac.cput.util.IdGenerator;

import java.time.LocalDateTime;
import java.util.List;

public class ProductFactory {

    private static IdGenerator idGenerator = new IdGenerator();

    public static Product createProduct(Long sellerId, Category category,
                                        String title, String description,
                                        double price, String condition,
                                        List<String> images) {

        // Validate required fields
        Helper.requireNonNull(sellerId, "Seller ID");
        Helper.requireNonNull(category, "Category");
        Helper.requireNotEmptyOrNull(title, "Title");
        Helper.requireNotEmptyOrNull(description, "Description");
        Helper.requireValidPrice(price);
        Helper.requireNotEmptyOrNull(condition, "Condition");

        // Validate description length
        if (description.length() < 20) {
            throw new IllegalArgumentException("Description must be at least 20 characters");
        }

        // Generate ID
        Long productId = idGenerator.generateNumericId("PRODUCT");

        return new Product.Builder()
                .setProductId(productId)
                .setSellerId(sellerId)
                .setCategory(category)
                .setTitle(title)
                .setDescription(description)
                .setPrice(price)
                .setCondition(condition)
                .setImages(images)
                .setStatus(ProductStatus.AVAILABLE)
                .setViewCount(0)
                .setDateListed(LocalDateTime.now())
                .setDateUpdated(LocalDateTime.now())
                .build();
    }

    /**
     * Creates a Product with custom status
     */
    public static Product createProductWithStatus(Long sellerId, Category category,
                                                  String title, String description,
                                                  double price, String condition,
                                                  List<String> images,
                                                  ProductStatus status) {

        Product product = createProduct(sellerId, category, title, description,
                price, condition, images);

        return new Product.Builder()
                .setProductId(product.getProductId())
                .setSellerId(sellerId)
                .setCategory(category)
                .setTitle(title)
                .setDescription(description)
                .setPrice(price)
                .setCondition(condition)
                .setImages(images)
                .setStatus(status)
                .setViewCount(0)
                .setDateListed(LocalDateTime.now())
                .setDateUpdated(LocalDateTime.now())
                .build();
    }
}
