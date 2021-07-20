plugins {
    id("java-library")
    kotlin("jvm")
}

group = "edu.duke.core"

repositories {
    mavenCentral()
}

dependencies {
    implementation(kotlin("stdlib"))
    api("org.apache.commons:commons-csv:1.8")

    //https://stackoverflow.com/questions/54166069/how-do-you-add-local-jar-file-dependency-to-build-gradle-kt-file
    api(fileTree(mapOf("dir" to "libs", "include" to listOf("*.jar"))))

    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}