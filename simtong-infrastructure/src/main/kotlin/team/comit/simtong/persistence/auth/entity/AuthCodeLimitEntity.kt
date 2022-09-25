package team.comit.simtong.persistence.auth.entity

import org.hibernate.annotations.ColumnDefault
import org.springframework.data.annotation.Id
import org.springframework.data.redis.core.RedisHash
import org.springframework.data.redis.core.TimeToLive
import javax.validation.constraints.NotNull

/**
 *
 * 이메일 인증 정책을 관리하는 AuthCodeLimitEntity
 *
 * @author Chokyunghyeon
 * @author kimbeomjin
 * @date 2022/08/29
 * @version 1.0.0
 **/
@RedisHash("tbl_authcode_limit")
class AuthCodeLimitEntity(
    @Id
    val key: String,

    @field:NotNull
    @TimeToLive
    val expirationTime: Int,

    @field:NotNull
    @ColumnDefault("1")
    val attemptCount: Short = 1,

    @field:NotNull
    @ColumnDefault("false")
    val isVerified: Boolean = false
)