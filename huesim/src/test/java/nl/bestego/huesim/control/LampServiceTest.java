package nl.bestego.huesim.control;

import nl.bestego.huesim.dto.LampDTO;
import nl.bestego.huesim.util.LampUtil;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)

public class LampServiceTest {

    @Mock
    private LampRepository repository;

    @InjectMocks GroepService groepService;

    @Test
    @Ignore
    public void testStatusLamp() {
    }

    @Test
    public void testStatusLampDTO() {
//        Long id = 1l;
//        LampDTO lamp = LampUtil.nieuweLampDTO(id);
//        when(repository.findById(id)).thenReturn(Optional.of());
    }

    @Test
    public void testStatusLampenDTO() {
    }
}