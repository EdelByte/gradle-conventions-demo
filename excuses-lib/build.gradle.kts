plugins {
    id("sh.edelbyte.gradle.spring-boot-base") version "0.1.0"
    id("sh.edelbyte.gradle.jacoco") version "0.1.0"
}

repositories {
    mavenCentral()
}

dependencies {
    api(project(":excuses-domain"))
}
