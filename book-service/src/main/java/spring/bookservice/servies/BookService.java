package spring.bookservice.servies;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.bookservice.model.Book;
import spring.bookservice.repository.BookRepository;


import java.util.List;
import java.util.stream.StreamSupport;


@Service
@RequiredArgsConstructor
public class BookService {
    private final BookRepository repository;

    public Book findById(Long id){
       return repository.findById(id).orElse(null);
    }
    public void addNewBook(String name){
        repository.save(new Book(name));
    }
    public void deleteById(Long id){
        repository.deleteById(id);
    }
    public List<Book> getAll(){
        return repository.findAll();
    }
    public Book findByName(String name){
        return repository.findByName(name);
    }
}
