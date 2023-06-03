package lethanhdat.Lab3.controller;

import lethanhdat.Lab3.entity.Book;
import lethanhdat.Lab3.services.BookService;
import lethanhdat.Lab3.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookController {
    @Autowired
    private BookService bookService;
    @Autowired
    private CategoryService categoryService;
    @GetMapping
    public String showAllBook(Model model) {
        List<Book> books = bookService.getAllBooks();
        model.addAttribute("books", books);
        return "book/list";
    }
    @GetMapping("/add")
    public String addBookForm(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("categories", categoryService.getAllCategories());
        return "book/add";
    }
    @PostMapping("/add")
    public String addBook(@ModelAttribute("book") Book book) {
        bookService.addBook(book);
        return "redirect:/books";
    }
    @GetMapping({"/delete/{id}"})
    public String DeleteBook(@PathVariable("id") Long id) {
        Book book = bookService.getBookById(id);
        if (book != null) {
            // Xoá Book dựa vào Book có Id vừa tìm được
            bookService.deleteBook(id);
            System.out.println("Book đã được xóa");
        } else {
            System.out.println("Book chưa được xóa");
        }
        return "redirect:/books";
    }
}
