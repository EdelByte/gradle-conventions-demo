package sh.edelbyte.demo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class ExcuseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void getExcuseReturnsRandomExcusePayload() throws Exception {
        mockMvc.perform(get("/api/excuses"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.category").isNotEmpty())
            .andExpect(jsonPath("$.description").isNotEmpty());
    }
}
