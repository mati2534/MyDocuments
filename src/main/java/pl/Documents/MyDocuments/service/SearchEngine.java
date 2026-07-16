package pl.Documents.MyDocuments.service;

import pl.Documents.MyDocuments.model.Document;
import pl.Documents.MyDocuments.model.Type;

import java.util.List;

public interface SearchEngine {
    public List<Document> findByType(Type documentType);
    public List<Document> listAll();
}
