package team.comit.simtong.domain.user.usecase

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.mockito.BDDMockito.given
import org.springframework.boot.test.mock.mockito.MockBean
import team.comit.simtong.domain.auth.exception.UsedNicknameException
import team.comit.simtong.domain.user.spi.QueryUserPort
import team.comit.simtong.global.annotation.SimtongTest

@SimtongTest
class CheckNicknameDuplicationUseCaseTests {

    @MockBean
    private lateinit var queryUserPort: QueryUserPort

    private lateinit var checkNicknameDuplicationUseCase: CheckNicknameDuplicationUseCase

    private val nickname = "test nickname"

    @BeforeEach
    fun setUp() {
        checkNicknameDuplicationUseCase = CheckNicknameDuplicationUseCase(queryUserPort)
    }

    @Test
    fun `닉네임이 중복 되지 않음`() {
        // given
        given(queryUserPort.existsUserByNickname(nickname))
            .willReturn(false)

        // when & then
        assertDoesNotThrow {
            checkNicknameDuplicationUseCase.execute(nickname)
        }
    }

    @Test
    fun `닉네임이 중복됨`() {
        // given
        given(queryUserPort.existsUserByNickname(nickname))
            .willReturn(true)

        // when & then
        assertThrows<UsedNicknameException> {
            checkNicknameDuplicationUseCase.execute(nickname)
        }
    }

}