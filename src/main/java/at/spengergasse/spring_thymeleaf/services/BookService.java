package at.spengergasse.spring_thymeleaf.services;

import at.spengergasse.spring_thymeleaf.entities.Book;
import at.spengergasse.spring_thymeleaf.entities.BookRepository;
import at.spengergasse.spring_thymeleaf.entities.Genre;
import at.spengergasse.spring_thymeleaf.entities.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;

    public BookService(BookRepository bookRepository, GenreRepository genreRepository) {
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
        

        if (genreRepository.count() == 0) {
            genreRepository.save(new Genre("Roman"));
            genreRepository.save(new Genre("Sachbuch"));
            genreRepository.save(new Genre("Krimi"));
            genreRepository.save(new Genre("Fantasy"));
            genreRepository.save(new Genre("Biografie"));
        }
    }

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    public Book getBookById(Integer id) {
        return bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid book Id:" + id));
    }

    public void deleteBookById(Integer id) {
        bookRepository.deleteById(id);
    }
}
