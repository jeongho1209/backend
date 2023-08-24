package com.example.thirdassignment.todo.service

import com.example.thirdassignment.aop.GetCurrentUser
import com.example.thirdassignment.todo.domain.TodoRepository
import com.example.thirdassignment.todo.stub.createTodoRequestStub
import com.example.thirdassignment.user.domain.UserEntity
import com.example.thirdassignment.user.domain.UserRepository
import com.example.thirdassignment.user.exception.UserNotFoundException
import com.example.thirdassignment.user.stub.createUserStub
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.DescribeSpec
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TodoServiceTest : DescribeSpec({
    val todoRepository: TodoRepository = mockk(relaxed = true)
    val userRepository: UserRepository = mockk(relaxed = true)
    val getCurrentUser: GetCurrentUser = mockk(relaxed = true)

    val todoService: TodoService = TodoService(
        todoRepository, userRepository, getCurrentUser
    )

    describe("유저가 할 일을 추가할 때") {
        context("유저의 인증이 실패하면") {
            coEvery { getCurrentUser.getCurrentUser() } returns null
            coEvery {
                withContext(Dispatchers.IO) {
                    userRepository.save(createUserStub())
                }
            }

            it("User Not Found 예외를 던진다") {
                shouldThrow<UserNotFoundException> {
                    todoService.addTodo(createTodoRequestStub())
                }
            }
        }
    }
})