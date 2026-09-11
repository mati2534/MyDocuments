package pl.Documents.MyDocuments.aop;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.MethodBeforeAdvice;

import java.lang.reflect.Method;

//Jej zadaniem jest automatyczne logowanie informacji o wywoływanych metodach zanim właściwa metoda w ogóle się rozpocznie.

public class BeforeLoggingModule implements MethodBeforeAdvice{
    private static final Logger log = LoggerFactory.getLogger(BeforeLoggingModule.class);


    //Method method – obiekt reprezentujący metodę, która za chwilę zostanie wykonana.
    //Object[] args – tablica argumentów, z jakimi ta metoda została wywołana.
    //Object target – obiekt (komponent/bean), na rzecz którego ta metoda została wywołana.
    public void before(Method method, Object[] args, Object target) throws Throwable{
        if(log.isDebugEnabled()){
            log.debug("@@@@(BEFORE) Wywołanna metoda: " + method.getName());

            if(args.length == 0){
                log.debug("@@@@(BEFORE) Nie przekazano argumentów.");
            }

            //Przechodzi przez każdy argument przekazany do metody (np. szukaną frazę lub ID dokumentu)
            for(Object arg : args){
                log.debug("@@@@(BEFORE) Przekazany argument: " + arg);
            }
        }
    }


}
