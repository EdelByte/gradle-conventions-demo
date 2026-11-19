package sh.edelbyte.demo.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import sh.edelbyte.demo.duration.FixDurationPhrases;
import sh.edelbyte.demo.service.ExcuseController.ExcuseResponse;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ExcuseControllerIntTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void getExcuseReturnsCategoryDescriptionAndDuration() {
        ResponseEntity<ExcuseResponse> response = restTemplate.getForEntity(
            "http://localhost:" + port + "/api/excuses",
            ExcuseResponse.class
        );

        assertThat(response.getStatusCode().is2xxSuccessful()).isTrue();
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().category()).isIn("SLOW_BUILD", "FAILED_BUILD");
        assertThat(response.getBody().description()).isNotBlank();
        assertThat(response.getBody().duration()).isNotBlank().isIn(FixDurationPhrases.PHRASES);
    }
}
