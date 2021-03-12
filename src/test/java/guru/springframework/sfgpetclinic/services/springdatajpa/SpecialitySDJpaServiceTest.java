package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Speciality;
import guru.springframework.sfgpetclinic.repositories.SpecialtyRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class SpecialitySDJpaServiceTest {

    @Mock
    SpecialtyRepository specialtyRepository;

    //tells mockito to create a real instance of the class under test
    @InjectMocks
    SpecialitySDJpaService specialitySDJpaService;

    @Test
    void testDeleteByObject() {
        Speciality speciality = new Speciality();

        specialitySDJpaService.delete(speciality);

        verify(specialtyRepository).delete(any(Speciality.class));
    }

    @Test
    void findByIdTest() {
        Speciality speciality = new Speciality();

        when(specialtyRepository.findById(1L)).thenReturn(Optional.of(speciality));

        Speciality foundSpecialty = specialitySDJpaService.findById(1L);

        assertThat(foundSpecialty).isNotNull();
        verify(specialtyRepository).findById(anyLong());
    }

    @Test
    void deleteById() {
        specialitySDJpaService.deleteById(1L);

        //very it was called once
        verify(specialtyRepository).deleteById(1L);
    }

    @Test
    void deleteByIdAtLeastOnce() {
        specialitySDJpaService.deleteById(1L);
        specialitySDJpaService.deleteById(1L);

        //very it was called once
        verify(specialtyRepository, atLeastOnce()).deleteById(1L);
    }

    @Test
    void deleteByIdAtMostFiveTimes() {
        specialitySDJpaService.deleteById(1L);
        specialitySDJpaService.deleteById(1L);

        //very it was called once
        verify(specialtyRepository, atMost(5)).deleteById(1L);
    }

    @Test
    void deleteByIdNever() {
        specialitySDJpaService.deleteById(1L);
        specialitySDJpaService.deleteById(1L);

        //very it was called once
        verify(specialtyRepository, atLeastOnce()).deleteById(1L);

        verify(specialtyRepository, never()).deleteById(5L);
    }

    @Test
    void testDelete() {
        specialitySDJpaService.delete(new Speciality());
    }
}