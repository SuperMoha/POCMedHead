package mshopital;

import com.fasterxml.jackson.databind.ObjectMapper;
import mshopital.model.Hopital;
import mshopital.service.HopitalService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;


import static org.hamcrest.Matchers.hasItems;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class HopitalControllerTests {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private HopitalService hopitalService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void testGetAllHopitaux() throws Exception {
        when(hopitalService.getAllHopitaux()).thenReturn(Arrays.asList(new Hopital(), new Hopital()));
        mockMvc.perform(get("/hopitaux")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetHopitalById() throws Exception {
        Hopital hopital = new Hopital();
        when(hopitalService.getHopitalById(anyInt())).thenReturn(hopital);

        mockMvc.perform(get("/hopitaux/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void testCreateHopital() throws Exception {
        doNothing().when(hopitalService).createHopital(any(Hopital.class));

        mockMvc.perform(post("/hopitaux")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new Hopital())))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateHopital() throws Exception {
        Hopital hopital = new Hopital();
        when(hopitalService.getHopitalById(anyInt())).thenReturn(hopital);
        mockMvc.perform(put("/hopitaux/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(new Hopital())))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteHopital() throws Exception {
        Hopital hopital = new Hopital();
        when(hopitalService.getHopitalById(anyInt())).thenReturn(hopital);
        mockMvc.perform(delete("/hopitaux/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
