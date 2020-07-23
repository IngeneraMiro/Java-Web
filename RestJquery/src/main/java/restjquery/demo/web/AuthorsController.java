package restjquery.demo.web;


import com.google.gson.Gson;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;
import restjquery.demo.model.Author;
import restjquery.demo.repository.AuthorRepository;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "*")
public class AuthorsController implements AuthorsNamespace {

  private final AuthorRepository authorRepository;
  private final Gson gson;

  public AuthorsController(AuthorRepository authorRepository, Gson gson) {
    this.authorRepository = authorRepository;
    this.gson = gson;
  }


  @GetMapping
  public List<Author> getAuthors() {
    return authorRepository.findAll();
  }

  @GetMapping("/{authorId}")
  public ResponseEntity<Author> getAuthor(@PathVariable Long authorId) {
    Optional<Author> theAuthor = authorRepository.findById(authorId);

    return theAuthor.map(ResponseEntity::ok).orElseGet(()-> ResponseEntity.notFound().build());
  }

  @PostMapping
  public ResponseEntity<Author> create(
      UriComponentsBuilder ucBuilder,
      @RequestBody Author author
  ) {
    Author newAuthor = authorRepository.save(author);
    return ResponseEntity.
        created(ucBuilder.path("/authors/{authorId}").buildAndExpand(newAuthor.getId()).toUri()).build();
  }

  @DeleteMapping("/{authorId}")
  public ResponseEntity<Author> delete(@PathVariable Long authorId) {
    authorRepository.deleteById(authorId);
    return ResponseEntity.noContent().build();
  }
}
