package pl.Documents.MyDocuments.service;

import pl.Documents.MyDocuments.data.DocumentDAO;
import pl.Documents.MyDocuments.model.Document;
import pl.Documents.MyDocuments.model.Type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchEngineService implements SearchEngine{

    private DocumentDAO documentDAO;

    public DocumentDAO getDocumentDAO(){
        return documentDAO;
    }

    public void setDocumentDAO(DocumentDAO documentDAO) {
        this.documentDAO = documentDAO;
    }

    public List<Document> findByType(Type documentType) {
           List<Document> result = new ArrayList<>();
           //pobiera wszystkie dokumenty przez listAll(), sprawdza ich typ i zwraca listę tylko tych, których nazwa typu jest taka sama jak podana w parametrze.
           for(Document doc : listAll()){
               if(doc.getType().getName().equals(documentType.getName()))
                   result.add(doc);
           }
           return result;
    }
    public List<Document> listAll(){
        //Arrays.asList(...) tworzy liste z tablicy getAll()
        return Arrays.asList(documentDAO.getAll());
    }
}
