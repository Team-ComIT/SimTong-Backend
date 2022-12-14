package team.comit.simtong.persistence.user.entity

import java.util.UUID
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.MapsId
import javax.persistence.Table
import javax.validation.constraints.NotNull

/**
 *
 * 유저(직원, 관리자)의 디바이스 토큰을 관리하는 DeviceTokenJpaEntity
 *
 * @author kimbeomjin
 * @date 2022/08/21
 * @version 1.0.0
 **/
@Entity
@Table(name = "tbl_device")
class DeviceTokenJpaEntity(
    @Id
    val userId: UUID,

    @MapsId
    @field:NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", columnDefinition = "BINARY(16)")
    val user: UserJpaEntity,

    @field:NotNull
    val token: String
)