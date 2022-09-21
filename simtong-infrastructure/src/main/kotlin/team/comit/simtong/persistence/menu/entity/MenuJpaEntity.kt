package team.comit.simtong.persistence.menu.entity

import team.comit.simtong.persistence.spot.entity.SpotJpaEntity
import java.time.LocalDate
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.Table
import javax.validation.constraints.NotNull

/**
 *
 * 지점에 속한 메뉴를 관리하는 MenuJpaEntity
 *
 * @author kimbeomjin
 * @date 2022/09/20
 * @version 1.0.0
 **/
@Entity
@Table(name = "tbl_menu")
class MenuJpaEntity(

    @Id
    val date: LocalDate,

    @field:NotNull
    val meal: String,

    @field:NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spot_id", columnDefinition = "BINARY(16)")
    val spot: SpotJpaEntity
)