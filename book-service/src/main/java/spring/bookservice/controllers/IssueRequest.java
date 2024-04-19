package spring.bookservice.controllers;

import lombok.Data;

@Data
public class IssueRequest {
    private Long idReader;
    private Long bookId;
}
