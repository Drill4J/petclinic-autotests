import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.50"
}

group = "com.epam.epm-d4j"
version = "1.0-SNAPSHOT"

val jUnitVersion = "5.4.0"
val restAssuredVersion = "4.0.0"

repositories {
    mavenCentral()
    mavenLocal()
    jcenter()
}

dependencies {
    implementation(kotlin("stdlib-jdk8"))
    implementation("io.rest-assured:rest-assured:$restAssuredVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-api:$jUnitVersion")
    testRuntime("org.junit.jupiter:junit-jupiter-api:$jUnitVersion")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:$jUnitVersion")
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.named<Test>("test") {
    useJUnitPlatform()
    systemProperty("petclinic.url", System.getProperty("petclinic.url"))
    val agentPath = System.getProperty("agentPath")
    val adminUrl = System.getProperty("admin.url")
    val agentId = System.getProperty("agent.id")
    val pluginId = System.getProperty("plugin.id")
    val serviceGroupId = System.getProperty("serviceGroup.id")
    jvmArgs("-javaagent:$agentPath=adminUrl=$adminUrl,agentId=$agentId,pluginId=$pluginId,serviceGroupId=$serviceGroupId")
}
