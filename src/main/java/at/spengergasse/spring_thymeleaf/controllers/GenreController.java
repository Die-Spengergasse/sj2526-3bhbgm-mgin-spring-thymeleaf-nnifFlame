package at.spengergasse.spring_thymeleaf.controllers;

import at.spengergasse.spring_thymeleaf.entities.Genre;
import at.spengergasse.spring_thymeleaf.services.BookService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/genre")
public class GenreController {
    private final BookService bookService;

    public GenreController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping("/list")
    public String listGenres(Model model) {
        model.addAttribute("genres", bookService.getAllGenres());
        return "genrelist";
    }

    @GetMapping("/add")
    public String addGenre(Model model) {
        model.addAttribute("genre", new Genre());
        return "add_genre";
    }

    @PostMapping("/add")
    public String saveGenre(@ModelAttribute("genre") Genre genre) {
        bookService.saveGenre(genre);
        return "redirect:/genre/list";
    }

    @GetMapping("/edit/{id}")
    public String editGenre(@PathVariable("id") Integer id, Model model) {
        model.addAttribute("genre", bookService.getGenreById(id));
        return "add_genre";
    }

    @GetMapping("/delete/{id}")
    public String deleteGenre(@PathVariable("id") Integer id) {
        bookService.deleteGenreById(id);
        return "redirect:/genre/list";
    }
}
