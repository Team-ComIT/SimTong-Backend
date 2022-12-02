package team.comit.simtong.persistence.holiday.entity

import team.comit.simtong.domain.holiday.model.HolidayType
import team.comit.simtong.persistence.spot.entity.SpotJpaEntity
import team.comit.simtong.persistence.user.entity.UserJpaEntity
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.EnumType
import javax.persistence.Enumerated
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.MapsId
import javax.persistence.Table
import javax.validation.constraints.NotNull

/**
 *
 * 유저의 휴무일을 관리하는 HolidayJpaEntity
 *
 * @author Chokyunghyeon
 * @date 2022/12/02
 * @version 1.0.0
 **/
@Entity
@Table(name = "tbl_holiday")
class HolidayJpaEntity(

    @EmbeddedId
    val id: HolidayId,

    @field:NotNull
    @Enumerated(EnumType.STRING)
    val type: HolidayType,

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", columnDefinition = "BINARY(16)", nullable = false)
    val user: UserJpaEntity,

    @field:NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spot_id", columnDefinition = "BINARY(16)", nullable = false)
    val spot: SpotJpaEntity

)