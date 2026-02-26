package at.spengergasse.spring_thymeleaf.services;

import at.spengergasse.spring_thymeleaf.entities.Book;
import at.spengergasse.spring_thymeleaf.entities.BookRepository;
import at.spengergasse.spring_thymeleaf.entities.Genre;
import at.spengergasse.spring_thymeleaf.entities.GenreRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    //Schnittstellen zur Datenbank
    private final BookRepository bookRepository;
    private final GenreRepository genreRepository;

    public BookService(BookRepository bookRepository, GenreRepository genreRepository) {
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
        
        // Wenn noch keine Genres da sind wird automatisch ein paar Standard-Genres ertstellt
        if (genreRepository.count() == 0) {
            genreRepository.save(new Genre("Roman"));
            genreRepository.save(new Genre("Sachbuch"));
            genreRepository.save(new Genre("Krimi"));
            genreRepository.save(new Genre("Fantasy"));
            genreRepository.save(new Genre("Biografie"));
        }
    }

    // Holt alle Bücher aus der Datenbank
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    // Holt alle Genres aus der Datenbank
    public List<Genre> getAllGenres() {
        return genreRepository.findAll();
    }

    // Speichert ein Genre
    public void saveGenre(Genre genre) {
        genreRepository.save(genre);
    }

    // Sucht ein Genre anhand seiner ID
    public Genre getGenreById(Integer id) {
        return genreRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Ungültige Genre ID:" + id));
    }

    // Löscht ein Genre
    public void deleteGenreById(Integer id) {
        genreRepository.deleteById(id);
    }

    // Speichert ein Buch
    public void saveBook(Book book) {
        bookRepository.save(book);
    }

    // Sucht ein Buch anhand seiner ID
    public Book getBookById(Integer id) {
        return bookRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Ungültige Buch ID:" + id));
    }

    // Löscht ein Buch
    public void deleteBookById(Integer id) {
        bookRepository.deleteById(id);
    }
}
