package pl.Documents.MyDocuments.aop;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.AfterReturningAdvice;

import java.lang.reflect.Method;

public class AfterLoggingModule implements AfterReturningAdvice {
    private static final Logger log = LoggerFactory.getLogger(AfterLoggingModule.class);

    public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable{
        if(log.isDebugEnabled()){
            log.debug("@@@@(AFTER) Wywołana metoda: "+ method.getName());

            if (args.length == 0){
                log.debug("@@@@(AFTER) Nie przekazano argumentów.");
            }

            for(Object arg : args){
                log.debug("@@@@(AFTER) Przekazany argument: " + arg);
            }
            //Ta linijka wypisuje to, co metoda faktycznie zwróciła do aplikacji.
            log.debug("@@@@(AFTER) Wynik: " + returnValue);
        }
    }
}
