object Dependencies {
    const val KOTLIN_REFLECT = "org.jetbrains.kotlin:kotlin-reflect"
    const val KOTLIN_JDK = "org.jetbrains.kotlin:kotlin-stdlib-jdk8"

    const val SPRING_WEB = "org.springframework.boot:spring-boot-starter-web"
    const val SPRING_VALIDATION = "org.springframework.boot:spring-boot-starter-validation"
    const val SPRING_SECURITY = "org.springframework.boot:spring-boot-starter-security"
    const val CONFIGURATION_PROCESSOR = "org.springframework.boot:spring-boot-configuration-processor"

    const val JACKSON = "com.fasterxml.jackson.module:jackson-module-kotlin"

    const val SPRING_DATA_JPA = "org.springframework.boot:spring-boot-starter-data-jpa"
    const val MYSQL_CONNECTOR = "mysql:mysql-connector-java"
    const val SPRING_REDIS = "org.springframework.boot:spring-boot-starter-data-redis"

    const val JAVA_SERVLET = "javax.servlet:javax.servlet-api:${DependencyVersions.SERVLET}"

    const val JWT = "io.jsonwebtoken:jjwt:${DependencyVersions.JWT_VERSION}"
    const val SPRING_TEST = "org.springframework.boot:spring-boot-starter-test:${PluginVersions.SPRING_BOOT_VERSION}"

    const val MAPSTRUCT = "org.mapstruct:mapstruct:${DependencyVersions.MAPSTRUCT_VERSION}"
    const val MAPSTRUCT_PROCESSER = "org.mapstruct:mapstruct-processor:${DependencyVersions.MAPSTRUCT_VERSION}"

    const val SPRING_AWS = "org.springframework.cloud:spring-cloud-starter-aws:${DependencyVersions.AWS_VERSION}"

    const val REDIS = "org.springframework.data:spring-data-redis:${DependencyVersions.REDIS_VERSION}"
}