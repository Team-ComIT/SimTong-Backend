plugins {
    kotlin("plugin.allopen") version PluginVersions.ALLOPEN_VERSION
}

dependencies {
}

allOpen {
    annotation("team.comit.simtong.global.annotation.DomainService")
}