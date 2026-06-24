package za.ac.cput.util;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Component
public class IdGenerator {

    // Atomic counters for each entity type
    private final AtomicLong studentCounter = new AtomicLong(0);
    private final AtomicLong adminCounter = new AtomicLong(0);
    private final AtomicLong productCounter = new AtomicLong(0);
    private final AtomicLong orderCounter = new AtomicLong(0);
    private final AtomicLong orderItemCounter = new AtomicLong(0);
    private final AtomicLong cartCounter = new AtomicLong(0);
    private final AtomicLong cartItemCounter = new AtomicLong(0);
    private final AtomicLong paymentCounter = new AtomicLong(0);
    private final AtomicLong deliveryCounter = new AtomicLong(0);
    private final AtomicLong deliveryTrackingCounter = new AtomicLong(0);
    private final AtomicLong wishlistCounter = new AtomicLong(0);
    private final AtomicLong wishlistItemCounter = new AtomicLong(0);
    private final AtomicLong reviewCounter = new AtomicLong(0);
    private final AtomicLong chatRoomCounter = new AtomicLong(0);
    private final AtomicLong messageCounter = new AtomicLong(0);
    private final AtomicLong notificationCounter = new AtomicLong(0);
    private final AtomicLong addressCounter = new AtomicLong(0);
    private final AtomicLong documentCounter = new AtomicLong(0);
    private final AtomicLong verificationCounter = new AtomicLong(0);
    private final AtomicLong verificationLogCounter = new AtomicLong(0);
    private final AtomicLong emailNotificationCounter = new AtomicLong(0);
    private final AtomicLong reportCounter = new AtomicLong(0);
    private final AtomicLong refundRequestCounter = new AtomicLong(0);


    public synchronized String generateNextId(String entityType) {
        AtomicLong counter;
        String prefix;

        switch (entityType.toUpperCase()) {
            case "STUDENT":
                counter = studentCounter;
                prefix = "STU";
                break;
            case "ADMIN":
                counter = adminCounter;
                prefix = "ADM";
                break;
            case "PRODUCT":
                counter = productCounter;
                prefix = "PRD";
                break;
            case "ORDER":
                counter = orderCounter;
                prefix = "ORD";
                break;
            case "ORDER_ITEM":
                counter = orderItemCounter;
                prefix = "ORDITM";
                break;
            case "CART":
                counter = cartCounter;
                prefix = "CRT";
                break;
            case "CART_ITEM":
                counter = cartItemCounter;
                prefix = "CRTITM";
                break;
            case "PAYMENT":
                counter = paymentCounter;
                prefix = "PAY";
                break;
            case "DELIVERY":
                counter = deliveryCounter;
                prefix = "DEL";
                break;
            case "DELIVERY_TRACKING":
                counter = deliveryTrackingCounter;
                prefix = "DLVTRK";
                break;
            case "WISHLIST":
                counter = wishlistCounter;
                prefix = "WISH";
                break;
            case "WISHLIST_ITEM":
                counter = wishlistItemCounter;
                prefix = "WSHITM";
                break;
            case "REVIEW":
                counter = reviewCounter;
                prefix = "REV";
                break;
            case "CHAT_ROOM":
                counter = chatRoomCounter;
                prefix = "CHR";
                break;
            case "MESSAGE":
                counter = messageCounter;
                prefix = "MSG";
                break;
            case "NOTIFICATION":
                counter = notificationCounter;
                prefix = "NOT";
                break;
            case "ADDRESS":
                counter = addressCounter;
                prefix = "ADR";
                break;
            case "DOCUMENT":
                counter = documentCounter;
                prefix = "DOC";
                break;
            case "VERIFICATION":
                counter = verificationCounter;
                prefix = "VER";
                break;
            case "VERIFICATION_LOG":
                counter = verificationLogCounter;
                prefix = "VERLOG";
                break;
            case "EMAIL_NOTIFICATION":
                counter = emailNotificationCounter;
                prefix = "EML";
                break;
            case "REPORT":
                counter = reportCounter;
                prefix = "RPT";
                break;
            case "REFUND_REQUEST":
                counter = refundRequestCounter;
                prefix = "REF";
                break;
            default:
                throw new IllegalArgumentException("Invalid entity type: " + entityType);
        }

        long nextValue = counter.incrementAndGet();
        return String.format("%s%06d", prefix, nextValue);
    }

    public synchronized Long generateNumericId(String entityType) {
        AtomicLong counter;

        switch (entityType.toUpperCase()) {
            case "STUDENT":
                counter = studentCounter;
                break;
            case "ADMIN":
                counter = adminCounter;
                break;
            case "PRODUCT":
                counter = productCounter;
                break;
            case "ORDER":
                counter = orderCounter;
                break;
            case "ORDER_ITEM":
                counter = orderItemCounter;
                break;
            case "CART":
                counter = cartCounter;
                break;
            case "CART_ITEM":
                counter = cartItemCounter;
                break;
            case "PAYMENT":
                counter = paymentCounter;
                break;
            case "DELIVERY":
                counter = deliveryCounter;
                break;
            case "DELIVERY_TRACKING":
                counter = deliveryTrackingCounter;
                break;
            case "WISHLIST":
                counter = wishlistCounter;
                break;
            case "WISHLIST_ITEM":
                counter = wishlistItemCounter;
                break;
            case "REVIEW":
                counter = reviewCounter;
                break;
            case "CHAT_ROOM":
                counter = chatRoomCounter;
                break;
            case "MESSAGE":
                counter = messageCounter;
                break;
            case "NOTIFICATION":
                counter = notificationCounter;
                break;
            case "ADDRESS":
                counter = addressCounter;
                break;
            case "DOCUMENT":
                counter = documentCounter;
                break;
            case "VERIFICATION":
                counter = verificationCounter;
                break;
            case "VERIFICATION_LOG":
                counter = verificationLogCounter;
                break;
            case "EMAIL_NOTIFICATION":
                counter = emailNotificationCounter;
                break;
            case "REPORT":
                counter = reportCounter;
                break;
            case "REFUND_REQUEST":
                counter = refundRequestCounter;
                break;
            default:
                throw new IllegalArgumentException("Invalid entity type: " + entityType);
        }

        return counter.incrementAndGet();
    }

    // ==================== INITIALIZATION METHODS ====================

    public void initializeStudentCounter(long initialValue) { studentCounter.set(initialValue); }
    public void initializeAdminCounter(long initialValue) { adminCounter.set(initialValue); }
    public void initializeProductCounter(long initialValue) { productCounter.set(initialValue); }
    public void initializeOrderCounter(long initialValue) { orderCounter.set(initialValue); }
    public void initializeOrderItemCounter(long initialValue) { orderItemCounter.set(initialValue); }
    public void initializeCartCounter(long initialValue) { cartCounter.set(initialValue); }
    public void initializeCartItemCounter(long initialValue) { cartItemCounter.set(initialValue); }
    public void initializePaymentCounter(long initialValue) { paymentCounter.set(initialValue); }
    public void initializeDeliveryCounter(long initialValue) { deliveryCounter.set(initialValue); }
    public void initializeWishlistCounter(long initialValue) { wishlistCounter.set(initialValue); }
    public void initializeReviewCounter(long initialValue) { reviewCounter.set(initialValue); }
    public void initializeChatRoomCounter(long initialValue) { chatRoomCounter.set(initialValue); }
    public void initializeMessageCounter(long initialValue) { messageCounter.set(initialValue); }
    public void initializeNotificationCounter(long initialValue) { notificationCounter.set(initialValue); }
    public void initializeAddressCounter(long initialValue) { addressCounter.set(initialValue); }
    public void initializeDocumentCounter(long initialValue) { documentCounter.set(initialValue); }
}
