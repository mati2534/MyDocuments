package pl.Documents.MyDocuments.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.Documents.MyDocuments.model.Document;

import java.util.List;

public class DocumentRepository implements DocumentDAO {

    private static final Logger log = LoggerFactory.getLogger(DocumentRepository.class);
    private List<Document> documents = null;

    public List<Document> getDocuments() {
        return documents;
    }

    public void setDocuments(List<Document> documents) {
        this.documents = documents;
    }

    public Document[] getAll(){
        if(log.isDebugEnabled())
        {
            log.debug("Początek metody getAll(): ");
        }
        Document[] result = documents.toArray(new Document[documents.size()]);
        if(log.isDebugEnabled())
        {
            log.debug("Koniec metody getAll(): ");
        }
        return result;
    }

}
