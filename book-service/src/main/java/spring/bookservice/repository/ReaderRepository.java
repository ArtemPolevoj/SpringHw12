package spring.bookservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import spring.bookservice.model.Reader;


public interface ReaderRepository extends JpaRepository<Reader, Long> {
    Reader findByName(String name);
}
