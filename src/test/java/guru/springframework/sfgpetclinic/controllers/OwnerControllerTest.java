package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.fauxspring.BindingResult;
import guru.springframework.sfgpetclinic.fauxspring.Model;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static guru.springframework.sfgpetclinic.controllers.OwnerController.VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    private static final String REDIRECT_OWNERS_5 = "redirect:/owners/5";

    @Mock
    OwnerService ownerService;

    @InjectMocks
    OwnerController ownerController;

    @Mock
    BindingResult bindingResult;

    @Captor
    ArgumentCaptor<String> stringArgumentCaptor;

    @BeforeEach
    void setUp() {
        given(ownerService.findAllByLastNameLike(stringArgumentCaptor.capture())).willAnswer(invocationOnMock -> {
            List<Owner> owners = new ArrayList<>();
            String name = invocationOnMock.getArgument(0);

            if (name.equals("%Buck%")) {
                owners.add(new Owner(1L, "Joe", "Buck"));
                return owners;
            } else if (name.equals("%DontFindMe%")) {
                return owners; //empty list
            } else if (name.equals("%FindMe%")) {
                owners.add(new Owner(1L, "Joe", "Buck"));
                owners.add(new Owner(2L, "Joe2", "Buck2"));
                return owners;
            }

            throw new RuntimeException("Invalid Argument");
        });
    }

    @Test
    void processFindFormWildcardStringWithAnnotationBasedCaptor() {
        //given
        Owner owner = new Owner(1L, "Joe", "Buck");

        //this given its now being applied in willAnswer on beforeEach
//        given(ownerService.findAllByLastNameLike(stringArgumentCaptor.capture())).willReturn(owners);

        //when
        String viewName = ownerController.processFindForm(owner, bindingResult, Mockito.mock(Model.class));

        //then
        //check the argument passed to ownerService mock
        assertThat("%Buck%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());
        assertThat("redirect:/owners/1").isEqualTo(viewName);
    }

    @Test
    void processFindFormWildcardNotFound() {
        //given
        Owner owner = new Owner(1L, "Joe", "DontFindMe");

        //when
        String viewName = ownerController.processFindForm(owner, bindingResult, null);

        //then
        //check the argument passed to ownerService mock
        assertThat("%DontFindMe%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());
        assertThat("owners/findOwners").isEqualTo(viewName);
    }

    @Test
    void processFindFormWildcardFound() {
        //given
        Owner owner = new Owner(1L, "Joe", "FindMe");

        //when
        String viewName = ownerController.processFindForm(owner, bindingResult, null);

        //then
        //check the argument passed to ownerService mock
        assertThat("%FindMe%").isEqualToIgnoringCase(stringArgumentCaptor.getValue());
        assertThat("owners/ownersList").isEqualTo(viewName);
    }



    @Test
    void processCreationFormWhenBindingResultsHasErrors() {
        //given
        Owner owner = new Owner(1L, "Sallo", "Szrajbman");
        given(bindingResult.hasErrors()).willReturn(true);

        //when
        String returnedValue = ownerController.processCreationForm(owner, bindingResult);

        //then
        assertThat(returnedValue).isEqualToIgnoringCase(VIEWS_OWNER_CREATE_OR_UPDATE_FORM);
        then(ownerService).shouldHaveZeroInteractions();

    }

    @Test
    void processCreationFormWhenBindingResultsHasNoErrors() {
        //given
        Owner owner = new Owner(5L, "Sallo", "Szrajbman");
        given(bindingResult.hasErrors()).willReturn(false);
        given(ownerService.save(any())).willReturn(owner);

        //when
        String returnedValue = ownerController.processCreationForm(owner, bindingResult);

        //then
        assertThat(returnedValue).isEqualToIgnoringCase(REDIRECT_OWNERS_5);
        then(ownerService).should(times(1)).save(owner);

    }
}