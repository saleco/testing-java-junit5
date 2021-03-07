package guru.springframework.sfgpetclinic.model;

import guru.springframework.sfgpetclinic.ModelRepeatedTests;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.RepetitionInfo;
import org.junit.jupiter.api.TestInfo;

public class PersonRepeatedTests implements ModelRepeatedTests {

    @RepeatedTest(value = 10,
      name = RepeatedTest.DISPLAY_NAME_PLACEHOLDER + " : " + RepeatedTest.CURRENT_REPETITION_PLACEHOLDER + " of " + RepeatedTest.TOTAL_REPETITIONS_PLACEHOLDER)
    @DisplayName("My Repeated Test")
    void myRepeatedTest() {
        //todo - impl
    }

    @RepeatedTest(5)
    void myRepeatedTestWithDependencyInjection(TestInfo testInfo, RepetitionInfo repetitionInfo) {
        System.out.println(testInfo.getDisplayName() + ":" + repetitionInfo.getCurrentRepetition());
    }

    @RepeatedTest(value = 5,
      name = RepeatedTest.DISPLAY_NAME_PLACEHOLDER + " : " + RepeatedTest.CURRENT_REPETITION_PLACEHOLDER + " of " + RepeatedTest.TOTAL_REPETITIONS_PLACEHOLDER)
    @DisplayName("My Assignment Repeated Test")
    void myAssignmentRepeated() {
        //todo impl
    }
}
