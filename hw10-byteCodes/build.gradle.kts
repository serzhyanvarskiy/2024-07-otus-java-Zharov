plugins {
    id("java")
}

group = "my.j.ex"

repositories {
    mavenCentral()
}

dependencies {
    implementation ("ch.qos.logback:logback-classic")
}

tasks.test {
    useJUnitPlatform()
}