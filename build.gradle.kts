plugins {
    kotlin("jvm") version PluginVersions.JVM_VERSION
}

subprojects {
    apply {
        plugin("org.jetbrains.kotlin.jvm")
        version = PluginVersions.JVM_VERSION
    }

    apply {
        plugin("org.jetbrains.kotlin.kapt")
        version = PluginVersions.KAPT_VERSION
    }

    dependencies {

        // kotlin
        implementation(Dependencies.KOTLIN_REFLECT)
        implementation(Dependencies.KOTLIN_JDK)

        // java servlet
        implementation(Dependencies.JAVA_SERVLET)

        // test
        implementation(Dependencies.SPRING_TEST)
        testImplementation(Dependencies.MOCKITO_KOTLIN)
    }
}

allprojects {
    group = "team.comit"
    version = "0.0.1-SNAPSHOT"

    apply(plugin = "jacoco")

    tasks {
        compileKotlin {
            kotlinOptions {
                freeCompilerArgs = listOf("-Xjsr305=strict")
                jvmTarget = "17"
            }
        }

        compileJava {
            sourceCompatibility = JavaVersion.VERSION_17.majorVersion
        }

        test {
            useJUnitPlatform()
        }
    }

    repositories {
        mavenCentral()
    }
}

tasks.register<JacocoReport>("jacocoRootReport") {
    subprojects {
        this@subprojects.plugins.withType<JacocoPlugin>().configureEach {
            this@subprojects.tasks.matching {
                it.extensions.findByType<JacocoTaskExtension>() != null
            }
                .configureEach {
                    sourceSets(this@subprojects.the<SourceSetContainer>().named("main").get())
                    executionData(this)
                }
        }
    }

    reports {
        xml.outputLocation.set(File("${buildDir}/reports/jacoco/test/jacocoTestReport.xml"))
        xml.required.set(true)
        html.required.set(false)
    }
}

tasks.getByName<Jar>("jar") {
    enabled = false
}