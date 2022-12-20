package team.comit.simtong.persistence.holiday.entity

import org.jetbrains.annotations.NotNull
import team.comit.simtong.persistence.spot.entity.SpotJpaEntity
import java.time.LocalDate
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.MapsId
import javax.persistence.Table

/**
 *
 * 휴무일 작성 기간을 관리하는 HolidayPeriodJpaEntity
 *
 * @author Chokyunghyeon
 * @date 2022/12/20
 * @version 1.0.0
 **/
@Entity
@Table(name = "tbl_holiday_period")
class HolidayPeriodJpaEntity(

    @EmbeddedId
    val id: HolidayPeriodId,

    @field:NotNull
    val startAt: LocalDate,

    @field:NotNull
    val endAt: LocalDate,

    @MapsId("spotId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spot_id", columnDefinition = "BINARY(16)", nullable = false)
    val spot: SpotJpaEntity
)