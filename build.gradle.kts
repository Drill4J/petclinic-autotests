import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.50"
    id("com.epam.drill.agent.runner.autotest") version ("0.2.1")
}

group = "com.epam.epm-d4j"
version = "1.0-SNAPSHOT"

val jUnitVersion = "5.6.2"
val restAssuredVersion = "4.0.0"
val selenideVersion = "5.13.0"

repositories {
    mavenCentral()
    mavenLocal()
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("com.codeborne:selenide:$selenideVersion")
    implementation("io.rest-assured:rest-assured:$restAssuredVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-api:$jUnitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$jUnitVersion")
    testCompile("org.junit.jupiter:junit-jupiter-params:$jUnitVersion")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

drill {
    version = "0.11.0"
    adminHost = System.getProperty("adminHost")
    if (System.getProperty("groupId") != null) {
        groupId = System.getProperty("groupId")
    } else {
        agentId = System.getProperty("agentId")
    }
    adminPort = 8090
    additionalParams = mutableMapOf(
        "browserProxyAddress" to "$adminHost:7777"
    )
    logLevel = com.epam.drill.agent.runner.LogLevels.DEBUG
}

tasks.named<Test>("test") {
    useJUnitPlatform()
    systemProperty("petclinicUrl", System.getProperty("petclinicUrl"))
}
