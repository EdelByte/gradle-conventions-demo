plugins {
    id("sh.edelbyte.gradle.spring-boot-service")
    id("sh.edelbyte.gradle.spring-boot-int-test")
}

repositories {
    mavenCentral()
}

dependencies {
    implementation(project(":excuses-lib"))
}
