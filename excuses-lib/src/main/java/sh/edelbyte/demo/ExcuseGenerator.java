package sh.edelbyte.demo;

import lombok.extern.slf4j.Slf4j;
import sh.edelbyte.demo.Excuse.ExcuseCategory;

import java.util.List;
import java.util.Random;

@Slf4j
public class ExcuseGenerator {
    
    private static final List<String> SLOW_BUILD_EXCUSES = List.of(
        "The build server is mining cryptocurrency on the side",
        "Gradle is still downloading the internet",
        "The hamsters powering the CI server need a water break",
        "Maven Central is being extra central today",
        "Your code is so good it needs extra time to compile",
        "The JVM is contemplating the meaning of life",
        "Kotlin coroutines are taking a coffee break",
        "The build cache went out for lunch",
        "Someone left debug logging on... everywhere",
        "The dependency tree is actually a dependency forest now",
        "Gradle daemon is writing its memoirs",
        "The annotation processor is overanalyzing things",
        "The incremental compiler is being very thorough",
        "Your build script is actually compiling Gradle itself"
    );
    
    private static final List<String> FAILED_BUILD_EXCUSES = List.of(
        "It works on my machine™",
        "Mercury is in retrograde",
        "Someone pushed directly to main (it wasn't me)",
        "The tests passed locally, I swear",
        "Cosmic rays flipped a bit in production",
        "The CI server is having an existential crisis",
        "A solar flare interfered with the build",
        "The build worked before lunch",
        "This is a known issue in the future version",
        "The code review was too positive, it got suspicious",
        "The linter is being too literal today",
        "Stack Overflow was down when I wrote this",
        "The compiler is being unnecessarily pedantic",
        "My cat walked across the keyboard",
        "It's a feature, not a bug"
    );
    
    private final Random random;
    
    public ExcuseGenerator() {
        this.random = new Random();
    }
    
    public ExcuseGenerator(long seed) {
        this.random = new Random(seed);
    }
    
    public Excuse getRandomSlowBuildExcuse() {
        String description = SLOW_BUILD_EXCUSES.get(random.nextInt(SLOW_BUILD_EXCUSES.size()));
        log.debug("Generated slow build excuse: {}", description);
        return new Excuse(ExcuseCategory.SLOW_BUILD, description);
    }
    
    public Excuse getRandomFailedBuildExcuse() {
        String description = FAILED_BUILD_EXCUSES.get(random.nextInt(FAILED_BUILD_EXCUSES.size()));
        log.debug("Generated failed build excuse: {}", description);
        return new Excuse(ExcuseCategory.FAILED_BUILD, description);
    }
    
    public Excuse getRandomExcuse() {
        boolean useSlowBuild = random.nextBoolean();
        return useSlowBuild ? getRandomSlowBuildExcuse() : getRandomFailedBuildExcuse();
    }
    
    public Excuse getRandomExcuseForCategory(ExcuseCategory category) {
        return switch (category) {
            case SLOW_BUILD -> getRandomSlowBuildExcuse();
            case FAILED_BUILD -> getRandomFailedBuildExcuse();
        };
    }
}
