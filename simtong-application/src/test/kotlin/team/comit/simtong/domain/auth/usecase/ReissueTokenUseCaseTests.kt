package team.comit.simtong.domain.auth.usecase

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit.jupiter.SpringExtension
import team.comit.simtong.domain.auth.exception.RefreshTokenNotFoundException
import team.comit.simtong.domain.auth.model.RefreshToken
import team.comit.simtong.domain.auth.spi.QueryRefreshTokenPort
import team.comit.simtong.domain.auth.spi.ReceiveTokenPort
import team.comit.simtong.domain.auth.usecase.dto.TokenResponse
import team.comit.simtong.domain.user.model.Authority
import java.util.*

@ExtendWith(SpringExtension::class)
class ReissueTokenUseCaseTests {

    @MockBean
    private lateinit var receiveTokenPort: ReceiveTokenPort

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
        reissueTokenUseCase = ReissueTokenUseCase(receiveTokenPort, queryRefreshTokenPort)
    }

    @Test
    fun `토큰 재발급 성공`() {
        // given
        given(queryRefreshTokenPort.queryRefreshTokenByToken(token))
            .willReturn(refreshTokenStub)

        given(receiveTokenPort.generateJsonWebToken(userId, authority))
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
        assertThrows<RefreshTokenNotFoundException> {
            reissueTokenUseCase.execute(token)
        }
    }

}