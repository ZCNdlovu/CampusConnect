package za.ac.cput.factory;

import za.ac.cput.domain.ApprovalStatus;
import za.ac.cput.domain.Document;
import za.ac.cput.domain.DocumentType;
import za.ac.cput.util.Helper;
import za.ac.cput.util.IdGenerator;

import java.time.LocalDateTime;

public class DocumentFactory {

    private static IdGenerator idGenerator = new IdGenerator();

    public static Document createDocument(Long studentId, DocumentType type,
                                          String fileUrl, String fileName,
                                          Long fileSize) {

        // Validate required fields
        Helper.requireNonNull(studentId, "Student ID");
        Helper.requireNonNull(type, "Document Type");
        Helper.requireNotEmptyOrNull(fileUrl, "File URL");
        Helper.requireNotEmptyOrNull(fileName, "File Name");
        Helper.requireNonNull(fileSize, "File Size");

        // Validate file size (max 5MB = 5,242,880 bytes)
        if (fileSize > 5_242_880) {
            throw new IllegalArgumentException("File size exceeds maximum of 5MB");
        }

        // Generate ID
        Long documentId = idGenerator.generateNumericId("DOCUMENT");

        return new Document.Builder()
                .setDocumentId(documentId)
                .setStudentId(studentId)
                .setType(type)
                .setFileUrl(fileUrl)
                .setFileName(fileName)
                .setFileSize(fileSize)
                .setUploadDate(LocalDateTime.now())
                .setApprovalStatus(ApprovalStatus.PENDING)
                .build();
    }
}
