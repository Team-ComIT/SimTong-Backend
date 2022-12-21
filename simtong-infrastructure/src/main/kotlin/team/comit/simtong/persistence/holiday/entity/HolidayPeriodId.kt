package team.comit.simtong.persistence.holiday.entity

import java.io.Serializable
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Embeddable

/**
 *
 * 휴무일 작성 기간의 기본키를 담당하는 HolidayPeriodId
 *
 * @author Chokyunghyeon
 * @date 2022/12/20
 * @version 1.0.0
 **/
@Embeddable
data class HolidayPeriodId(
    @Column(nullable = false)
    val year: Int,

    @Column(nullable = false)
    val month: Int,

    @Column(nullable = false, columnDefinition = "BINARY(16)")
    val spotId: UUID
) : Serializable