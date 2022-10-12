package team.comit.simtong.persistence.menu

import com.querydsl.jpa.impl.JPAQueryFactory
import org.springframework.stereotype.Component
import team.comit.simtong.domain.menu.model.Menu
import team.comit.simtong.domain.menu.spi.MenuPort
import team.comit.simtong.persistence.menu.entity.QMenuJpaEntity.menuJpaEntity
import team.comit.simtong.persistence.menu.mapper.MenuMapper
import team.comit.simtong.persistence.spot.entity.QSpotJpaEntity.spotJpaEntity
import java.util.*

/**
 *
 * Menu의 영속성을 관리하는 MenuPersistenceAdapter
 *
 * @author kimbeomjin
 * @date 2022/09/20
 * @version 1.0.0
 **/
@Component
class MenuPersistenceAdapter(
    private val menuMapper: MenuMapper,
    private val queryFactory: JPAQueryFactory
) : MenuPort {

    override fun queryMenuBySpotId(year: Int, month: Int, spotId: UUID): List<Menu> {
        return queryFactory
            .selectFrom(menuJpaEntity)
            .leftJoin(menuJpaEntity.spot, spotJpaEntity)
            .on(spotJpaEntity.id.eq(spotId))
            .where(
                yearEq(year),
                monthEq(month)
            )
            .fetch()
            .map {
                menuMapper.toDomain(it)!!
            }
    }

    override fun queryMenuBySpotName(year: Int, month: Int, spotName: String): List<Menu> {
        return queryFactory
            .selectFrom(menuJpaEntity)
            .leftJoin(menuJpaEntity.spot, spotJpaEntity)
            .where(
                yearEq(year),
                monthEq(month),
                spotJpaEntity.name.eq(spotName)
            )
            .fetch()
            .map {
                menuMapper.toDomain(it)!!
            }
    }

    private fun yearEq(year: Int) = menuJpaEntity.date.year().eq(year)

    private fun monthEq(month: Int) = menuJpaEntity.date.month().eq(month)

}