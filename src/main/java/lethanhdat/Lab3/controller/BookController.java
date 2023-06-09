package lethanhdat.Lab3.controller;

import jakarta.validation.Valid;
import lethanhdat.Lab3.entity.Book;
import lethanhdat.Lab3.services.BookService;
import lethanhdat.Lab3.services.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
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
    public String addBook(@Valid @ModelAttribute("book") Book book, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("categories", categoryService.getAllCategories());
            return "book/add";
        }

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
    @GetMapping("/edit/{id}")
    public String editBookForm(@PathVariable("id") Long id, Model model) {
        Book book = bookService.getBookById(id);
        if (book != null) {
            model.addAttribute("book", book);
            model.addAttribute("categories", categoryService.getAllCategories());
            return "book/edit";
        } else {
            return "redirect:/books";
        }
    }
    @PostMapping("/edit")
    public String editBook(@ModelAttribute("book") Book updatedBook) {
        Long bookId = updatedBook.getId();

        if (bookId != null) {
            Book existingBook = bookService.getBookById(bookId);

            if (existingBook != null) {
                existingBook.setTitle(updatedBook.getTitle());
                existingBook.setAuthor(updatedBook.getAuthor());
                existingBook.setPrice(updatedBook.getPrice());
                existingBook.setCategory(updatedBook.getCategory());
                bookService.updateBook(existingBook);
            }
        }

        return "redirect:/books";
    }
}