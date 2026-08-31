plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    compileOnly("org.projectlombok:lombok:1.18.46")
    annotationProcessor("org.projectlombok:lombok:1.18.46")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testImplementation("org.junit.platform:junit-platform-suite")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")

    testImplementation(platform("io.cucumber:cucumber-bom:7.34.4"))
    testImplementation("io.cucumber:cucumber-java")
    testImplementation("io.cucumber:cucumber-junit-platform-engine")

    implementation("net.datafaker:datafaker:2.5.4")
    implementation("com.github.lalyos:jfiglet:0.0.8")
    implementation("tools.jackson.core:jackson-databind:3.2.1")
    testImplementation("net.datafaker:datafaker:2.5.4")
    testImplementation("io.rest-assured:rest-assured:6.0.0")
    testImplementation("org.hamcrest:hamcrest:3.0")
    implementation("com.codeborne:selenide:7.17.0")
    testImplementation("com.codeborne:selenide:7.17.0")
}

tasks.test {
    useJUnitPlatform()
}