package com.example.BookStore.domain;
import org.springframework.data.repository.CrudRepository;
import com.example.BookStore.*;

import java.util.List;

public interface BookRepository extends CrudRepository<Book, Long>{
    List<Book> findByIsbn(String Isbn);
    Book findBooksById(Long Id);
   /* Book modifyById(Long Id); */
}
