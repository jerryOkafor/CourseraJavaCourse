plugins {
    java
    kotlin("jvm")
}

group = "edu.duke.course3.week1"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    project(":core")

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}