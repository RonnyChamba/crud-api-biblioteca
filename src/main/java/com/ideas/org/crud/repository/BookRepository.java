package com.ideas.org.crud.repository;

import com.ideas.org.crud.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BookRepository extends JpaRepository<Book, Integer> {

    boolean existsByTitleIgnoreCaseAndIsbn(String title, String isbn);

    @Query("select bo from Book  bo join fetch  bo.category")
    List<Book> findAllFetchCategory();

    @Query("select bo from Book  bo join fetch  bo.category ca where  ca.ide =?1")
    List<Book> findAllFetchByCategory(Integer ide);


}
