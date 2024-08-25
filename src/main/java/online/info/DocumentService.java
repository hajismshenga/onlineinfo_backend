package online.info;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DocumentService {

    @Autowired
    private DocumentRepository documentRepository;

    public void addDocument(String title, String description, byte[] fileContent) {
        Document document = new Document();
        document.setTitle(title);
        document.setDescription(description);
        document.setFileContent(fileContent); // This can be null if no file is provided
        documentRepository.save(document);
    }

    public void updateDocument(Long id, String title, String description, byte[] fileContent) {
        Document document = documentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Document not found"));
        document.setTitle(title);
        document.setDescription(description);
        document.setFileContent(fileContent); // This can be null if no file is provided
        documentRepository.save(document);
    }

    public Document getDocumentById(Long id) {
        return documentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Document not found"));
    }

    public List<Document> getAllDocuments() {
        return documentRepository.findAll();
    }

    public void deleteDocument(Long id) {
        documentRepository.deleteById(id);
    }
}
