package team.comit.simtong.persistence.menu

import com.querydsl.core.types.dsl.BooleanExpression
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Component
import team.comit.simtong.domain.menu.model.Menu
import team.comit.simtong.domain.menu.spi.MenuPort
import team.comit.simtong.persistence.QuerydslExtensionUtils.sameMonthFilter
import team.comit.simtong.persistence.menu.entity.QMenuJpaEntity.menuJpaEntity
import team.comit.simtong.persistence.menu.mapper.MenuMapper
import team.comit.simtong.persistence.spot.entity.QSpotJpaEntity.spotJpaEntity
import java.time.LocalDate
import java.util.UUID

/**
 *
 * Menu의 영속성을 관리하는 MenuPersistenceAdapter
 *
 * @author kimbeomjin
 * @author Chokyunghyeon
 * @date 2022/09/20
 * @version 1.0.0
 **/
@Component
class MenuPersistenceAdapter(
    private val menuMapper: MenuMapper,
    private val queryFactory: JPAQueryFactory
) : MenuPort {

    override fun queryMenuByMonthAndSpotId(date: LocalDate, spotId: UUID): List<Menu> {
        return queryFactory
            .selectFrom(menuJpaEntity)
            .join(menuJpaEntity.spot, spotJpaEntity)
            .on(spotJpaEntity.id.eq(spotId))
            .where(
                menuFilter(date)
            )
            .fetch()
            .map {
                menuMapper.toDomain(it)!!
            }
    }

    override fun queryMenuByMonthAndSpotName(date: LocalDate, spotName: String): List<Menu> {
        return queryFactory
            .selectFrom(menuJpaEntity)
            .join(menuJpaEntity.spot, spotJpaEntity)
            .on(spotJpaEntity.name.eq(spotName))
            .where(
                menuFilter(date)
            )
            .fetch()
            .map {
                menuMapper.toDomain(it)!!
            }
    }

    private fun menuFilter(date: LocalDate) : BooleanExpression {
        return menuJpaEntity.id.date.sameMonthFilter(date)
    }

}