# AGENTS.md

## Repo overview
Plain Gradle (Kotlin DSL) Java project (root project `School`), no web framework, no Spring. Pure Java 17 source under `src/main/java`. There are **no `*.kt` files** — Java only.

## Workflow convention (important)
Each lesson lives on its **own git branch** created off `main` (`lesson_3`, `lesson_4`, ... `lesson18`, etc.). Work is done on the lesson branch, then merged via PR. Follow the existing commit style: commit messages are Russian, e.g. `17 Задание - Выполнение 3 части задания`. Don't create/commit work directly on `main`.

## Layout
- `src/main/java/org/lessonNN/...` — each lesson gets its own package (`org.lesson1`, `org.lesson17`, ...). `lesson18` doesn't exist yet; create it as `org.lesson18`.
- `src/test/java/org/lessonNN/...` — lesson-specific JUnit 5 tests.
- `build/`, `.gradle/`, `.idea/` are git-ignored; never commit build artifacts.

## Commands
- Build / run tests: `./gradlew test` (JUnit Platform is wired via `tasks.test { useJUnitPlatform() }`). Java plugin only, no test tag filtering configured.
- Run a single test class: `./gradlew test --tests "org.lesson17.ServeRestTest"`
- Gradle wrapper is version `9.2.0`. Use `./gradlew`, not a system `gradle`.

## Dependencies worth knowing (from `build.gradle.kts`)
- Lombok is on `compileOnly` + `annotationProcessor` — DTO/model classes may rely on generated getters/setters/`@Data`.
- `net.datafaker:datafaker` — for generating fake test data.
- `io.rest-assured:rest-assured` + `org.hamcrest` — **only** used for the `org.lesson17` REST API tests against the live `https://serverest.dev` service. This requires network access.
- `tools.jackson` (New Jackson, `jackson-databind 3.x`) — note it is *not* the classic `com.fasterxml.jackson` coordinates.
- `com.github.lalyos:jfiglet` — ASCII text banner generator.

## Testing quirks
- `ServeRestTest` (`org.lesson17`) hits a real external API and is order-dependent (`@TestMethodOrder` + `@Order`, shared static state across tests). It is not hermetic — needs internet.
- Other suites (e.g. `org.lesson15`) are plain unit tests.

## Gotchas
- No formatter/linter/typecheck beyond the Java compiler; `./gradlew build` is the validation gate.
- `.gitignore` keeps `build/` but re-includes some nested `build/` under `src` — don't manually manage those.