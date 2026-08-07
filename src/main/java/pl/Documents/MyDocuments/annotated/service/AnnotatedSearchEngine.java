package pl.Documents.MyDocuments.annotated.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;
import pl.Documents.MyDocuments.model.Document;
import pl.Documents.MyDocuments.model.Type;
import pl.Documents.MyDocuments.service.SearchEngine;
import pl.Documents.MyDocuments.data.DocumentDAO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Spring automatycznie tworzy obiekt tej klasy i rejestruje go pod nazwą engine
@Service("engine") // to samo co w xml =  <bean id="engine" class="pl.Documents.MyDocuments.annotated.service.AnnotatedSearchEngine"/>
//użycie sposobu konfiguracji springa: annotations.
@Profile("annotations")
public class AnnotatedSearchEngine implements SearchEngine {
    @Autowired // to samo co w xml = <property name="documentDAO" ref="documentDAO"/>
    private DocumentDAO documentDAO;


    public List<Document> findByType(Type documentType) {
        List<Document> result = new ArrayList<Document>();
        for(Document doc : listAll()){
            if(doc.getType().getName().equals(documentType.getName()))
                result.add(doc);
        }
        return result;
    }


    public List<Document> listAll() {
        return Arrays.asList(documentDAO.getAll());
    }
}
