package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.fauxspring.BindingResult;
import guru.springframework.sfgpetclinic.model.Owner;
import guru.springframework.sfgpetclinic.services.OwnerService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static guru.springframework.sfgpetclinic.controllers.OwnerController.VIEWS_OWNER_CREATE_OR_UPDATE_FORM;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {

    @Mock
    OwnerService ownerService;

    @InjectMocks
    OwnerController ownerController;

    @Mock
    BindingResult bindingResult;

    @Test
    void processCreationFormWhenBindingResultsHasErrors() {
        //given
        Owner owner = new Owner(1L, "Sallo", "Szrajbman");
        given(bindingResult.hasErrors()).willReturn(true);

        //when
        String returnedValue = ownerController.processCreationForm(owner, bindingResult);

        //then
        assertThat(returnedValue).isEqualTo(VIEWS_OWNER_CREATE_OR_UPDATE_FORM);
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
        assertThat(returnedValue).isEqualTo("redirect:/owners/5");
        then(ownerService).should(times(1)).save(owner);

    }
}