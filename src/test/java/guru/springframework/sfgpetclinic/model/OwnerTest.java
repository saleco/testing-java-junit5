package guru.springframework.sfgpetclinic.model;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class OwnerTest {

    @Test
    void dependentAssertions() {
        Owner owner = new Owner(1l, "Joe", "Buck");
        owner.setCity("Key West");
        owner.setTelephone("12312331234");

        assertAll("Properties Test",
          () -> assertAll("Person Properties",
            () -> assertEquals("Joe", owner.getFirstName(), "First name did not match"),
            () -> assertEquals("Buck", owner.getLastName(), "Last name did not match")),
          () -> assertAll("Owner Properties",
            () -> assertEquals("Key West", owner.getCity(), "City did not match"),
            () -> assertEquals("12312331234", owner.getTelephone(), "Telephone did not match"))
        );

        //Hamcrest assertThat
        assertThat(owner.getCity(), is("Key West"));
    }
}