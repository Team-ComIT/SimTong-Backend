object Dependencies {

    // kotlin
    const val KOTLIN_REFLECT = "org.jetbrains.kotlin:kotlin-reflect"
    const val KOTLIN_JDK = "org.jetbrains.kotlin:kotlin-stdlib-jdk8"
    const val JACKSON = "com.fasterxml.jackson.module:jackson-module-kotlin"

    // java servlet
    const val JAVA_SERVLET = "javax.servlet:javax.servlet-api:${DependencyVersions.SERVLET}"

    // web
    const val SPRING_WEB = "org.springframework.boot:spring-boot-starter-web"

    // validation
    const val SPRING_VALIDATION = "org.springframework.boot:spring-boot-starter-validation"

    // transaction
    const val SPRING_TRANSACTION = "org.springframework:spring-tx:${DependencyVersions.SPRING_TRANSACTION}"

    // querydsl
    const val QUERYDSL = "com.querydsl:querydsl-jpa:${DependencyVersions.QUERYDSL}"
    const val QUERYDSL_PROCESSOR = "com.querydsl:querydsl-apt:${DependencyVersions.QUERYDSL}:jpa"

    // configuration
    const val CONFIGURATION_PROCESSOR = "org.springframework.boot:spring-boot-configuration-processor"

    // database
    const val SPRING_DATA_JPA = "org.springframework.boot:spring-boot-starter-data-jpa"
    const val MYSQL_CONNECTOR = "mysql:mysql-connector-java"
    const val SPRING_REDIS = "org.springframework.boot:spring-boot-starter-data-redis"
    const val REDIS = "org.springframework.data:spring-data-redis:${DependencyVersions.REDIS_VERSION}"

    // security
    const val SPRING_SECURITY = "org.springframework.boot:spring-boot-starter-security"

    // jwt
    const val JWT = "io.jsonwebtoken:jjwt:${DependencyVersions.JWT_VERSION}"

    // aws
    const val SPRING_AWS = "org.springframework.cloud:spring-cloud-starter-aws:${DependencyVersions.AWS_VERSION}"
    const val AWS_SES = "com.amazonaws:aws-java-sdk-ses:${DependencyVersions.AWS_SES_VERSION}"

    // test
    const val SPRING_TEST = "org.springframework.boot:spring-boot-starter-test:${PluginVersions.SPRING_BOOT_VERSION}"
    const val MOCKITO_KOTLIN = "org.mockito.kotlin:mockito-kotlin:${PluginVersions.MOCKITO_KOTLIN_VERSION}"

    // s3 test
    const val S3MOCK = "io.findify:s3mock_2.12:${DependencyVersions.S3MOCK}"

    // bytebuddy
    const val BYTEBUDDY = "net.bytebuddy:byte-buddy:${DependencyVersions.BYTE_BUDDY}"

    // commons io
    const val COMMONS_IO = "commons-io:commons-io:${DependencyVersions.COMMONS_IO}"

    // poi
    const val POI = "org.apache.poi:poi:${DependencyVersions.POI_VERSION}"
    const val POI_OOXML = "org.apache.poi:poi-ooxml:${DependencyVersions.POI_VERSION}"

    // firebase
    const val FIREBASE_ADMIN = "com.google.firebase:firebase-admin:${DependencyVersions.FIREBASE_ADMIN_VERSION}"

    // sentry
    const val SENTRY = "io.sentry:sentry-spring-boot-starter:${DependencyVersions.SENTRY_VERSION}"
}