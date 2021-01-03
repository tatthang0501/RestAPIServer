package practice.data;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import practice.Student;

public interface StudentRepository extends CrudRepository<Student,Integer> {
    
    @Query(value="SELECT * FROM student WHERE name LIKE ?1",nativeQuery = true)
    public Iterable<Student> findAllByName(String name);
}
