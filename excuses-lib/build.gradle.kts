plugins {
    id("sh.edelbyte.gradle.spring-boot-base")
    id("sh.edelbyte.gradle.jacoco")
}

repositories {
    mavenCentral()
}

dependencies {
    api(project(":excuses-domain"))
    api(project(":duration-domain"))
}
