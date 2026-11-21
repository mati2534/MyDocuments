package pl.Documents.MyDocuments.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import pl.Documents.MyDocuments.model.Type;

import java.util.HashMap;
import java.util.Map;

//Klasa CachingModule to klasyczny interceptor (przechwytywacz) z interfejsu AOP Alliance, który realizuje dokładnie tę samą logikę buforowania  wyników co caching.java dla metody findByType,
// ale bez użycia adnotacji AspectJ (@Aspect, @Around).
//Zamiast deklaratywnego aspektu wykorzystuje programistyczne podejście z niższego poziomu Spring AOP (używane często w starszych konfiguracjach opartych o ProxyFactoryBean lub pliki XML).

//dodaje do aplikacji prosty mechanizm buforowania (cache'owania) wyników wyszukiwania. Dzięki niej program nie musi wielokrotnie wykonywać tej samej, potencjalnie kosztownej operacji
public class CachingModule implements MethodInterceptor {
    private static final Logger log = LoggerFactory.getLogger(CachingModule.class);
    private static final Map<String, Object> cache = new HashMap<String, Object>();

    public Object invoke(MethodInvocation invocation) throws Throwable {
        Object result = null;
        Type documentType = null;

        log.debug("@@@(Buforowanie) sprawdza, czy to wywołanie można buforować... " );
        if("findByType".equals(invocation.getMethod().getName()) &&
                                            invocation.getArguments().length == 1  &&
                                            invocation.getArguments()[0] instanceof Type){
            documentType = (Type) invocation.getArguments()[0];
            log.debug("@@@(Buforowanie) Można buforować!");

            if(cache.containsKey(documentType.getName())){
                log.debug("@@@(Buforowanie) Znaleziono w buforze!");
                return cache.get(documentType.getName());
            }
            log.debug("@@@(Buforowanie) Nie znaleziono! Ale można buforować!");
            result = invocation.proceed();
            cache.put(documentType.getName(), result);
            return result;
        }
        return invocation.proceed();
    }

//1. Implementacja MethodInterceptor: Nadpisuje metodę invoke(MethodInvocation invocation), która pełni tu identyczną rolę jak porada @Around z obiektem ProceedingJoinPoint.
//2. Sprawdzenie wywołania: Pobiera metadane przez invocation.getMethod().getName() oraz tablicę argumentów przez invocation.getArguments(), weryfikując, czy wywołano findByType(Type).
//3. Obsługa pamięci podręcznej (HashMap): Jeśli wynik dla documentType.getName() jest już w mapie cache, zwraca go od razu; w przeciwnym razie uruchamia oryginalną metodę przez invocation.proceed() i zapisuje rezultat w mapie.
//4. Różnica względem poprzedniej klasy: Zamiast bezpiecznej wielowątkowo mapy ConcurrentHashMap zastosowano tu zwykłą HashMap (co przy jednoczesnych żądaniach od wielu użytkowników może powodować błędy współbieżności), a także brakuje nawiasu zamykającego } na samym końcu pliku.

    
}
