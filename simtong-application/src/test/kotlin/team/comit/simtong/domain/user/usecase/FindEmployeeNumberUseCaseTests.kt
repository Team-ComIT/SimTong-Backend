package team.comit.simtong.domain.user.usecase

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit.jupiter.SpringExtension
import team.comit.simtong.domain.user.exception.UserNotFoundException
import team.comit.simtong.domain.user.model.Authority
import team.comit.simtong.domain.user.model.User
import team.comit.simtong.domain.user.spi.QueryUserPort
import team.comit.simtong.domain.user.dto.FindEmployeeNumberRequest
import java.util.*

@ExtendWith(SpringExtension::class)
class FindEmployeeNumberUseCaseTests {

    @MockBean
    private lateinit var queryUserPort: QueryUserPort

    private lateinit var findEmployeeNumberUseCase: FindEmployeeNumberUseCase

    private val name: String = "test name"

    private val email: String = "test email"

    private val spotId: UUID = UUID.randomUUID()

    private val employeeNumber: Int = 1234567891

    private val userStub: User by lazy {
        User(
            nickname = "test nickname",
            name = "test name",
            email = "test email",
            employeeNumber = employeeNumber,
            password = "test password",
            authority = Authority.ROLE_COMMON,
            spotId = UUID.randomUUID(),
            teamId = UUID.randomUUID(),
            profileImagePath = "test path"
        )
    }

    private val requestStub: FindEmployeeNumberRequest by lazy {
        FindEmployeeNumberRequest(
            name = name,
            spotId = spotId,
            email = email
        )
    }

    @BeforeEach
    fun setUp() {
        findEmployeeNumberUseCase = FindEmployeeNumberUseCase(queryUserPort)
    }

    @Test
    fun `사원 번호 찾기`() {
        // given
        given(queryUserPort.queryUserByNameAndSpotAndEmail(name, spotId, email))
            .willReturn(userStub)

        // when & then
        assertEquals(findEmployeeNumberUseCase.execute(requestStub), employeeNumber)
    }

    @Test
    fun `사원 번호 찾기 실패`() {
        // given
        given(queryUserPort.queryUserByNameAndSpotAndEmail(name, spotId, email))
            .willReturn(null)

        // when & then
        assertThrows<UserNotFoundException> {
            findEmployeeNumberUseCase.execute(requestStub)
        }
    }

}