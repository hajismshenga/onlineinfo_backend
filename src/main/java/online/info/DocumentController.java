package online.info;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/document")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    @GetMapping("/all")
    public ResponseEntity<List<Document>> getAllDocuments() {
        List<Document> documents = documentService.getAllDocuments();
        return new ResponseEntity<>(documents, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<Document> addDocument(@RequestBody Document document) {
        Document savedDocument = documentService.addDocument(document);
        return new ResponseEntity<>(savedDocument, HttpStatus.CREATED);
    }

    @GetMapping("/documentById/{id}")
    public ResponseEntity<Document> getDocumentById(@PathVariable("id") Long documentId) {
        Document document = documentService.getDocumentById(documentId);
        return new ResponseEntity<>(document, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteDocument(@PathVariable("id") Long documentId) {
        documentService.deleteDocument(documentId);
        return new ResponseEntity<>("Document deleted successfully", HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Document> updateDocument(@PathVariable("id") Long documentId, @RequestBody Document document) {
        document.setId(documentId);
        Document updatedDocument = documentService.updateDocument(document);
        return new ResponseEntity<>(updatedDocument, HttpStatus.CREATED);
    }
}
