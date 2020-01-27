import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.github.jengelman.gradle.plugins.shadow.tasks.ShadowJar


val ktorVersion = "1.2.6"
val logbackVersion = "1.2.3"
val logstashVersion = "5.1"
val kotlinLoggerVersion = "1.7.6"
val medlemskapDomeneVersion = "18"
val junitJupiterVersion = "5.4.0"

val mainClass = "no.nav.medlemskap.ApplicationKt"

val githubUser: String by project
val githubPassword: String by project

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.3.61"
    id("com.github.johnrengelman.shadow") version "5.1.0"
}

repositories {
    mavenCentral()
    jcenter()
    maven {
        url = uri("https://maven.pkg.github.com/navikt/medlemskap-domene")
        credentials {
            username = githubUser
            password = githubPassword
        }
    }
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
    implementation("io.ktor:ktor-server-netty:$ktorVersion")
    implementation("io.ktor:ktor-gson:$ktorVersion")
    implementation("ch.qos.logback:logback-classic:$logbackVersion")
    implementation("net.logstash.logback:logstash-logback-encoder:$logstashVersion")
    implementation("no.nav.medlemskap:medlemskap-domene:$medlemskapDomeneVersion")
    implementation("io.github.microutils:kotlin-logging:$kotlinLoggerVersion")

    testImplementation("org.junit.jupiter:junit-jupiter-api:$junitJupiterVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-params:$junitJupiterVersion")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:$junitJupiterVersion")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

tasks.withType<Wrapper> {
    gradleVersion = "5.6.3"
}

tasks.withType<ShadowJar> {
    archiveBaseName.set("app")
    archiveClassifier.set("")
    manifest {
        attributes(
                mapOf(
                        "Main-Class" to mainClass
                )
        )
    }
}
