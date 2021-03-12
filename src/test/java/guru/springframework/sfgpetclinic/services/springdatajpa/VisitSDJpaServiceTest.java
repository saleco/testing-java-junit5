package guru.springframework.sfgpetclinic.services.springdatajpa;

import guru.springframework.sfgpetclinic.model.Visit;
import guru.springframework.sfgpetclinic.repositories.VisitRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class VisitSDJpaServiceTest {

    @Mock
    VisitRepository visitRepository;

    @InjectMocks
    VisitSDJpaService visitSDJpaService;


    @Test
    void findAll() {
        Set<Visit> visits = new HashSet<>();
        when(visitRepository.findAll()).thenReturn(visits);
        Set<Visit> visitsCheck = visitSDJpaService.findAll();

        assertThat(visitsCheck).isNotNull();
        verify(visitRepository).findAll();
    }

    @Test
    void findById() {
        Visit visit = new Visit();
        when(visitRepository.findById(1L)).thenReturn(Optional.of(visit));

        Visit visitCheck = visitSDJpaService.findById(1L);
        assertThat(visitCheck).isNotNull();
        verify(visitRepository).findById(any());

    }

    @Test
    void save() {
        Visit visit = new Visit();
        when(visitRepository.save(visit)).thenReturn(visit);

        Visit visitCheck = visitSDJpaService.save(visit);
        assertThat(visitCheck).isNotNull();
        verify(visitRepository).save(any(Visit.class));
    }

    @Test
    void delete() {
        Visit visit = new Visit();
        visitSDJpaService.delete(visit);
        verify(visitRepository).delete(any(Visit.class));
    }

    @Test
    void deleteById() {
        visitSDJpaService.deleteById(1l);
        verify(visitRepository).deleteById(anyLong());
    }
}