package at.spengergasse.spring_thymeleaf.controllers;

import at.spengergasse.spring_thymeleaf.entities.Book;
import at.spengergasse.spring_thymeleaf.services.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/book")
public class BookController {
    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/list")
    public String books(Model model) {
        model.addAttribute("books", bookService.getAllBooks());
        return "booklist";
    }

    @GetMapping("/add")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("genres", bookService.getAllGenres());
        return "add_book";
    }

    @PostMapping("/add")
    public String addBook(@ModelAttribute("book") Book book) {
        bookService.saveBook(book);
        return "redirect:/book/list";
    }

    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("book", bookService.getBookById(id));
        model.addAttribute("genres", bookService.getAllGenres());
        return "add_book";
    }

    @GetMapping("/delete/{id}")
    public String deleteBook(@PathVariable("id") Integer id) {
        bookService.deleteBookById(id);
        return "redirect:/book/list";
    }
}
