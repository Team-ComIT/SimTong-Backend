package team.comit.simtong.persistence.team.entity

import org.hibernate.validator.constraints.Length
import team.comit.simtong.persistence.BaseUUIDEntity
import team.comit.simtong.persistence.spot.entity.SpotJpaEntity
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.validation.constraints.NotNull

@Entity
@Table(name = "tbl_team")
class TeamJpaEntity(

    @field:NotNull
    @field:Length(max = 8)
    val name: String,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spot_id", columnDefinition = "BINARY(16)", nullable = false)
    val spot: SpotJpaEntity,
) : BaseUUIDEntity()