package za.ac.cput.factory;

import za.ac.cput.domain.Notification;
import za.ac.cput.domain.NotificationType;
import za.ac.cput.util.Helper;
import za.ac.cput.util.IdGenerator;

import java.time.LocalDateTime;

public class NotificationFactory {

    private static IdGenerator idGenerator = new IdGenerator();

    public static Notification createNotification(Long recipientId, NotificationType type,
                                                  String title, String message,
                                                  String actionUrl) {

        // Validate required fields
        Helper.requireNonNull(recipientId, "Recipient ID");
        Helper.requireNonNull(type, "Notification Type");
        Helper.requireNotEmptyOrNull(title, "Title");
        Helper.requireNotEmptyOrNull(message, "Message");

        // Generate ID
        Long notificationId = idGenerator.generateNumericId("NOTIFICATION");

        return new Notification.Builder()
                .setNotificationId(notificationId)
                .setRecipientId(recipientId)
                .setType(type)
                .setTitle(title)
                .setMessage(message)
                .setIsRead(false)
                .setCreatedAt(LocalDateTime.now())
                .setActionUrl(actionUrl)
                .build();
    }

    /**
     * Creates an order update notification
     */
    public static Notification createOrderUpdateNotification(Long recipientId, String orderReference, String status) {
        String title = "Order Update";
        String message = "Your order " + orderReference + " status has been updated to: " + status;
        String actionUrl = "/orders/" + orderReference;

        return createNotification(recipientId, NotificationType.ORDER_UPDATE, title, message, actionUrl);
    }

    /**
     * Creates a price drop notification
     */
    public static Notification createPriceDropNotification(Long recipientId, String productTitle, double newPrice) {
        String title = "Price Drop Alert!";
        String message = productTitle + " has dropped to R" + newPrice;
        String actionUrl = "/products";

        return createNotification(recipientId, NotificationType.PRICE_DROP, title, message, actionUrl);
    }
}
