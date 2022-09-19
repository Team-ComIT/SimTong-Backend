plugins {
    kotlin("plugin.allopen") version PluginVersions.ALLOPEN_VERSION
}

dependencies {
    // impl project
    implementation(project(":simtong-domain"))

    // spring transaction
    implementation(Dependencies.SPRING_TRANSACTION)
}

allOpen {
    annotation("team.comit.simtong.global.annotation.UseCase")
    annotation("team.comit.simtong.global.annotation.ReadOnlyUseCase")
}