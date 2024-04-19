package spring.bookservice.controllers;

import lombok.RequiredArgsConstructor;
import org.slf4j.event.Level;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import spring.bookservice.model.Book;
import spring.bookservice.servies.BookService;
import spring.timerstarter.Timer;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequiredArgsConstructor
@RequestMapping("book")
public class BookController {
    private final BookService service;
    @Timer
    @GetMapping("{id}")
    public ResponseEntity<Book> findById(@PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping()
    public ResponseEntity<List<Book>> getAll() {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Book> delete(@PathVariable Long id) {
        service.deleteById(id);
        try {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(service.findById(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<Book> createNewBook(@RequestBody String name) {
        service.addNewBook(name);
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(service.findByName(name));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
