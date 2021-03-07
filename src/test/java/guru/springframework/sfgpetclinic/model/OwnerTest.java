package guru.springframework.sfgpetclinic.model;

import guru.springframework.sfgpetclinic.CustomArgsProvider;
import guru.springframework.sfgpetclinic.ModelTests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.*;

import java.util.stream.Stream;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.ParameterizedTest.DEFAULT_DISPLAY_NAME;
import static org.junit.jupiter.params.ParameterizedTest.DISPLAY_NAME_PLACEHOLDER;

class OwnerTest implements ModelTests {

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

    @DisplayName("Value Source Test")
    @ParameterizedTest(name = DISPLAY_NAME_PLACEHOLDER + " - " + DEFAULT_DISPLAY_NAME)
    @ValueSource(strings = {"Spring", "Framework", "Guru"})
    void testValueSource(String val) {
        System.out.println(val);
    }

    @DisplayName("Enum Source Test")
    @ParameterizedTest(name = DISPLAY_NAME_PLACEHOLDER + " - " + DEFAULT_DISPLAY_NAME)
    @EnumSource(OwnerType.class)
    void enumTest(OwnerType ownerType) {
        System.out.println(ownerType);
    }

    @DisplayName("Csv Input Test")
    @ParameterizedTest(name = DISPLAY_NAME_PLACEHOLDER + " - " + DEFAULT_DISPLAY_NAME)
    @CsvSource({"FL,1,1", "OH,2,2", "MI,3,1"})
    void csvInputTest(String stateName, int val1, int val2) {
        System.out.println(stateName + "=" + val1 + ":" + val2);
    }

    @DisplayName("Csv From File Test")
    @ParameterizedTest(name = DISPLAY_NAME_PLACEHOLDER + " - " + DEFAULT_DISPLAY_NAME)
    @CsvFileSource(resources = "/input.csv", numLinesToSkip = 1)
    void csvFromFileTest(String stateName, int val1, int val2) {
        System.out.println(stateName + "=" + val1 + ":" + val2);
    }

    @DisplayName("Method Provider Test")
    @ParameterizedTest(name = DISPLAY_NAME_PLACEHOLDER + " - " + DEFAULT_DISPLAY_NAME)
    @MethodSource("getargs")
    void fromMethodTest(String stateName, int val1, int val2) {
        System.out.println(stateName + "=" + val1 + ":" + val2);
    }

    static Stream<Arguments> getargs() {
        return Stream.of(
          Arguments.of("FL",5,1),
          Arguments.of("OH",2,8),
          Arguments.of("MI",3,5)
        );
    }

    @DisplayName("Custom Provider Test")
    @ParameterizedTest(name = DISPLAY_NAME_PLACEHOLDER + " - " + DEFAULT_DISPLAY_NAME)
    @ArgumentsSource(CustomArgsProvider.class)
    void fromCustomProviderTest(String stateName, int val1, int val2) {
        System.out.println(stateName + "=" + val1 + ":" + val2);
    }
}