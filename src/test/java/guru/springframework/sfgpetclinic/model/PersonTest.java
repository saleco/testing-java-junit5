package guru.springframework.sfgpetclinic.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PersonTest {

    @Test
    void groupedAssertions() {
        //given
        Person person =  new Person(1l, "Joe", "Buck");

        //then
        assertAll("Test Props Set",
          () -> assertEquals("Joe", person.getFirstName()),
          () -> assertEquals("Buck", person.getLastName()));
    }

    @Test
    void groupedAssertionWithMessages() {
        //given
        Person person =  new Person(1l, "Joe", "Buck");

        //then
        assertAll("Test Props Set",
          () -> assertEquals("Joe", person.getFirstName(), "First name Failed"),
          () -> assertEquals("Buck", person.getLastName(), "Last name Failed"));
    }
}