package name.chengchao.hellospring.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Table("person")
public class Person {

    @Id
    private Long id;
    private String firstName;
    private String LastName;

    public Person() {}

    public Person(String firstName, String lastName) {
        this.firstName = firstName;
        LastName = lastName;
    }

    @Override
    public String toString() {
        return "Person [id=" + id + ", firstName=" + firstName + ", LastName=" + LastName + "]";
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return LastName;
    }

    public void setLastName(String lastName) {
        LastName = lastName;
    }

}
