package restjquery.demo.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.gson.annotations.Expose;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="authors")
public class Author {

  @Id
  @Expose
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name="id", nullable = false, updatable = false)
  private long id;

  @Column(nullable = false)
  @Expose
  private String name;


  @JsonIgnore
  @OneToMany(mappedBy = "author",
         cascade = CascadeType.ALL,
      fetch = FetchType.EAGER)
  private List<Book> books = new ArrayList<>();

  public long getId() {
    return id;
  }

  public Author setId(long id) {
    this.id = id;
    return this;
  }

  public String getName() {
    return name;
  }

  public Author setName(String name) {
    this.name = name;
    return this;
  }

  public List<Book> getBooks() {
    return books;
  }

  public Author setBooks(List<Book> books) {
    this.books = books;
    return this;
  }
}
