plugins {
    id("sh.edelbyte.gradle.java-core") version "0.1.0"
}

repositories {
    mavenCentral()
}

dependencies {
    // SLF4J is compileOnly in java-core, so we need a runtime implementation
    testRuntimeOnly("org.slf4j:slf4j-simple:2.0.16")
}
