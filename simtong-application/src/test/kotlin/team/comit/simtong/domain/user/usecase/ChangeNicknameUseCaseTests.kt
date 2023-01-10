package team.comit.simtong.domain.user.usecase

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.mockito.kotlin.given
import org.springframework.boot.test.mock.mockito.MockBean
import team.comit.simtong.domain.user.dto.ChangeNicknameRequest
import team.comit.simtong.domain.user.exception.UserExceptions
import team.comit.simtong.domain.user.model.value.Authority
import team.comit.simtong.domain.user.model.User
import team.comit.simtong.domain.user.spi.CommandUserPort
import team.comit.simtong.domain.user.spi.QueryUserPort
import team.comit.simtong.domain.user.spi.UserSecurityPort
import team.comit.simtong.global.annotation.SimtongTest
import java.util.UUID

@SimtongTest
class ChangeNicknameUseCaseTests {

    @MockBean
    private lateinit var queryUserPort: QueryUserPort

    @MockBean
    private lateinit var userSecurityPort: UserSecurityPort

    @MockBean
    private lateinit var commandUserPort: CommandUserPort

    private lateinit var changeNicknameUseCase: ChangeNicknameUseCase

    private val id = UUID.randomUUID()

    private val requestStub: ChangeNicknameRequest by lazy {
        ChangeNicknameRequest(
            nickname = "test nickname"
        )
    }

    private val userStub: User by lazy {
        User(
            id = id,
            nickname = "test nickname",
            name = "test name",
            email = "test@test.com",
            password = "test password",
            employeeNumber = 1234567890,
            authority = Authority.ROLE_COMMON,
            spotId = id,
            teamId = id,
            profileImagePath = "test profile"
        )
    }

    @BeforeEach
    fun setUp() {
        changeNicknameUseCase = ChangeNicknameUseCase(
            queryUserPort,
            userSecurityPort,
            commandUserPort
        )
    }

    @Test
    fun `닉네임 변경 성공`() {
        // given
        given(queryUserPort.existsUserByNickname(requestStub.nickname))
            .willReturn(false)

        given(userSecurityPort.getCurrentUserId())
            .willReturn(id)

        given(queryUserPort.queryUserById(id))
            .willReturn(userStub)

        // when & then
        assertDoesNotThrow {
            changeNicknameUseCase.execute(requestStub)
        }
    }

    @Test
    fun `중복된 닉네임`() {
        // given
        given(queryUserPort.existsUserByNickname(requestStub.nickname))
            .willReturn(true)

        // when & then
        assertThrows<UserExceptions.AlreadyUsedNickname> {
            changeNicknameUseCase.execute(requestStub)
        }
    }

    @Test
    fun `찾을 수 없는 사용자`() {
        // given
        given(queryUserPort.queryUserById(id))
            .willReturn(null)

        // when & then
        assertThrows<UserExceptions.NotFound> {
            changeNicknameUseCase.execute(requestStub)
        }
    }

}