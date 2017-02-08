/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.wctc.asm.model;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 *
 * @author CloudAerius
 */
public class AuthorService {
    
    public List<Author> getAuthors(){
        return Arrays.asList(
                new Author(100, "Mark Twain", new Date()),
                new Author(200, "William Shakespeare", new Date()),
                new Author(300, "Ernest Hemingway", new Date()),
                new Author(400, "Charles Dickens", new Date())
        );
    }
}
