package com.example.demo.services;

import com.example.demo.domain.Author;
import com.example.demo.domain.User;
import com.example.demo.dto.request.RequestAuthorDto;
import com.example.demo.dto.response.ResponseAuthorDto;
import com.example.demo.dto.response.ResponseBookDto;
import com.example.demo.mapper.AuthorMapper;
import com.example.demo.mapper.BookMapper;
import com.example.demo.repositories.AuthorRepository;
import com.example.demo.repositories.BookRepository;
import com.example.demo.resources.resources_impl.UserResourceImpl;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Log4j2
public class AuthorService {

    private final AuthorMapper authorMapper;
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    //todo add loging
    public List<ResponseAuthorDto> getAllAuthors() {
        return authorRepository.findAll().stream()
                .map(author -> authorMapper.fromAuthorToResponseAuthorDto(author))
                .collect(Collectors.toList());
    }

    @Transactional
    public ResponseAuthorDto upsertAuthor(RequestAuthorDto requestAuthorDto) {
        log.info("AuthorService.upsertAuthor -> Author {} | {} | {}",
                requestAuthorDto.getAuthorId(), requestAuthorDto.getAuthorFirstName(), requestAuthorDto.getAuthorSecondName());
        if (requestAuthorDto.getAuthorId() == null) {
            Author author = authorMapper.fromRequestAuthorDtoToAuthor(requestAuthorDto);
            log.info("AuthorService.upsertAuthor -> Author {} | {} | {}",
                    requestAuthorDto.getAuthorId(), requestAuthorDto.getAuthorFirstName(), requestAuthorDto.getAuthorSecondName());
            return authorMapper.fromAuthorToResponseAuthorDto(
                    authorRepository.save(author));
        } else {
            Optional<Author> author = authorRepository.
                    findById(authorMapper.fromRequestAuthorDtoToAuthor(requestAuthorDto).getAuthorId());//todo потом добавить эксепшены для неправильного ид
            Author currentAuthor = author.get(); //todo разобраться с ifPresent
            log.info("AuthorService.upsertAuthor -> Author {} | {} | {}",
                    requestAuthorDto.getAuthorId(), requestAuthorDto.getAuthorFirstName(), requestAuthorDto.getAuthorSecondName());
            currentAuthor.setAuthorFirstName(requestAuthorDto.getAuthorFirstName());
            currentAuthor.setAuthorSecondName(requestAuthorDto.getAuthorSecondName());

            return authorMapper.fromAuthorToResponseAuthorDto(authorRepository.save(currentAuthor));
        }
    }

    public ResponseAuthorDto getAuthorById(@PathVariable String authorId) {
        return authorMapper.fromAuthorToResponseAuthorDto(
                authorRepository.findById(UUID.fromString(authorId)).get());
    }

    public List<ResponseBookDto> getAuthorsBookList(@PathVariable String authorId) {
        return bookRepository.findAllByAuthorId(UUID.fromString(authorId)).stream()
                .map(book -> bookMapper.toResponseBookDto(book))
                .collect(Collectors.toList());
    }
}