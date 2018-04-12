package at.refugeescode.mp12socialnetworkFakebook.persistence.repository;

import at.refugeescode.mp12socialnetworkFakebook.persistence.model.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long>{
}
