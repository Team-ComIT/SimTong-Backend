package team.comit.simtong.domain.user.usecase

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit.jupiter.SpringExtension
import team.comit.simtong.domain.user.model.Authority
import team.comit.simtong.domain.user.model.User
import team.comit.simtong.domain.user.spi.QueryUserPort
import team.comit.simtong.domain.user.usecase.dto.FindEmployeeNumberRequest

@ExtendWith(SpringExtension::class)
class FindEmployeeNumberUseCaseTests {

    @MockBean
    private lateinit var queryUserPort: QueryUserPort

    private lateinit var findEmployeeNumberUseCase: FindEmployeeNumberUseCase

    private val name: String = "test name"

    private val email: String = "test email"

    private val spot: String = "test spot"

    private val employeeNumber: Int = 1234567891

    private val userStub: User by lazy {
        User(
            nickname = "test nickname",
            name = "test name",
            email = "test email",
            employeeNumber = employeeNumber,
            password = "test password",
            authority = Authority.ROLE_COMMON,
            profileImagePath = "test path"
        )
    }

    private val requestStub: FindEmployeeNumberRequest by lazy {
        FindEmployeeNumberRequest(
            name = name,
            spot = spot,
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
        given(queryUserPort.queryUserByNameAndSpotAndEmail(name, spot, email))
            .willReturn(userStub)

        // when & then
        assertEquals(findEmployeeNumberUseCase.execute(requestStub), employeeNumber)
    }

}