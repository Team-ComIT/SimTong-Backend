plugins {
    id("org.springframework.boot") version PluginVersions.SPRING_BOOT_VERSION
    id("io.spring.dependency-management") version PluginVersions.DEPENDENCY_MANAGER_VERSION
    kotlin("plugin.spring") version PluginVersions.SPRING_PLUGIN_VERSION
    kotlin("plugin.jpa") version PluginVersions.JPA_PLUGIN_VERSION
}

dependencies {
    // impl project
    implementation(project(":simtong-domain"))
    implementation(project(":simtong-application"))
    implementation(project(":simtong-presentation"))

    // kotlin
    implementation(Dependencies.JACKSON)

    // validation
    implementation(Dependencies.SPRING_VALIDATION)

    // security
    implementation(Dependencies.SPRING_SECURITY)

    //jwt
    implementation(Dependencies.JWT)

    // database
    implementation(Dependencies.SPRING_DATA_JPA)
    runtimeOnly(Dependencies.MYSQL_CONNECTOR)
    implementation(Dependencies.REDIS)
    implementation(Dependencies.SPRING_REDIS)

    // querydsl
    implementation(Dependencies.QUERYDSL)
    kapt(Dependencies.QUERYDSL_PROCESSOR)

    // aws
    implementation(Dependencies.SPRING_AWS)
    implementation(Dependencies.AWS_SES)

    // mapstruct
    implementation(Dependencies.MAPSTRUCT)
    kapt(Dependencies.MAPSTRUCT_PROCESSOR)

    // read-file
    implementation(Dependencies.COMMONS_IO)
    implementation(Dependencies.POI)
    implementation(Dependencies.POI_OOXML)

    // firebase
    implementation(Dependencies.FIREBASE_ADMIN)

    // sentry
    implementation(Dependencies.SENTRY)

    // configuration
    annotationProcessor(Dependencies.CONFIGURATION_PROCESSOR)

    // s3mock
    testImplementation(Dependencies.S3MOCK)
}

kapt {
    arguments {
        arg("mapstruct.defaultComponentModel", "spring")
        arg("mapstruct.unmappedTargetPolicy", "ignore")
    }
}

allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}

noArg {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
    annotation("javax.persistence.Embeddable")
}

tasks.getByName<Jar>("jar") {
    enabled = false
}