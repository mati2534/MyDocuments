package pl.Documents.MyDocuments.data;

import pl.Documents.MyDocuments.model.Type;

public interface TypeDataDAO {
    public Type[] getAll();
    public Type findById(String id);
}
