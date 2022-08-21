package team.comit.simtong.persistence.spot.entity

import org.hibernate.validator.constraints.Length
import team.comit.simtong.persistence.BaseUUIDEntity
import javax.persistence.Entity
import javax.persistence.Table
import javax.validation.constraints.NotNull

@Entity
@Table(name = "tbl_spot")
class SpotJpaEntity(

    @field:NotNull
    @field:Length(max = 20)
    val name: String,

    @field:NotNull
    @field:Length(max = 40)
    val location: String
) : BaseUUIDEntity()