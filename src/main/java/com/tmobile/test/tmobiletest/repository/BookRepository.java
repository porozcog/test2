package com.tmobile.test.tmobiletest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.tmobile.test.tmobiletest.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer >
{

}
