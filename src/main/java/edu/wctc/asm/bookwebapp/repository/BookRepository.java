/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.asm.bookwebapp.repository;

import edu.wctc.asm.bookwebapp.entity.Book;
import java.io.Serializable;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author CloudAerius
 */
@Repository
public interface BookRepository extends JpaRepository<Book, Integer>, Serializable{

//    public List<Book> searchByAuthorId(Integer id);
//
//    public List<Book> searchByAuthorIdAndIsbn(Integer id, String bn);
//
//    public List<Book> searchByIsbn(String bn);
//
//    public List<Book> searchByAuthorIdAndTitle(Integer id, String title);
//
//    public List<Book> searchByTitle(String title);
//
//    public List<Book> searchByAuthorIdAndIsbnAndTitle(Integer id, String bn, String title);
//
//    public List<Book> searchByIsbnAndTitle(String bn, String title);
    
}
