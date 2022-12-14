package team.comit.simtong.persistence.team.entity

import org.hibernate.validator.constraints.Length
import team.comit.simtong.persistence.BaseUUIDEntity
import team.comit.simtong.persistence.spot.entity.SpotJpaEntity
import java.util.UUID
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.validation.constraints.NotNull

/**
 *
 * 지점에 속한 팀을 관리하는 TeamJpaEntity
 *
 * @author kimbeomjin
 * @date 2022/08/21
 * @version 1.2.3
 **/
@Entity
@Table(name = "tbl_team")
class TeamJpaEntity(
    override val id: UUID?,

    @field:NotNull
    @field:Length(max = 8)
    val name: String,

    @field:NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spot_id", columnDefinition = "BINARY(16)")
    val spot: SpotJpaEntity,
) : BaseUUIDEntity(id)