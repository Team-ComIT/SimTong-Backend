package team.comit.simtong.persistence.spot

import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import team.comit.simtong.domain.spot.model.Spot
import team.comit.simtong.domain.spot.spi.SpotPort
import team.comit.simtong.persistence.spot.mapper.SpotMapper
import java.util.UUID

/**
 *
 * Spot의 영속성을 관리하는 SpotPersistenceAdapter
 *
 * @author Chokyunghyeon
 * @date 2022/09/18
 * @version 1.2.3
 **/
@Component
class SpotPersistenceAdapter(
    private val spotJpaRepository: SpotJpaRepository,
    private val spotMapper: SpotMapper
) : SpotPort {

    override fun existsSpotById(id: UUID): Boolean {
        return spotJpaRepository.existsById(id)
    }

    override fun queryAllSpot(): List<Spot> {
        return spotJpaRepository.findAll()
            .mapNotNull(spotMapper::toDomain)
    }

    override fun querySpotByName(name: String): Spot? {
        return spotJpaRepository.querySpotJpaEntityByName(name)
            .let(spotMapper::toDomain)
    }

    override fun querySpotById(id: UUID): Spot? {
        return spotJpaRepository.findByIdOrNull(id)
            .let(spotMapper::toDomain)
    }
}