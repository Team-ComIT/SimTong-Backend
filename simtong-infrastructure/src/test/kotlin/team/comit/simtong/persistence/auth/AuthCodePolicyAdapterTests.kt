package team.comit.simtong.persistence.auth

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.BDDMockito.given
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.test.context.junit.jupiter.SpringExtension
import team.comit.simtong.domain.auth.exception.UncertifiedEmailException
import team.comit.simtong.persistence.auth.entity.AuthCodePolicyEntity
import team.comit.simtong.persistence.auth.repository.AuthCodePolicyRepository

@ExtendWith(SpringExtension::class)
class AuthCodePolicyAdapterTests {

    @MockBean
    private lateinit var authCodePolicyRepository: AuthCodePolicyRepository

    private lateinit var authCodePolicyAdapter: AuthCodePolicyAdapter

    private val email: String = "test@test.com"

    private val authCodePolicyEntityStub: AuthCodePolicyEntity by lazy {
        AuthCodePolicyEntity(
            key = email,
            expirationTime = 123456789
        )
    }

    @BeforeEach
    fun setUp() {
        authCodePolicyAdapter = AuthCodePolicyAdapter(authCodePolicyRepository)
    }

    @Test
    fun `정책 인증 확인`() {
        // given
        given(authCodePolicyRepository.queryAuthCodePolicyEntityByKey(email))
            .willReturn(authCodePolicyEntityStub)

        // when & then
        assertFalse(authCodePolicyAdapter.queryAuthCodePolicyByEmail(email))
    }

    @Test
    fun `정책 인증 미확인`() {
        // given
        given(authCodePolicyRepository.queryAuthCodePolicyEntityByKey(email))
            .willReturn(null)

        assertThrows<UncertifiedEmailException> {
            authCodePolicyAdapter.queryAuthCodePolicyByEmail(email)
        }
    }
}