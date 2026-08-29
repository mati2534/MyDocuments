package pl.Documents.MyDocuments;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.Locale;

public class MyDocumentsI18nTest {
    private static final Logger log = LoggerFactory.getLogger(MyDocumentsI18nTest.class);
    private ClassPathXmlApplicationContext context;

    @BeforeEach
    public void setUp() {
        context = new ClassPathXmlApplicationContext("mydocuments-i18n-context.xml");
    }

    @Test
    public void testMenu(){
        log.debug("Do przetłumaczenia...");
        // 1. id w properties, 2.nie przyjmuje żadnych dynamicznych argumentów, 3.gotowa stała z języka Java nakazująca Springowi szukać tekstu w pliku z polskim sufiksem
        //getMessage() pochodzi z interfejsu MessageSource. Służy do pobierania gotowych tekstów lub komunikatów (np. tłumaczeń, komunikatów o błędach, etykiet z menu) ze skojarzonych z aplikacją plików konfiguracyjnych
        String polish = context.getMessage("main.title", null, new Locale("pl") );
        String spanish = context.getMessage("main.title",null, new Locale("es"));
        System.out.println("Polski: " + polish);
        System.out.println("Spanish: " + spanish);
    }
}
