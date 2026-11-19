package sh.edelbyte.demo.service;

import org.junit.jupiter.api.Test;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import sh.edelbyte.demo.domain.Excuse;
import sh.edelbyte.demo.duration.FixDuration;
import sh.edelbyte.demo.lib.ExcuseGenerator;
import sh.edelbyte.demo.lib.FixEstimator;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ExcuseControllerTest {

    @Test
    void getExcuseMapsExcuseAndEstimatedDurationToJson() throws Exception {
        ExcuseGenerator excuseGenerator = mock(ExcuseGenerator.class);
        FixEstimator fixEstimator = mock(FixEstimator.class);
        when(excuseGenerator.getRandomExcuse())
            .thenReturn(new Excuse(Excuse.Category.FAILED_BUILD, "The build gremlins are on strike."));
        when(fixEstimator.estimateFixDuration())
            .thenReturn(new FixDuration("after one coffee break"));
        MockMvc controllerMockMvc = MockMvcBuilders
            .standaloneSetup(new ExcuseController(excuseGenerator, fixEstimator))
            .build();

        controllerMockMvc.perform(get("/api/excuses"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.category").value("FAILED_BUILD"))
            .andExpect(jsonPath("$.description").value("The build gremlins are on strike."))
            .andExpect(jsonPath("$.duration").value("after one coffee break"));

        verify(excuseGenerator).getRandomExcuse();
        verify(fixEstimator).estimateFixDuration();
    }
}
