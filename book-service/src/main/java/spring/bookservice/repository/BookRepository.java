package spring.bookservice.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import spring.bookservice.model.Book;

public interface BookRepository extends JpaRepository<Book, Long> {
    Book findByName(String name);
}
