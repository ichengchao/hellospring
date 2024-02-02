package name.chengchao.hellospring.service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import name.chengchao.hellospring.model.Person;

@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

}
