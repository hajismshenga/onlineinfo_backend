package online.info;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Document {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Ensure the id is of type Long to match with repository

    private String title;
    private String description;
    private byte[] fileContent; // This can be null if no file is provided

    private Long userId; // Add this field if you need to filter by user
}
