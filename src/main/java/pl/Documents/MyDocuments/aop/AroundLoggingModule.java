package pl.Documents.MyDocuments.aop;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

public class AroundLoggingModule implements MethodInterceptor {
    private static final Logger log = LoggerFactory.getLogger(AfterLoggingModule.class);

    public Object invoke(MethodInvocation invocation) throws  Throwable{
        Object result = null;
        if(log.isDebugEnabled()){
            log.debug("@@@@(AROUND-BEFORE) Wywołana metoda: " + invocation.getMethod().getName());
            if (invocation.getArguments().length == 0){
                log.debug("@@@@(AROUND-BEFORE) Nie przekazano argumentów.");
            }
            for(Object arg : invocation.getArguments()){
                log.debug("@@@@(AROUND-BEFORE) Przekazany argument: " + arg);
            }
        }
        try{
            if(log.isDebugEnabled()){
                log.debug("@@@(AROUND-AFTER) Przetwarzanie...");
            }

            result = invocation.proceed();

            if(log.isDebugEnabled()){
                log.debug("@@@(AROUND-AFTER) Wynik:  " +  result);
            }

            return result;
        }catch (IllegalArgumentException ex){
            log.error("@@@(AROUND) Zgłasza wyjątek: " + ex.getMessage());
            throw ex;
        }
    }


}
