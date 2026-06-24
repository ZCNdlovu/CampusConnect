package za.ac.cput.factory;

import za.ac.cput.domain.Message;
import za.ac.cput.util.Helper;
import za.ac.cput.util.IdGenerator;

import java.time.LocalDateTime;

public class MessageFactory {

    private static IdGenerator idGenerator = new IdGenerator();

    public static Message createMessage(Long chatRoomId, Long senderId, String content) {

        // Validate required fields
        Helper.requireNonNull(chatRoomId, "Chat Room ID");
        Helper.requireNonNull(senderId, "Sender ID");
        Helper.requireNotEmptyOrNull(content, "Content");

        // Validate content length (max 1000 characters)
        if (content.length() > 1000) {
            throw new IllegalArgumentException("Message content exceeds maximum length of 1000 characters");
        }

        // Generate ID
        Long messageId = idGenerator.generateNumericId("MESSAGE");

        return new Message.Builder()
                .setMessageId(messageId)
                .setChatRoomId(chatRoomId)
                .setSenderId(senderId)
                .setContent(content)
                .setTimestamp(LocalDateTime.now())
                .setIsRead(false)
                .setIsDeleted(false)
                .build();
    }
}
