package at.refugeescode.mp12socialnetworkFakebook.endpoint;

import at.refugeescode.mp12socialnetworkFakebook.persistence.model.Person;
import at.refugeescode.mp12socialnetworkFakebook.persistence.repository.PersonRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/persons")
public class PersonEndpoint {

    private PersonRepository personRepository;

    public PersonEndpoint(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    @GetMapping
    List<Person> findAll() {
        return personRepository.findAll();
    }

    @PostMapping
    Person addNew(@RequestBody Person person) {
        return personRepository.save(person);
    }

    @GetMapping("/{id}")
    Person findOneById(@PathVariable Long id) {
        return personRepository.findById(id)
                .orElse(null);
    }

    @PutMapping("{id1}/friend/{id2}")
    Person addFriend(@PathVariable Long id1, @PathVariable Long id2) {
        Optional<Person> person = personRepository.findById(id1);
        Optional<Person> friend = personRepository.findById(id2);
        if (person.isPresent() && friend.isPresent()) {
            addNewFriend(person, friend);
        }
        return person.get();
    }

    @PutMapping("{id1}/unfriend/{id2}")
    Person unfriend(@PathVariable Long id1, @PathVariable Long id2) {
        Optional<Person> person = personRepository.findById(id1);
        Optional<Person> friend = personRepository.findById(id2);
        if (person.isPresent() && friend.isPresent()) {
            removeFriend(person, friend);
        }
        return person.get();
    }

    @DeleteMapping("/{id}")
    void removeOneById(@PathVariable Long id) {
        personRepository.deleteById(id);
    }

    @DeleteMapping
    void deleteAll() {
        personRepository.deleteAll();
    }

    private void addNewFriend(Optional<Person> person, Optional<Person> friend) {
        person.get().getFriends().add(friend.get());
        personRepository.save(person.get());
    }

    private void removeFriend(Optional<Person> person, Optional<Person> friend) {
        person.get().getFriends().remove(friend.get());
        personRepository.save(person.get());
    }
}
