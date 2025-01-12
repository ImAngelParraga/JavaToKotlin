plugins {
    kotlin("jvm") version "2.0.21"
    java
}

group = "angel.parraga"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(kotlin("test"))

    testImplementation(kotlin("reflect"))
}

tasks.test {
    useJUnitPlatform()
}