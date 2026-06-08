package sh.edelbyte.demo.lib;

import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;

@AutoConfiguration
public class ExcusesLibAutoConfiguration {

    @Bean
    @ConditionalOnMissingBean
    public ExcuseGenerator excuseGenerator() {
        return new ExcuseGenerator();
    }

    @Bean
    @ConditionalOnMissingBean
    public FixEstimator fixEstimator() {
        return new FixEstimator();
    }
}
