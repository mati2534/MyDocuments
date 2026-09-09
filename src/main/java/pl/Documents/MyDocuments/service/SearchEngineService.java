package pl.Documents.MyDocuments.service;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;
import pl.Documents.MyDocuments.data.DocumentDAO;
import pl.Documents.MyDocuments.model.Document;
import pl.Documents.MyDocuments.model.Type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class SearchEngineService implements SearchEngine{

    //Logger - typ obiektu odpowiedzialnego za zapisywanie komunikatów w logach.
    //LoggerFactory.getLogger(SearchEngineService.class) — tworzy/pobiera logger przypisany do tej klasy. Dzięki temu w logu wiadomo, skąd pochodzi komunikat.
    //private static final Logger log = LoggerFactory.getLogger(SearchEngineService.class);
    private DocumentDAO documentDAO;

    public SearchEngineService() {

    }

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
        List<Document> result = Arrays.asList(documentDAO.getAll());
        return result;
    }

    public List<Document> findByLocation(String location){
        throw new UnsupportedOperationException("Metoda findByLocation not implemented.");
    }
}
