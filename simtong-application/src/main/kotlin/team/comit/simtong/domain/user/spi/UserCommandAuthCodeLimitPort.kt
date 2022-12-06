package team.comit.simtong.domain.user.spi

import team.comit.simtong.domain.auth.model.AuthCodeLimit

interface UserCommandAuthCodeLimitPort {

    fun delete(authCodeLimit: AuthCodeLimit)

}