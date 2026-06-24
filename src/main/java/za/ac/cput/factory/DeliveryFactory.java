package za.ac.cput.factory;

import za.ac.cput.domain.*;
import za.ac.cput.util.Helper;
import za.ac.cput.util.IdGenerator;

import java.time.LocalDateTime;

public class DeliveryFactory {

    private static IdGenerator idGenerator = new IdGenerator();

    public static Delivery createDelivery(Long orderId, DeliveryMethod method,
                                          CollectionPoint collectionPoint,
                                          Address deliveryAddress,
                                          double deliveryFee) {

        // Validate required fields
        Helper.requireNonNull(orderId, "Order ID");
        Helper.requireNonNull(method, "Delivery Method");
        Helper.requireNotNegative(deliveryFee, "Delivery Fee");

        // Generate ID and tracking number
        Long deliveryId = idGenerator.generateNumericId("DELIVERY");
        String trackingNumber = Helper.generateTrackingNumber();

        return new Delivery.Builder()
                .setDeliveryId(deliveryId)
                .setOrderId(orderId)
                .setMethod(method)
                .setCollectionPoint(collectionPoint)
                .setDeliveryAddress(deliveryAddress)
                .setScheduledDate(LocalDateTime.now())
                .setTrackingNumber(trackingNumber)
                .setDeliveryFee(deliveryFee)
                .build();
    }

    /**
     * Creates a pickup delivery (no delivery address needed)
     */
    public static Delivery createPickupDelivery(Long orderId, CollectionPoint collectionPoint) {
        return createDelivery(orderId, DeliveryMethod.PICKUP, collectionPoint, null, 0.0);
    }

    /**
     * Creates a home delivery (delivery address required)
     */
    public static Delivery createHomeDelivery(Long orderId, Address deliveryAddress, double deliveryFee) {
        return createDelivery(orderId, DeliveryMethod.DELIVERY, null, deliveryAddress, deliveryFee);
    }
}
