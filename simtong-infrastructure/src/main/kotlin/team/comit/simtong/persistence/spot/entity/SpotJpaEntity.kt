package team.comit.simtong.persistence.spot.entity

import org.hibernate.validator.constraints.Length
import team.comit.simtong.persistence.BaseUUIDEntity
import java.util.*
import javax.persistence.Entity
import javax.persistence.Table
import javax.validation.constraints.NotNull

/**
 *
 * 지점에 대해 관리하는 SpotJpaEntity
 *
 * @author kimbeomjin
 * @date 2022/08/21
 * @version 1.0.0
 **/
@Entity
@Table(name = "tbl_spot")
class SpotJpaEntity(
    override val id: UUID?,

    @field:NotNull
    @field:Length(max = 20)
    val name: String,

    @field:NotNull
    @field:Length(max = 40)
    val location: String
) : BaseUUIDEntity(id)