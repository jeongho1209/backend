package com.example.thirdassignment.user.service

import com.example.thirdassignment.user.domain.UserRepository
import com.example.thirdassignment.user.presentation.dto.request.UserSignUpRequest
import com.example.thirdassignment.user.stub.createUserStub
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import org.springframework.security.crypto.password.PasswordEncoder

internal class UserServiceTest : DescribeSpec({

    val userRepository: UserRepository = mockk(relaxed = true)
    val passwordEncoder: PasswordEncoder = mockk(relaxed = true)

    val userService = UserService(
        userRepository = userRepository,
        passwordEncoder = passwordEncoder,
    )

    describe("유저가 회원가입을 진행할 때") {
        val userStub = createUserStub() // stub 만드는 이유 -> 테스트 하기 전에 검증해야할 객체를 만든다.

        val signUpRequestStub: UserSignUpRequest by lazy { // by lazy 붙이는 이유 -> 사용할 때 초기화 돼서 성능 향상
            UserSignUpRequest(
                accountId = "test accountId",
                password = "test password"
            )
        }

        context("유저의 아이디가 중복되면") {
            coEvery {
                withContext(Dispatchers.IO) {
                    userRepository.existsByAccountId(signUpRequestStub.accountId)
                }
            } returns true

            it("UserExistException 예외를 던진다") {
                shouldThrow<IllegalArgumentException> {
                    userService.signUp(signUpRequestStub)
                }
            }
        }

        context("유저의 정보가 올바르게 주어지면") {
            coEvery {
                withContext(Dispatchers.IO) {
                    userRepository.existsByAccountId(signUpRequestStub.accountId)
                }
            } returns false
            coEvery { passwordEncoder.encode(signUpRequestStub.password) } returns userStub.password
            coEvery {
                withContext(Dispatchers.IO) {
                    userRepository.save(any())
                }
            } returns userStub

            it("유저 정보를 저장한다") {
                userService.signUp(signUpRequestStub)
            }
        }
    }
})
