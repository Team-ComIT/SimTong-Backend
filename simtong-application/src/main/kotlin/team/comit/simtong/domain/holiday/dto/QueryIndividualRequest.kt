package team.comit.simtong.domain.holiday.dto

import java.time.LocalDate

/**
 *
 * 개인 휴무일 조회 요청 정보를 전달하는 QueryIndividualRequest
 *
 * @author Chokyunghyeon
 * @date 2022/12/21
 * @version 1.0.0
 **/
data class QueryIndividualRequest(
    val startAt: LocalDate,

    val endAt: LocalDate,

    val status: String
)