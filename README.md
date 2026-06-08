# gradle-conventions-demo
Demo repository to showcase usage of the `sh.edelbyte.gradle` convention plugins.

## Purpose
The main goal of this repo is to demonstrate how convention plugins are applied in a consumer multi-module build.

The application domain is intentionally simple, a random excuse generator for flakey builds.


## How plugins are applied
Each module applies the relevant convention plugin in its own `build.gradle.kts`:
- `excuses-domain` → `sh.edelbyte.gradle.java-core`
- `duration-domain` → `sh.edelbyte.gradle.kotlin-core`
- `excuses-lib` → `sh.edelbyte.gradle.spring-boot-base` + `sh.edelbyte.gradle.jacoco`

This keeps module build files minimal while centralizing shared standards in the convention plugins.

## What this showcases
- Consistent build standards across Java and Kotlin modules
- Spring Boot library conventions without repeating setup in each module
- Coverage conventions (`jacoco`) applied declaratively by plugin ID

## Build
- `./gradlew build`
