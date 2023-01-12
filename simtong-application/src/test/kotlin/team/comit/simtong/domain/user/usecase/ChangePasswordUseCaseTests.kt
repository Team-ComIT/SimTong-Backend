package team.comit.simtong.domain.user.usecase

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.mockito.BDDMockito.given
import org.springframework.boot.test.mock.mockito.MockBean
import team.comit.simtong.domain.user.dto.request.ChangePasswordData
import team.comit.simtong.domain.user.exception.UserExceptions
import team.comit.simtong.domain.user.model.Authority
import team.comit.simtong.domain.user.model.User
import team.comit.simtong.domain.user.spi.CommandUserPort
import team.comit.simtong.domain.user.spi.QueryUserPort
import team.comit.simtong.domain.user.spi.UserSecurityPort
import team.comit.simtong.global.annotation.SimtongTest
import java.util.UUID

@SimtongTest
class ChangePasswordUseCaseTests {

    @MockBean
    private lateinit var queryUserPort: QueryUserPort

    @MockBean
    private lateinit var userSecurityPort: UserSecurityPort

    @MockBean
    private lateinit var commandUserPort: CommandUserPort

    private lateinit var changePasswordUseCase: ChangePasswordUseCase

    private val id = UUID.randomUUID()

    private val userStub: User by lazy {
        User(
            id = id,
            nickname = "test nickname",
            name = "test name",
            email = "test@test.com",
            password = "test password",
            employeeNumber = 1234567890,
            authority = Authority.ROLE_COMMON,
            spotId = UUID.randomUUID(),
            teamId = UUID.randomUUID(),
            profileImagePath = "test profile image path"
        )
    }

    private val requestStub: ChangePasswordData by lazy {
        ChangePasswordData(
            password = "test password",
            newPassword = "test new password"
        )
    }

    @BeforeEach
    fun setUp() {
        changePasswordUseCase = ChangePasswordUseCase(
            queryUserPort,
            userSecurityPort,
            commandUserPort
        )
    }

    @Test
    fun `비밀번호 변경 성공`() {
        // given
        given(userSecurityPort.getCurrentUserId())
            .willReturn(id)

        given(queryUserPort.queryUserById(id))
            .willReturn(userStub)

        given(userSecurityPort.compare(requestStub.password, userStub.password))
            .willReturn(true)

        given(userSecurityPort.encode(requestStub.newPassword))
            .willReturn(requestStub.newPassword)

        // when & then
        assertDoesNotThrow {
            changePasswordUseCase.execute(requestStub)
        }
    }

    @Test
    fun `유저 찾기 실패`() {
        // given
        given(userSecurityPort.getCurrentUserId())
            .willReturn(id)

        given(queryUserPort.queryUserById(id))
            .willReturn(null)

        // when & then
        assertThrows<UserExceptions.NotFound> {
            changePasswordUseCase.execute(requestStub)
        }
    }

    @Test
    fun `비밀번호 불일치`() {
        // given
        given(userSecurityPort.getCurrentUserId())
            .willReturn(id)

        given(queryUserPort.queryUserById(id))
            .willReturn(userStub)

        given(userSecurityPort.compare(requestStub.password, userStub.password))
            .willReturn(false)

        // when & then
        assertThrows<UserExceptions.DifferentPassword> {
            changePasswordUseCase.execute(requestStub)
        }
    }

}