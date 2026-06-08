package sh.edelbyte.demo.lib;

import lombok.extern.slf4j.Slf4j;
import sh.edelbyte.demo.domain.Excuse;
import sh.edelbyte.demo.domain.Excuse.Category;
import sh.edelbyte.demo.domain.Excuses;

import java.util.Random;

@Slf4j
public class ExcuseGenerator {
    
    private final Random random;
    
    public ExcuseGenerator() {
        this.random = new Random();
    }
    
    public ExcuseGenerator(long seed) {
        this.random = new Random(seed);
    }
    
    public Excuse getRandomSlowBuildExcuse() {
        String description = Excuses.SLOW_BUILD_EXCUSES.get(random.nextInt(Excuses.SLOW_BUILD_EXCUSES.size()));
        log.debug("Generated slow build excuse: {}", description);
        return new Excuse(Category.SLOW_BUILD, description);
    }
    
    public Excuse getRandomFailedBuildExcuse() {
        String description = Excuses.FAILED_BUILD_EXCUSES.get(random.nextInt(Excuses.FAILED_BUILD_EXCUSES.size()));
        log.debug("Generated failed build excuse: {}", description);
        return new Excuse(Category.FAILED_BUILD, description);
    }
    
    public Excuse getRandomExcuse() {
        boolean useSlowBuild = random.nextBoolean();
        return useSlowBuild ? getRandomSlowBuildExcuse() : getRandomFailedBuildExcuse();
    }
    
    public Excuse getRandomExcuseForCategory(Category category) {
        return switch (category) {
            case SLOW_BUILD -> getRandomSlowBuildExcuse();
            case FAILED_BUILD -> getRandomFailedBuildExcuse();
        };
    }
}
