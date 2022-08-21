plugins {
    id("org.springframework.boot") version PluginVersions.SPRING_BOOT_VERSION
    id("io.spring.dependency-management") version PluginVersions.DEPENDENCY_MANAGER_VERSION
    kotlin("plugin.spring") version PluginVersions.SPRING_PLUGIN_VERSION
}

dependencies {

    // web
    implementation(Dependencies.SPRING_WEB)

    // impl project
    implementation(project(":simtong-infrastructure"))
}

tasks.getByName<Jar>("jar") {
    enabled = false
}