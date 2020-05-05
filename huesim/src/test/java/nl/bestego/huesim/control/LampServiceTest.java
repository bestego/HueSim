package nl.bestego.huesim.control;

import nl.bestego.huesim.dto.LampDTO;
import nl.bestego.huesim.model.Lamp;
import nl.bestego.huesim.util.LampUtil;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)

public class LampServiceTest {

    @Mock
    private LampRepository repository;

    @InjectMocks LampService lampService;

    @Test
    @Ignore
    public void testStatusLamp() {
    }

    @Test
    public void testStatusLampDtoBestaandeId() {
        Long id = 1l;
        Optional<Lamp> lampExpected = LampUtil.nieuweOptionalLamp(id);
        when(repository.findById(id)).thenReturn(lampExpected);
        LampDTO lampResult = lampService.statusLampDTO(id);
        assertNotNull(lampResult);
        assertTrue(lampExpected.equals(lampResult));
    } //ToDo: CONTINUE HERE

//    @Test
//    public void testStatusLampenDto() {
//        when(repository.findAll()).thenReturn(LampUtil.nieuweLampenDTO());
//        lampService.statusLampenDTO();
//    }
}