package spring.bookservice.servies;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import spring.bookservice.controllers.IssueRequest;
import spring.bookservice.model.Issue;
import spring.bookservice.repository.BookRepository;
import spring.bookservice.repository.IssueRepository;
import spring.bookservice.repository.ReaderRepository;

import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;
import java.util.TreeMap;

@Slf4j
@RequiredArgsConstructor
@Service
public class IssueService {
    @Value("${application.issue.max-allowed-books:1}")
    private int maxBook;
    private final BookRepository bookRepository;
    private final IssueRepository issueRepository;
    private final ReaderRepository readerRepository;

    public Issue createIssue(IssueRequest request) {
        if (bookRepository.findById(request.getBookId()) == null) {
            log.info("Не удалось найти книгу с id " + request.getBookId());
            throw new NoSuchElementException("Не удалось найти книгу с id " + request.getBookId());
        }
        if (readerRepository.findById(request.getIdReader()) == null) {
            log.info("Не удалось найти читателя с id " + request.getIdReader());
            throw new NoSuchElementException("Не удалось найти читателя с id " + request.getIdReader());
        }

        if (issueRepository.findCountBooksByIdReader(request.getIdReader()) >= maxBook) {
            log.info("У читателя с id " + request.getIdReader() + " максимальное количество книг на руках.");
            return new Issue(-1L, -1L);
        }

        Issue issue = new Issue(request.getIdReader(), request.getBookId());
        issueRepository.save(issue);
        return issue;
    }

    public TreeMap<String, String> findById(Long id) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        TreeMap<String, String> map = new TreeMap<>();
        Issue issue = issueRepository.findById(id).orElse(null);
        Long idReader = issue.getIdReader();
        Long idBook = issue.getIdBook();
        String nameReader = readerRepository.findById(idReader).orElse(null).getName();
        String nameBook = bookRepository.findById(idBook).orElse(null).getName();
        String issuedAt = issue.getIssuedAt().format(formatter);
        String returnedAt = "-";
        if (issue.getReturnedAt() != null) {
            returnedAt = issue.getReturnedAt().format(formatter);
        }
        map.put("Reader", nameReader);
        map.put("Book", nameBook);
        map.put("issued_at", issuedAt);
        map.put("returned_at", returnedAt);
        return map;
    }

    public void setReturnedAt(Long id) {
        issueRepository.setReturnedAt(id);
    }

}
