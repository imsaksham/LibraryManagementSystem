package com.intellewings.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.intellewings.model.Book;

@Repository
public interface BookDao extends JpaRepository<Book, Integer> {

	@Query("SELECT COUNT(b) FROM Book b WHERE b.title = :title")
	Integer countByTitle(@Param("title") String title);
	
	@Query("SELECT COUNT(b) FROM Book b WHERE b.author = :author")
	Integer countByAuthor(@Param("author") String author);
	
	@Query("SELECT b FROM Book b WHERE b.title = :title")
    List<Book> findByTitle(@Param("title") String title);
	
	@Query("SELECT b FROM Book b WHERE b.author = :author")
	List<Book> findByAuthor(@Param("author") String author);

}
