plugins {
    id("java")
}

group = "ru.ATM"

repositories {
    mavenCentral()
}

dependencies {
    val implementation = implementation("ch.qos.logback:logback-classic")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}