package pl.Documents.MyDocuments;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@SpringBootApplication
// podpięcie pliku mydocuments-context.xml
@ImportResource("classpath:mydocuments-context.xml")
public class MyDocumentsApplication {

	public static void main(String[] args) {
		SpringApplication.run(MyDocumentsApplication.class, args);
	}

}
