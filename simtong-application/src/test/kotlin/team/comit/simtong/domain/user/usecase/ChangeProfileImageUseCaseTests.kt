package team.comit.simtong.domain.user.usecase

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit.jupiter.SpringExtension
import team.comit.simtong.domain.file.exception.NotFoundFilePathException
import team.comit.simtong.domain.file.spi.IdentifyFilePort
import team.comit.simtong.domain.user.dto.ChangeProfileImageRequest
import team.comit.simtong.domain.user.exception.UserNotFoundException
import team.comit.simtong.domain.user.model.Authority
import team.comit.simtong.domain.user.model.User
import team.comit.simtong.domain.user.spi.CommandUserPort
import team.comit.simtong.domain.user.spi.QueryUserPort
import team.comit.simtong.domain.user.spi.UserSecurityPort
import java.util.UUID

@ExtendWith(SpringExtension::class)
class ChangeProfileImageUseCaseTests {

    @MockBean
    private lateinit var queryUserPort: QueryUserPort

    @MockBean
    private lateinit var userSecurityPort: UserSecurityPort

    @MockBean
    private lateinit var commandUserPort: CommandUserPort

    @MockBean
    private lateinit var identifyFilePort: IdentifyFilePort

    private lateinit var changeProfileImageUseCase: ChangeProfileImageUseCase

    private val id = UUID.randomUUID()

    private val requestStub: ChangeProfileImageRequest by lazy {
        ChangeProfileImageRequest(
            profileImagePath = "test path"
        )
    }

    private val userStub: User by lazy {
        User(
            id = id,
            nickname = "test nickname",
            name = "test name",
            email = "test email",
            password = "test password",
            employeeNumber = 1234567890,
            authority = Authority.ROLE_COMMON,
            spotId = id,
            teamId = id,
            profileImagePath = "test path"
        )
    }

    @BeforeEach
    fun setUp() {
        changeProfileImageUseCase = ChangeProfileImageUseCase(
            queryUserPort,
            userSecurityPort,
            commandUserPort,
            identifyFilePort
        )
    }

    @Test
    fun `프로필 사진 변경 성공`() {
        // given
        given(identifyFilePort.existsPath(requestStub.profileImagePath))
            .willReturn(true)

        given(userSecurityPort.getCurrentUserId())
            .willReturn(id)

        given(queryUserPort.queryUserById(id))
            .willReturn(userStub)

        // when & then
        assertDoesNotThrow {
            changeProfileImageUseCase.execute(requestStub)
        }
    }

    @Test
    fun `알려지지 않은 사진 경로`() {
        // given
        given(identifyFilePort.existsPath(requestStub.profileImagePath))
            .willReturn(false)

        // when & then
        assertThrows<NotFoundFilePathException> {
            changeProfileImageUseCase.execute(requestStub)
        }
    }

    @Test
    fun `유저를 찾을 수 없음`() {
        // given
        given(identifyFilePort.existsPath(requestStub.profileImagePath))
            .willReturn(true)

        given(userSecurityPort.getCurrentUserId())
            .willReturn(id)

        given(queryUserPort.queryUserById(id))
            .willReturn(null)

        // when & then
        assertThrows<UserNotFoundException> {
            changeProfileImageUseCase.execute(requestStub)
        }
    }

}