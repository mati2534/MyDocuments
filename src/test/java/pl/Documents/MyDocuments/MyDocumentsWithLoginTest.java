package pl.Documents.MyDocuments;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pl.Documents.MyDocuments.service.Login;

import static org.junit.jupiter.api.Assertions.assertNotNull;

public class MyDocumentsWithLoginTest {
    private static final Logger log = LoggerFactory.getLogger(MyDocumentsWithLoginTest.class);
    private static final String EMAIL = "test@mydocuments.com";
    private static final String PASS = "test123";
    private static final String SUCCESS = "Ten użytkownik ma autoryzacje";
    private static final String FAILURE = "UWAGA! Ten użytkownik nie ma autoryzacji";
    private ClassPathXmlApplicationContext context;

    @BeforeEach
    public void SetUp() {
        context = new ClassPathXmlApplicationContext("classpath:mydocuments-login-context.xml");
    }

    @Test
    public void testLogin(){
        log.debug("Test logowania");
        Login login = context.getBean(Login.class);
        assertNotNull(login);
        if (login.isAuthorized(EMAIL, PASS)) {
            System.out.println(SUCCESS);
        }else {
            System.out.println(FAILURE);
        }
    }
}
