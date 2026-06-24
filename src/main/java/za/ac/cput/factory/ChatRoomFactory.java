package za.ac.cput.factory;

import za.ac.cput.domain.ChatRoom;
import za.ac.cput.util.Helper;
import za.ac.cput.util.IdGenerator;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class ChatRoomFactory {

    private static IdGenerator idGenerator = new IdGenerator();

    public static ChatRoom createChatRoom(Long studentOneId, Long studentTwoId) {

        // Validate required fields
        Helper.requireNonNull(studentOneId, "Student One ID");
        Helper.requireNonNull(studentTwoId, "Student Two ID");

        // Validate that students are different
        if (studentOneId.equals(studentTwoId)) {
            throw new IllegalArgumentException("Cannot create chat room with same student");
        }

        // Generate ID
        Long chatRoomId = idGenerator.generateNumericId("CHAT_ROOM");

        return new ChatRoom.Builder()
                .setChatRoomId(chatRoomId)
                .setStudentOneId(studentOneId)
                .setStudentTwoId(studentTwoId)
                .setCreatedAt(LocalDateTime.now())
                .setIsActive(true)
                .setStudentOneUnreadCount(0)
                .setStudentTwoUnreadCount(0)
                .setMessages(new ArrayList<>())
                .build();
    }
}
