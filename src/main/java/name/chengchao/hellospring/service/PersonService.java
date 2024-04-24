//package name.chengchao.hellospring.service;
//
//import java.util.List;
//import java.util.Optional;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.util.Streamable;
//import org.springframework.jdbc.core.JdbcTemplate;
//import org.springframework.stereotype.Service;
//import org.springframework.util.Assert;
//
//import name.chengchao.hellospring.model.Person;
//
//@Service
//public class PersonService {
//
//    @Autowired
//    private PersonRepository personRepository;
//
//    @Autowired
//    private JdbcTemplate jdbcTemplate;
//
//    public void test() {
//        jdbcTemplate.queryForList("select * from person");
//    }
//
//    public List<Person> getAllPersons() {
//        Iterable<Person> iterable = personRepository.findAll();
//        List<Person> list = Streamable.of(iterable).toList();
//        return list;
//    }
//
//    public Person getPersonById(Long id) {
//        Assert.notNull(id, "id can not be null!");
//        Optional<Person> op = personRepository.findById(id);
//        return op.get();
//    }
//
//    public Person addPerson(Person person) {
//        Assert.notNull(person, "person can not be null!");
//        Assert.isNull(person.getId(), "id must be null!");
//        Assert.hasText(person.getFirstName(), "firstName can not be blank!");
//        Assert.hasText(person.getLastName(), "lastName can not be blank!");
//        personRepository.save(person);
//        return person;
//    }
//
//    public Person updatePerson(Person person) {
//        Assert.notNull(person, "person can not be null!");
//        Assert.notNull(person.getId(), "id can not be null!");
//        Assert.hasText(person.getFirstName(), "firstName can not be blank!");
//        Assert.hasText(person.getLastName(), "lastName can not be blank!");
//        personRepository.save(person);
//        return person;
//    }
//
//    public void deletePerson(Long id) {
//        Assert.notNull(id, "id can not be null!");
//        personRepository.deleteById(id);
//    }
//
//}
