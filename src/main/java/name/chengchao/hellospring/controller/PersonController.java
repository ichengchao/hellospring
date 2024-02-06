package name.chengchao.hellospring.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import name.chengchao.hellospring.model.Person;
import name.chengchao.hellospring.service.PersonService;

@RestController
public class PersonController {

    // create table
    // CREATE TABLE `person` (
    // `id` int unsigned NOT NULL AUTO_INCREMENT,
    // `first_name` varchar(200) DEFAULT NULL,
    // `last_name` varchar(200) DEFAULT NULL,
    // `gmt_create` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
    // PRIMARY KEY (`id`)
    // ) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8mb4 ;

    @Autowired
    private PersonService personService;

    private static Logger logger = LoggerFactory.getLogger(PersonController.class);

    @RequestMapping(value = "/persons", method = RequestMethod.GET)
    public List<Person> getAllPersons() {
        List<Person> list = personService.getAllPersons();
        logger.info("getAllPersons,size:{}", list.size());
        return list;
    }

    @RequestMapping(value = "/person/{id}", method = RequestMethod.GET)
    public Person getPersonById(@PathVariable long id) {
        logger.info("getPersonById,id:{}", id);
        return personService.getPersonById(id);
    }

    @RequestMapping(value = "/person", method = RequestMethod.POST, produces = "application/json")
    public Object addPerson(@RequestBody Person person) {
        personService.addPerson(person);
        logger.info("addPerson,{}", person.toString());
        return person;
    }

    @RequestMapping(value = "/person", method = RequestMethod.PUT, produces = "application/json")
    public Object updatePerson(@RequestBody Person person) {
        person = personService.updatePerson(person);
        logger.info("updatePerson,{}", person.toString());
        return person;
    }

    @RequestMapping(value = "/person/{id}", method = RequestMethod.DELETE)
    public Object deletePerson(@PathVariable long id) {
        logger.info("deletePerson,id:{}", id);
        personService.deletePerson(id);
        return id;
    }

}
