package team.comit.simtong.domain.menu.usecase

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit.jupiter.SpringExtension
import team.comit.simtong.domain.menu.model.Menu
import team.comit.simtong.domain.menu.spi.QueryMenuPort
import java.time.LocalDate
import java.util.*

@ExtendWith(SpringExtension::class)
class QueryMenuByMonthUseCaseTests {

    @MockBean
    private lateinit var queryMenuPort: QueryMenuPort

    private lateinit var queryMenuByMonthUseCase: QueryMenuByMonthUseCase

    private val menuStub: Menu by lazy {
        Menu(
            date = LocalDate.of(2022, 9, 1),
            meal = "오늘 아침은 아침밥",
            spotId = UUID.randomUUID()
        )
    }

    private val menuStub2: Menu by lazy {
        Menu(
            date = LocalDate.of(2022, 9, 30),
            meal = "오늘 점심은 점심밥",
            spotId = UUID.randomUUID()
        )
    }

    @BeforeEach
    fun setUp() {
        queryMenuByMonthUseCase = QueryMenuByMonthUseCase(queryMenuPort)
    }

    @Test
    fun `메뉴 조회 성공`() {
        // given
        val spotId = UUID.randomUUID()
        val now = LocalDate.now()

        given(queryMenuPort.queryMenuByMonth(now.year, now.monthValue, spotId))
            .willReturn(
                listOf(menuStub, menuStub2)
            )

        // when
        val response = queryMenuByMonthUseCase.execute(now, spotId)

        // then
        assertThat(response).isNotNull
    }

}