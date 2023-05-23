package lethanhdat.Lab3.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import lethanhdat.Lab3.entity.Book;

@Repository
public interface IBookRepository extends JpaRepository<Book, Long> {
}
