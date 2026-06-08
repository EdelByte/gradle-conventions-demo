plugins {
    id("sh.edelbyte.gradle.spring-boot-service")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":excuses-lib"))
}
