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
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
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
        //given
        Visit visit = new Visit();
        Set<Visit> visits = new HashSet<>();
        visits.add(visit);
        given(visitRepository.findAll()).willReturn(visits);

        //when
        Set<Visit> visitsCheck = visitSDJpaService.findAll();

        //then
        then(visitRepository).should().findAll();
        assertThat(visitsCheck).isNotNull();
        assertThat(visitsCheck).hasSize(1);
    }

    @Test
    void findById() {
        //given
        Visit visit = new Visit();
        given(visitRepository.findById(1L)).willReturn(Optional.of(visit));

        //when
        Visit visitCheck = visitSDJpaService.findById(1L);

        //then
        then(visitRepository).should().findById(anyLong());
        assertThat(visitCheck).isNotNull();

    }

    @Test
    void save() {
        //given
        Visit visit = new Visit();
        given(visitRepository.save(any(Visit.class))).willReturn(visit);

        //when
        Visit visitCheck = visitSDJpaService.save(new Visit());

        //then
        then(visitRepository).should().save(any(Visit.class));
        assertThat(visitCheck).isNotNull();
    }

    @Test
    void delete() {
        //given
        Visit visit = new Visit();

        //when
        visitSDJpaService.delete(visit);

        //then
        then(visitRepository).should().delete(any(Visit.class));
    }

    @Test
    void deleteById() {
        //given - none

        //when
        visitSDJpaService.deleteById(1l);

        //then
        then(visitRepository).should().deleteById(anyLong());
    }
}