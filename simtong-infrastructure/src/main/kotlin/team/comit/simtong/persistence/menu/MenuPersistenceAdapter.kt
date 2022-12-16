package team.comit.simtong.persistence.menu

import team.comit.simtong.persistence.menu.entity.QMenuJpaEntity.menuJpaEntity as menu
import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Component
import team.comit.simtong.domain.menu.model.Menu
import team.comit.simtong.domain.menu.spi.MenuPort
import team.comit.simtong.persistence.QuerydslExtensionUtils.sameMonthFilter
import team.comit.simtong.persistence.menu.mapper.MenuMapper
import team.comit.simtong.persistence.menu.repository.MenuJpaRepository
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
    private val menuRepository: MenuJpaRepository,
    private val queryFactory: JPAQueryFactory
) : MenuPort {

    override fun existsMenuByMonthAndSpotId(date: LocalDate, spotId: UUID): Boolean {
        return queryFactory
            .selectFrom(menu)
            .where(
                menu.menuId.spotId.eq(spotId),
                menu.menuId.date.sameMonthFilter(date)
            )
            .fetchOne() != null
    }

    override fun queryMenusByPeriodAndSpotId(startAt: LocalDate, endAt: LocalDate, spotId: UUID): List<Menu> {
        return queryFactory
            .selectFrom(menu)
            .where(
                menu.menuId.spotId.eq(spotId),
                menu.menuId.date.between(startAt, endAt)
            )
            .orderBy(menu.menuId.date.asc())
            .fetch()
            .map { menuMapper.toDomain(it)!! }
    }

    override fun queryMenusByPeriodAndSpotName(startAt: LocalDate, endAt: LocalDate, spotName: String): List<Menu> {
        return queryFactory
            .selectFrom(menu)
            .where(
                menu.spot.name.eq(spotName),
                menu.menuId.date.between(startAt, endAt)
            )
            .orderBy(menu.menuId.date.asc())
            .fetch()
            .map { menuMapper.toDomain(it)!! }
    }

    override fun saveAll(menus: List<Menu>) {
        menuRepository.saveAll(
            menus.map(menuMapper::toEntity)
        )
    }

}