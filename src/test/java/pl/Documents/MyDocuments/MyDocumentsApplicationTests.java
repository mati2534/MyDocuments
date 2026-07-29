package pl.Documents.MyDocuments;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import pl.Documents.MyDocuments.model.Document;
import pl.Documents.MyDocuments.model.Type;
import pl.Documents.MyDocuments.service.MySearchEngine;
import pl.Documents.MyDocuments.service.SearchEngine;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class MyDocumentsApplicationTests {

	private SearchEngine engine = new MySearchEngine();

	@Test
	public void testFindByType() {
		Type documentType = new Type();
		documentType.setName("WEB");
		documentType.setDesc("Łącze sieciowe");
		documentType.setExtension(".url");


		List<Document> documents = engine.findByType(documentType);
		assertNotNull(documents);
		//sprawdza czy documents posiada 1 element
		assertTrue(documents.size() == 1);
		assertEquals(documentType.getName(), documents.get(0).getType().getName());
		assertEquals(documentType.getDesc(), documents.get(0).getType().getDesc());
		assertEquals(documentType.getExtension(), documents.get(0).getType().getExtension());
	}

	@Test
	public void testListAll(){
		List<Document> documents = engine.listAll();
		assertNotNull(documents);
		assertTrue(documents.size() == 4);
	}

}
