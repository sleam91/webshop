package webshop.data;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import webshop.model.Person;

public interface PersonRepository extends JpaRepository<Person, Integer> {

    Optional<Person> findByEmail(String email);
}
