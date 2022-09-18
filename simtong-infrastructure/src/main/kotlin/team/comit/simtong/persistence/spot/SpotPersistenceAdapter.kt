package team.comit.simtong.persistence.spot

import org.springframework.stereotype.Component
import team.comit.simtong.domain.spot.spi.DomainQuerySpotPort
import team.comit.simtong.persistence.spot.mapper.SpotMapper

/**
 *
 * Spot의 영속성을 관리하는 SpotPersistenceAdapter
 *
 * @author Chokyunghyeon
 * @date 2022/09/18
 * @version 1.0.0
 **/
@Component
class SpotPersistenceAdapter(
    private val spotJpaRepository: SpotJpaRepository,
    private val spotMapper: SpotMapper
): DomainQuerySpotPort {

    override fun querySpotByName(name: String) = spotMapper.toDomain(
        spotJpaRepository.querySpotJpaEntityByName(name)
    )

}