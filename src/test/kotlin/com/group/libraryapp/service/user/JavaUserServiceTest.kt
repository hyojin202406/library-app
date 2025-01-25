package com.group.libraryapp.service.user

import com.group.libraryapp.domain.user.JavaUser
import com.group.libraryapp.domain.user.UserRepository
import com.group.libraryapp.dto.user.request.UserCreateRequest
import com.group.libraryapp.dto.user.request.UserUpdateRequest
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class UserServiceTest @Autowired constructor(
    private val userRepository: UserRepository,
    private val userService: UserService,
) {

    @AfterEach
    fun clean() {
        userRepository.deleteAll()
    }


    @Test
    @DisplayName("유저 저장이 정상 동작한다")
    fun saveUserTest() {
        // given
        val request = UserCreateRequest("이름", null)

        // when
        userService.saveUser(request)

        // then
        val results = userRepository.findAll()
        assertThat(results.size).isEqualTo(1)
        assertThat(results[0].name).isEqualTo("이름")
        // 플랫폼 타입 (자바와 코틀린 사용시 주의점)
        // User에 @Nullable 선언
        assertThat(results[0].age).isNull()
    }

    @Test
    @DisplayName("유저 조회가 정상 동작한다")
    fun getUserTest() {
        // given
        userRepository.saveAll(
            listOf(
                JavaUser("A", 20),
                JavaUser("B", null)
            )
        )

        // when
        val results = userService.getUsers()

        // then
        assertThat(results.size).isEqualTo(2)
        assertThat(results).extracting("name").containsExactlyInAnyOrder("A", "B")
    }

    @Test
    @DisplayName("유저 수정이 정상 동작한다")
    fun updateUserNameTest() {
        // given
        val savedUser = userRepository.save(JavaUser("A", null))
        val request = UserUpdateRequest(savedUser.id, "B")

        // when
        userService.updateUserName(request)

        // then
        val result = userRepository.findAll()[0]
        assertThat(result.name).isEqualTo("B")
    }

    @Test
    @DisplayName("유저 삭제가 정상 동작한다")
    fun deleteUserTest() {
        // given
        userRepository.save(JavaUser("A", null))

        // when
        userService.deleteUser("A")

        // then
        assertThat(userRepository.findAll()).hasSize(0)
    }
}