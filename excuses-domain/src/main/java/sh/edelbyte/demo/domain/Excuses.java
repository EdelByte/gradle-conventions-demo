package sh.edelbyte.demo.domain;

import java.util.List;

public final class Excuses {
    
    private Excuses() {}
    
    public static final List<String> SLOW_BUILD_EXCUSES = List.of(
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
    
    public static final List<String> FAILED_BUILD_EXCUSES = List.of(
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
}
