package com.group.libraryapp.dto.book

import com.group.libraryapp.domain.book.BookType

class BookStatResponse(
    val type: BookType,
    val count: Int
)