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
- `excuses-service` → `sh.edelbyte.gradle.spring-boot-service` + `sh.edelbyte.gradle.spring-boot-int-test`

This keeps module build files minimal while centralizing shared standards in the convention plugins.
All six convention-plugin IDs share the `conventionPluginsVersion` default in `settings.gradle.kts`; modules apply them without specifying versions. During prerelease development, the included `../gradle-conventions` build supplies the plugin implementations from that checkout rather than resolving the default published version.

## What this showcases
- Consistent build standards across Java and Kotlin modules
- Spring Boot library conventions without repeating setup in each module
- Coverage conventions (`jacoco`) applied declaratively by plugin ID

## Build
For prerelease development, clone [gradle-conventions](https://github.com/EdelByte/gradle-conventions) alongside this repository as `../gradle-conventions`; the build uses that local checkout for convention plugins.
- `./gradlew build`
