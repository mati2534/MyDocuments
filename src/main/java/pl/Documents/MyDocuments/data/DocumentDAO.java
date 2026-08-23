package pl.Documents.MyDocuments.data;

import pl.Documents.MyDocuments.model.Document;

public interface DocumentDAO {
        //zawartość w mydocuments-context.xml
        public Document[] getAll();
}
