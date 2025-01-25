package com.group.libraryapp.domain.user.loanhistory

import com.group.libraryapp.domain.user.JavaUser
import javax.persistence.*

@Entity
class UserLoanHistory(
    @ManyToOne
    val user: JavaUser,

    val bookName: String,

    var inReturn: Boolean,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
) {
    // JPA 기본 생성자
    protected constructor() : this(
        user = JavaUser("", null), // 임의의 기본값 설정 (더미 데이터)
        bookName = "",
        inReturn = false
    )


    fun doReturn() {
        this.inReturn = true
    }

}