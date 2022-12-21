package team.comit.simtong.persistence.holiday.entity

import team.comit.simtong.persistence.spot.entity.SpotJpaEntity
import java.io.Serializable
import java.time.LocalDate
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.persistence.EmbeddedId
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.MapsId
import javax.persistence.Table
import javax.validation.constraints.NotNull

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
    val id: Id,

    @MapsId("spotId")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "spot_id", columnDefinition = "BINARY(16)", nullable = false)
    val spot: SpotJpaEntity,

    @field:NotNull
    val startAt: LocalDate,

    @field:NotNull
    val endAt: LocalDate
) {

    /**
     *
     * 휴무일 작성 기간의 기본키를 담당하는 HolidayPeriod Id
     *
     * @author Chokyunghyeon
     * @date 2022/12/20
     * @version 1.0.0
     **/
    @Embeddable
    data class Id(
        @Column(nullable = false)
        val year: Int,

        @Column(nullable = false)
        val month: Int,

        @Column(columnDefinition = "BINARY(16)", nullable = false)
        val spotId: UUID
    ) : Serializable

}