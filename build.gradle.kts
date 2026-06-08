plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    testRuntimeOnly("org.junit.platform:junit-platform-launcher")
    implementation("net.datafaker:datafaker:2.5.4")
    implementation("com.github.lalyos:jfiglet:0.0.8")

}

tasks.test {
    useJUnitPlatform()
}