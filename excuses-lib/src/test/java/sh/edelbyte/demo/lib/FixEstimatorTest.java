package sh.edelbyte.demo.lib;

import org.junit.jupiter.api.Test;
import sh.edelbyte.demo.duration.FixDuration;

import static org.assertj.core.api.Assertions.assertThat;

class FixEstimatorTest {

    @Test
    void estimateFixDurationReturnsNonBlankPhrase() {
        FixEstimator estimator = new FixEstimator(7L);
        FixDuration estimate = estimator.estimateFixDuration();

        assertThat(estimate).isNotNull();
        assertThat(estimate.getPhrase()).isNotBlank();
    }
}
