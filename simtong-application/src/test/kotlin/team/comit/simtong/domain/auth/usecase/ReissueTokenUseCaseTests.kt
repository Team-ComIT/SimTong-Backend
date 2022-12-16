package team.comit.simtong.domain.auth.usecase

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.mockito.BDDMockito.given
import org.springframework.boot.test.mock.mockito.MockBean
import team.comit.simtong.domain.auth.dto.TokenResponse
import team.comit.simtong.domain.auth.exception.AuthExceptions
import team.comit.simtong.domain.auth.model.RefreshToken
import team.comit.simtong.domain.auth.spi.JwtPort
import team.comit.simtong.domain.auth.spi.QueryRefreshTokenPort
import team.comit.simtong.domain.user.model.Authority
import team.comit.simtong.global.annotation.SimtongTest
import java.util.Date
import java.util.UUID

@SimtongTest
class ReissueTokenUseCaseTests {

    @MockBean
    private lateinit var jwtPort: JwtPort

    @MockBean
    private lateinit var queryRefreshTokenPort: QueryRefreshTokenPort

    private lateinit var reissueTokenUseCase: ReissueTokenUseCase

    private val token = "test token"

    private val userId = UUID.randomUUID()

    private val authority = Authority.ROLE_COMMON

    private val refreshTokenStub: RefreshToken by lazy {
        RefreshToken(
            token = token,
            userId = userId,
            authority = authority,
            expirationTime = 987654321
        )
    }

    private val tokenResponseStub: TokenResponse by lazy {
        TokenResponse(
            accessToken = "test access token",
            accessTokenExp = Date(),
            refreshToken = "test refresh token"
        )
    }

    @BeforeEach
    fun setUp() {
        reissueTokenUseCase = ReissueTokenUseCase(jwtPort, queryRefreshTokenPort)
    }

    @Test
    fun `토큰 재발급 성공`() {
        // given
        given(queryRefreshTokenPort.queryRefreshTokenByToken(token))
            .willReturn(refreshTokenStub)

        given(jwtPort.receiveToken(userId, authority))
            .willReturn(tokenResponseStub)

        // when & then
        assertEquals(reissueTokenUseCase.execute(token), tokenResponseStub)
    }

    @Test
    fun `토큰 찾기 실패`() {
        //given
        given(queryRefreshTokenPort.queryRefreshTokenByToken(token))
            .willReturn(null)

        // when & then
        assertThrows<AuthExceptions.RefreshTokenNotFound> {
            reissueTokenUseCase.execute(token)
        }
    }

}