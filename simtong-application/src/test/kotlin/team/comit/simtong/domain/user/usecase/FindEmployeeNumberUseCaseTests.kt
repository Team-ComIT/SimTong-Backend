package team.comit.simtong.domain.user.usecase

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit.jupiter.SpringExtension
import team.comit.simtong.domain.user.model.User
import team.comit.simtong.domain.user.spi.QueryUserPort

@ExtendWith(SpringExtension::class)
class FindEmployeeNumberUseCaseTests {

    @MockBean
    private lateinit var queryUserPort: QueryUserPort

    private lateinit var findEmployeeNumberUseCase: FindEmployeeNumberUseCase

    private val userStub: User by lazy {
        User(
            
        )
    }

    @BeforeEach
    fun setUp() {
        findEmployeeNumberUseCase = FindEmployeeNumberUseCase(queryUserPort)
    }

    @Test
    fun `사원 번호 찾기 성공`() {
        // given
        given(queryUserPort.queryUserByNameAndSpotAndEmail())
    }

}