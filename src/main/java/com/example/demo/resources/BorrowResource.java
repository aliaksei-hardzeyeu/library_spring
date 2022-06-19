package com.example.demo.resources;

import com.example.demo.dto.request.RequestUserDto;
import com.example.demo.dto.response.ResponseBookDto;
import com.example.demo.dto.response.ResponseUserDto;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@RequestMapping(value = "/api/")
public interface BorrowResource {

    /**
     * GET api/borrows/ - all
     * GET api/borrows/{user_id} - by user
     * GET api/borrows/{books_id} - by book
     * POST api/borrows - new borrow (user, dates, book, author)GET api/borrows/ - all
     * GET api/borrows/{user_id} - by user
     * GET api/borrows/{books_id} - by book
     * POST api/borrows - new borrow (user, dates, book, author)
     * @return
     */
//    @Operation(description = "Получить список всех заказов", summary = "Получить список всех заказов")
//    List<ResponseBorrowDto> getAllUsersBorrowsList();
//
//    @Operation(description = "Получить список заказов юзера по userId",
//            summary = "Получить список заказов юзера по userId (по конкретному пользователю)")
//    List<ResponseBorrowDto> getBorrowsListByUserId(String userId);
//
//    @Operation(description = "Получить список заказов по bookId", summary = "Получить список заказов по bookId (по конкретной книге)")
//    List<ResponseBorrowDto> getBorrowsListByBookId(String bookId);
//
//    @Operation(description = "Найти список книг пользователя по userId", summary = "Найти список книг пользователя по userId")
//    ResponseBorrowDto upsertBorrow(RequestBorrowDto requestBorrowDto);
}
