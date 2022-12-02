package team.comit.simtong.persistence.holiday.entity

import java.io.Serializable
import java.time.LocalDate
import java.util.UUID
import javax.persistence.Column
import javax.persistence.Embeddable

/**
 *
 * 휴무일의 기본키를 구성하는 HolidayId
 *
 * @author Chokyunghyeon
 * @date 2022/12/02
 * @version 1.0.0
 **/
@Embeddable
data class HolidayId(

    @Column(nullable = false)
    val date: LocalDate,

    @Column(nullable = false)
    val userId: UUID
) : Serializable