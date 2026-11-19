# Demo completion TODOs

Reviewed `main` at `5fc44c3`, including all five commits currently in the repository.

## Current structure and recent work

- `excuses-domain`: Java excuse model and phrase catalogs.
- `duration-domain`: Kotlin fix-duration model and phrase catalog.
- `excuses-lib`: generators, Spring Boot auto-configuration, and library unit tests; depends on both domain modules.
- `excuses-service`: HTTP application consuming the library, with MockMvc and HTTP integration tests.
- Recent commits split the domain from the library (`9ff74ac`), introduced auto-configuration, duration estimation, and the service (`7483efb`), then added integration tests (`5fc44c3`).

## Build and reproducibility

- [x] **T1 — Fix local convention-plugin resolution for prerelease development.**
  Updated `settings.gradle.kts` to include `../gradle-conventions` instead of the stale `../mumakil` path and documented the required sibling checkout in the README. The composite build is intentional while developing prereleases; standalone published-plugin consumption is deferred. Verified with `./gradlew help --offline --no-daemon --console=plain`.

- [x] **T2 — Centralize and consistently pin convention-plugin versions.**
  All six convention-plugin IDs now use the single `conventionPluginsVersion` default in `settings.gradle.kts` under `pluginManagement.plugins`. Removed duplicated module versions; both service plugins have explicit defaults too. Verified all four modules configure with the intentional local prerelease composite build using `./gradlew help projects --offline --no-daemon --console=plain`, including configuration-cache reuse on a second run. The included checkout supplies plugin implementations regardless of these default versions; published-plugin resolution remains deferred as noted in T1.

- [x] **T3 — Establish a passing full-build baseline after T1 and T2.**
  Verified with a full JDK 17: two successful clean builds, configuration-cache reuse on the second build, and a separate forced service integration-test run. All 7 unit tests and 1 HTTP integration test pass with no failures, errors, or skips. Confirmed that the service's `check` task includes `intTest` and that the library JaCoCo XML report is generated. No source or build-configuration fixes were necessary; commands and prerequisites are documented in the README.

## Complete the application demo

- [ ] **T4 — Provide a reproducible service launch/package path.**
  The service has a `main` class but no documented launch command. The current neighboring `spring-boot-service` convention deliberately does not apply the Spring Boot plugin and does not configure Jib, so neither `bootRun` nor a container workflow should be assumed. Select and configure the intended launch/package mechanism, then smoke-test `/api/excuses` using the produced application.

- [x] **T5 — Decide and demonstrate how duration estimation reaches consumers.**
  `GET /api/excuses` now includes a top-level `duration` string alongside the existing `category` and `description` fields. `ExcuseController` uses the auto-configured `FixEstimator` to expose the Kotlin `FixDuration` phrase. A standalone deterministic MockMvc test verifies exact field mapping, and the typed HTTP integration test exercises application wiring and checks that the duration belongs to the Kotlin phrase catalog. The README documents the JSON contract and a sample response.

## Verification gaps

- [ ] **T6 — Test auto-configuration defaults and consumer overrides.**
  Add focused tests for `ExcusesLibAutoConfiguration`: both default beans are created, and each `@ConditionalOnMissingBean` backs off independently when a consumer supplies its own implementation. Retain an application-level test that exercises discovery through `AutoConfiguration.imports`, rather than relying solely on directly importing the configuration under test.

- [ ] **T7 — Strengthen the controller and HTTP integration-test contracts.**
  T5 adds standalone deterministic controller mapping coverage and a typed HTTP response with valid-category, nonblank-description, and duration-catalog assertions; HTTP coverage retains the actual security filter chain. Extend deterministic mapping coverage to both categories.

- [ ] **T8 — Finish generator behavior coverage.**
  Extend `ExcuseGeneratorTest` and `FixEstimatorTest` to check that generated values belong to the appropriate catalogs and that equal seeds reproduce equal sequences. Exercise both random-excuse branches deterministically. Add direct Kotlin tests for any domain behavior introduced while completing T5; `duration-domain` currently has no tests of its own.

- [ ] **T9 — Add automated build verification and visible coverage output.**
  This demo has no CI workflow. Add clean-checkout JDK 17 verification using the plugin-resolution strategy from T1 and T2, run unit and integration tests, and retain test results and the library's JaCoCo XML report. Verify that the report is actually generated; the current neighboring JaCoCo convention enables XML and disables HTML, so document XML consumption or explicitly enable HTML if a browsable demo is desired.

## Documentation

- [ ] **T10 — Update the README into a complete demo walkthrough.**
  Add the four-module dependency layout, the missing `excuses-service` plugin mapping, plugin versions and local-development setup, JDK prerequisites, unit/integration-test commands, report locations, and the verified launch command from T4. Include a sample HTTP request/response and the duration usage chosen in T5. Clearly distinguish behavior inherited from conventions from explicit consumer configuration.

## Validation performed during this review

Before T1, `./gradlew help --offline --no-daemon --console=plain` failed during settings evaluation:
`Included build '/home/fant4stic/repos/mumakil' does not exist.`

After T1, the same command succeeds using `../gradle-conventions`.

T3 baseline verified with Temurin JDK 17.0.21:

- `./gradlew clean build --no-daemon --console=plain` succeeded twice; the second invocation reported configuration-cache reuse.
- `./gradlew :excuses-service:intTest --rerun --no-daemon --console=plain` successfully executed the HTTP integration test again, rather than just reporting it up-to-date.
- `./gradlew :excuses-service:check --dry-run --no-daemon --console=plain` included `:excuses-service:intTest` before `:excuses-service:check`.
- JUnit XML results report 6 library unit tests, 1 service unit test, and 1 service integration test, all passing without skips.
- Coverage XML exists at `excuses-lib/build/reports/jacoco/test/jacocoTestReport.xml`.

Non-blocking Gradle deprecation warnings remain relevant to a future Gradle 9 upgrade. T5 replaces the HTTP integration test's unchecked raw `Map` with a typed response. Verification uses the neighboring `../gradle-conventions` source checkout; compatibility with a published plugin release remains to be verified.
