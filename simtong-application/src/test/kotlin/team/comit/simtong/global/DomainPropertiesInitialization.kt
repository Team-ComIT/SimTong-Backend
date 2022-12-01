package team.comit.simtong.global

object DomainPropertiesInitialization {
    init {
        DomainProperties.putAll(
            mapOf(
                Pair(DomainPropertiesPrefix.USER_DEFAULT_IMAGE, "test"),
                Pair(DomainPropertiesPrefix.AUTHCODE_EXP, "12345"),
                Pair(DomainPropertiesPrefix.AUTHCODELIMIT_EXP, "12345"),
                Pair(DomainPropertiesPrefix.AUTHCODELIMIT_VERIFIED_EXP, "12345"),
                Pair(DomainPropertiesPrefix.AUTHCODELIMIT_MAX_ATTEMPT_COUNT, "12345"),
                Pair(DomainPropertiesPrefix.HEAD_SHOP, "test")
            )
        )
    }
}