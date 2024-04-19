package spring.bookservice.servies;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import spring.bookservice.model.Issue;
import spring.bookservice.model.Reader;
import spring.bookservice.repository.IssueRepository;
import spring.bookservice.repository.ReaderRepository;


import java.util.List;

@Service
@RequiredArgsConstructor
public class ReaderService {
    private final ReaderRepository readerRepository;
    private final IssueRepository issueRepository;
    public Reader findById(Long id){
        return readerRepository.findById(id).orElse(null);
    }
    public void addNewReader(String name){
        readerRepository.save(new Reader(name));
    }
    public void deleteById(Long id){
        readerRepository.deleteById(id);
    }
    public List<Reader> getAll(){
        return  readerRepository.findAll();
    }
    public Reader findByName(String name){
        return readerRepository.findByName(name);
    }

    public List<Issue> findIssue(Long id) {
        return issueRepository.findIssueAllByIdReader(id);
    }
}
