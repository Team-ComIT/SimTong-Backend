package team.comit.simtong.persistence.user.entity

import java.util.*
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.MapsId
import javax.persistence.Table
import javax.validation.constraints.NotNull

@Entity
@Table(name = "tbl_device")
class DeviceTokenJpaEntity(

    @Id
    val userId: UUID,

    @MapsId
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", columnDefinition = "BINARY(16)", nullable = false)
    val user: UserJpaEntity,

    @field:NotNull
    val token: String
)