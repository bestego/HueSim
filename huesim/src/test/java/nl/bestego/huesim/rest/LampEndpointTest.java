package nl.bestego.huesim.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import nl.bestego.huesim.control.LampService;
import nl.bestego.huesim.util.LampUtil;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(LampEndpoint.class)
public class LampEndpointTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private ObjectMapper objectmapper;

    @MockBean
    private LampService service;

    @org.junit.Test
    public void getStatusLamp() throws Exception {
        when(service.statusLampDTO(1L)).thenReturn(LampUtil.nieuweLampDTO(1L));
        mvc.perform(MockMvcRequestBuilders
                .get("/lamp/1")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("helderheid", is(50)));
    }

    @org.junit.Test
    public void getStatusLampen() throws Exception {
        when(service.statusLampenDTO()).thenReturn(LampUtil.nieuweLampenDTO());
        mvc.perform(MockMvcRequestBuilders
                .get("/lamp")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.*", hasSize(LampUtil.COLLECTION_COUNT)))
                .andExpect(jsonPath("$.[0].helderheid", is(50)));
    }
}