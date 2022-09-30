package team.comit.simtong.persistence.auth.mapper

import org.mapstruct.Mapper
import team.comit.simtong.domain.auth.model.AuthCode
import team.comit.simtong.persistence.GenericMapper
import team.comit.simtong.persistence.auth.entity.AuthCodeEntity

/**
 *
 * AuthCodeEntity와 DomainAuthCode를 변환하는 AuthCodeMapper
 *
 * @author Chokyunghyeon
 * @date 2022/09/25
 * @version 1.0.0
 **/
@Mapper
abstract class AuthCodeMapper : GenericMapper<AuthCodeEntity, AuthCode> {
}