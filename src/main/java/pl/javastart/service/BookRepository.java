package pl.javastart.service;


import org.springframework.stereotype.Component;
import pl.javastart.model.Book;

import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@Component
public class BookRepository implements GenericRepository<String, Book> {

    private List<Book> books;

    public BookRepository() {
        books = new LinkedList<>();
    }

    @Override
    public Book get(String isbn) throws IllegalAccessException {
        if (isbn == null || (isbn.length()!=13))
            throw new IllegalAccessException("You have to provide valid ISBN number");
        Book find = books.stream()
                .filter(b->isbn.equals(b.getIsbn()))
                .findFirst()
                .get();
        randomPause(300);
        return find;
    }

    @Override
    public void add(Book book) throws IllegalAccessException {
        if (book == null || book.getIsbn() == null || book.getTitle() == null || book.getAuthor() == null)
            throw new IllegalAccessException("Book cannot have null fields");
        randomPause(1000);
        books.add(book);
    }

    private void randomPause(int maxTime) {
        try {
            Thread.sleep(new Random().nextInt(maxTime));
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
