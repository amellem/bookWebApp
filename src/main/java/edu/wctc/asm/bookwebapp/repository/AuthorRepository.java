package edu.wctc.asm.bookwebapp.repository;

import edu.wctc.asm.bookwebapp.entity.Author;
import java.io.Serializable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author CloudAerius
 */
@Repository
public interface AuthorRepository extends JpaRepository<Author, Integer>, Serializable{
    
}
