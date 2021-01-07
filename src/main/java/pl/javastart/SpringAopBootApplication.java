package pl.javastart;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import pl.javastart.model.Book;
import pl.javastart.service.BookRepository;
import pl.javastart.service.GenericRepository;

@SpringBootApplication
@EnableAspectJAutoProxy(proxyTargetClass=true)
public class SpringAopBootApplication {

    public static void main(String[] args) throws IllegalAccessException {
        AnnotationConfigApplicationContext context =
                new AnnotationConfigApplicationContext(SpringAopBootApplication.class);

        GenericRepository<String, Book> repo = context.getBean(BookRepository.class);
        repo.add(new Book("1234567890123", "Pierwsza", "Pierwszy autor"));
        repo.add(new Book("2345678901234", "Druga", "Drugi autor"));
        repo.add(new Book("3456789012345", "Trzecia", "Trzeci autor"));

        Book book = repo.get("1234567890123");

        System.out.println(book);
        context.close();
    }
}
