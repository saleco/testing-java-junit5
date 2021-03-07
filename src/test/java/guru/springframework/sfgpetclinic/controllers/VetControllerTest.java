package guru.springframework.sfgpetclinic.controllers;

import guru.springframework.sfgpetclinic.fauxspring.Model;
import guru.springframework.sfgpetclinic.fauxspring.ModelMapimpl;
import guru.springframework.sfgpetclinic.model.Vet;
import guru.springframework.sfgpetclinic.services.SpecialtyService;
import guru.springframework.sfgpetclinic.services.VetService;
import guru.springframework.sfgpetclinic.services.map.SpecialityMapService;
import guru.springframework.sfgpetclinic.services.map.VetMapService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

class VetControllerTest {

    VetController vetController;
    VetService vetService;
    SpecialtyService specialtyService;

    @BeforeEach
    void setUp() {
     this.specialtyService = new SpecialityMapService();
     this.vetService =  new VetMapService(specialtyService);
     this.vetController = new VetController(vetService);

        Vet vet1 = new Vet(1l, "joe", "buck", null);
        Vet vet2 = new Vet(2l, "Jimmy", "Smith", null);

        vetService.save(vet1);
        vetService.save(vet2);
    }

    @Test
    void listVets() {
        Model model = new ModelMapimpl();
        String view = vetController.listVets(model);
        assertThat("vets/index").isEqualTo(view);

        Set modelAttribute = (Set) ((ModelMapimpl) model).getMap().get("vets");
        assertThat(modelAttribute.size()).isEqualTo(2);

    }
}