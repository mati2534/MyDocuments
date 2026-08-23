package pl.Documents.MyDocuments.data;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.Documents.MyDocuments.model.Type;

import java.util.Map;

public class TypeDataRepository implements TypeDataDAO {

    private static final Logger log = LoggerFactory.getLogger(TypeDataRepository.class);
    private Map<String, Type> types = null;

    public Map<String, Type> getTypes() {
        return types;
    }

    public void setTypes(Map<String, Type> types){
        this.types = types;
    }

//    Otrzymuje parametr id, np. "A1".
//    Jeśli włączone jest logowanie DEBUG, zapisuje informację o rozpoczęciu wyszukiwania wraz z przekazanym ID.
//    Wywołuje types.get(id), czyli pobiera z mapy wartość przypisaną do klucza id (<String> z map).
//    Wynik zapisuje w zmiennej type.
//    - jeśli ID istnieje w mapie  type zawiera znaleziony obiekt Type(<Type> z map);
//    - jeśli ID nie istnieje type będzie miało wartość null.
//    Opcjonalnie zapisuje w logu wynik wyszukiwania.
//    Zwraca znaleziony typ albo null.
    public Type findById(String id){
        if(log.isDebugEnabled())
        {
            log.debug("Początek metody findById: " + id);
        }
        Type type = types.get(id);
        if(log.isDebugEnabled())
        {
            log.debug("koniec metody findById: " + type);
        }
        return type;
    }

    public Type[] getAll(){
        return types.values().toArray(new Type[types.values().size()]);
    }

}
