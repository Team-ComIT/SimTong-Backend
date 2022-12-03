package team.comit.simtong.persistence

import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.core.types.dsl.DatePath
import java.time.DayOfWeek
import java.time.LocalDate

/**
 *
 * Querydsl의 확장 함수를 관리하는 QuerydslExtensionUtils
 *
 * @author Chokyunghyeon
 * @date 2022/12/03
 * @version 1.0.0
 **/
object QuerydslExtensionUtils {

    /**
     * 입력된 날짜가 포함된 달
     */
    fun DatePath<LocalDate>.monthFilter(date: LocalDate): BooleanExpression {
        val startOfMonth = date.withDayOfMonth(1)
        val endOfMonth = date.withDayOfMonth(date.lengthOfMonth())

        return between(startOfMonth, endOfMonth)
    }

    /**
     * 입력된 날짜가 포함된 주
     */
    fun DatePath<LocalDate>.weekFilter(date: LocalDate) : BooleanExpression {
        val startOfWeek = date.with(DayOfWeek.MONDAY)
        val endOfWeek = date.with(DayOfWeek.SUNDAY)

        return between(startOfWeek, endOfWeek)
    }

}