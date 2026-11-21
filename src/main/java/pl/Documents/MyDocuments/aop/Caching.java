package pl.Documents.MyDocuments.aop;

import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import pl.Documents.MyDocuments.model.Type;
import  org.aspectj.lang.ProceedingJoinPoint;


import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

//Klasa Caching to aspekt (AOP) w Springu, który implementuje prosty mechanizm pamięci podręcznej (cache) dla metody findByType z serwisu SearchEngine,
// unikając wielokrotnego wykonywania tych samych zapytań.

@Component
@Aspect
public class Caching {
    public static final Logger log = LoggerFactory.getLogger(Caching.class);
    public static final Map<String, Object> cache = new ConcurrentHashMap<>();

    @Around("execution(* pl.Documents.MyDocuments.service.SearchEngine.*(..))")
    //ProceeedingJoinPoint -  służy do sterowania wykonaniem przechwyconej metody wewnątrz porady typu @Around
    public Object caching(ProceedingJoinPoint pjp) throws Throwable{
        Object result = null;
        Type documentType = null;

        log.debug("@@@(Buforowanie) sprawdza, czy to wywołanie można buforować...");

        //instanceof  - sprawdza w czasie działania programu, czy dany obiekt jest instancją konkretnej klasy
        if("findByType".equals(pjp.getSignature().getName()) && pjp.getArgs().length ==1 && pjp.getArgs()[0] instanceof  Type){
            documentType  = (Type)pjp.getArgs()[0];
            log.debug("@@@(Buforowanie) Można buforować!!");
            if(cache.containsKey(documentType.getName())){
                log.debug("@@@(Buforowanie) Znaleziono w buforze!");
                return  cache.get(documentType.getName());
            }
            log.debug("@@@(Buforowanie) Nie znaleziono! Ale można buforować!");
            result = pjp.proceed();
            cache.put(documentType.getName(), result);
            return result;
        }
        return pjp.proceed();
    }
//1. Przechwycenie wywołania (@Around): Aspekt przechwytuje wszystkie wywołania metod w klasie SearchEngine.
//2. Weryfikacja warunków: Za pomocą ProceedingJoinPoint sprawdza, czy wywołana metoda nazywa się findByType, przyjmuje dokładnie jeden argument i czy ten argument jest typu Type (używając instanceof).
//3. Odczyt z pamięci podręcznej (ConcurrentHashMap): Jeśli nazwa danego typu dokumentu znajduje się już w mapie cache, aspekt zatrzymuje wykonanie oryginalnej metody i natychmiast zwraca zapisany wcześniej wynik.
//4. Zapis do pamięci podręcznej: Jeśli wyniku nie ma w cache, aspekt uruchamia oryginalną metodę przez pjp.proceed(), zapisuje jej rezultat w mapie pod kluczem documentType.getName() i zwraca wynik.
//5. Pominięcie buforowania: Wszystkie inne metody z SearchEngine są uruchamiane normalnie bez zapisywania w pamięci podręcznej.
}
