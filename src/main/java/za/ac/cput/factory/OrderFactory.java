package za.ac.cput.factory;

import za.ac.cput.domain.Order;
import za.ac.cput.domain.OrderStatus;
import za.ac.cput.util.Helper;
import za.ac.cput.util.IdGenerator;

import java.time.LocalDateTime;

public class OrderFactory {

    private static IdGenerator idGenerator = new IdGenerator();

    public static Order createOrder(Long buyerId, double totalAmount,
                                    String specialInstructions) {

        // Validate required fields
        Helper.requireNonNull(buyerId, "Buyer ID");
        Helper.requirePositive(totalAmount, "Total Amount");

        // Generate ID and reference
        Long orderId = idGenerator.generateNumericId("ORDER");
        String orderReference = Helper.generateOrderReference();

        return new Order.Builder()
                .setOrderId(orderId)
                .setBuyerId(buyerId)
                .setOrderReference(orderReference)
                .setOrderDate(LocalDateTime.now())
                .setTotalAmount(totalAmount)
                .setStatus(OrderStatus.PENDING_PAYMENT)
                .setSpecialInstructions(specialInstructions)
                .build();
    }

    public static Order createOrderWithStatus(Long buyerId, double totalAmount,
                                              String specialInstructions,
                                              OrderStatus status) {
        Order order = createOrder(buyerId, totalAmount, specialInstructions);

        return new Order.Builder()
                .setOrderId(order.getOrderId())
                .setBuyerId(buyerId)
                .setOrderReference(order.getOrderReference())
                .setOrderDate(order.getOrderDate())
                .setTotalAmount(totalAmount)
                .setStatus(status)
                .setSpecialInstructions(specialInstructions)
                .build();
    }
}
