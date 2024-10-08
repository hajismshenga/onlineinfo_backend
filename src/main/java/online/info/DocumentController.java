package online.info;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/document")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @PostMapping("/add")
    public ResponseEntity<?> addDocument(@RequestParam("title") String title,
                                          @RequestParam("description") String description,
                                          @RequestParam(value = "file", required = false) MultipartFile file,
                                          @RequestParam("userId") Long userId) {
        try {
            byte[] fileContent = file != null ? file.getBytes() : null;
            documentService.addDocument(title, description, fileContent, userId);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while adding document.");
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updateDocument(@PathVariable Long id,
                                             @RequestParam("title") String title,
                                             @RequestParam("description") String description) {
        try {
            documentService.updateDocument(id, title, description, null);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while updating document.");
        }
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getDocumentById(@PathVariable Long id) {
        try {
            Document document = documentService.getDocumentById(id);
            return ResponseEntity.ok(document);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Document not found.");
        }
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllDocuments() {
        try {
            return ResponseEntity.ok(documentService.getAllDocuments());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while fetching documents.");
        }
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<?> getDocumentsByUserId(@PathVariable Long userId) {
        try {
            List<Document> documents = documentService.getDocumentsByUserId(userId);
            return ResponseEntity.ok(documents);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error occurred while fetching documents.");
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteDocument(@PathVariable Long id) {
        try {
            documentService.deleteDocument(id);
            return ResponseEntity.ok().body("Document deleted successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Document not found.");
        }
    }
}
