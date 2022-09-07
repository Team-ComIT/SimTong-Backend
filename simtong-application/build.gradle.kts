plugins {
    id("org.jetbrains.kotlin.plugin.allopen") version PluginVersions.ALLOPEN_VERSION
}

dependencies {

    // spring transaction
    implementation(Dependencies.SPRING_TRANSACTION)

    // api project
    api(project(":simtong-domain"))
}

allOpen {
    annotation("team.comit.simtong.global.annotation.UseCase")
    annotation("team.comit.simtong.global.annotation.ReadOnlyUseCase")
}