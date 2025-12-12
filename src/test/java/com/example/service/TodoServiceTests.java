package com.example.service;

import com.example.w2.dto.TodoDTO;
import com.example.w2.service.TodoService;
import lombok.extern.log4j.Log4j2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

@Log4j2
public class TodoServiceTests {
    private TodoService todoService;

    @BeforeEach
    public void ready()
    {
        todoService = TodoService.INSTANCE;
    }

    @Test
    public void testRegister()throws Exception
    {
        TodoDTO todoDTO = TodoDTO.builder()
                .title("JDBC Test Title")
                .dueDate(LocalDate.now())
                .build();

        todoService.register(todoDTO);
    }

    @Test
    public void testList() throws Exception
    {
        List<TodoDTO> list = todoService.listAll();
        list.forEach(dto -> System.out.println(dto));
    }

    @Test
    public void testGet() throws Exception
    {
        Long tno = 2L;

        TodoDTO dto = todoService.get(tno);

        log.info(dto);
    }

    @Test
    public void testModify() throws Exception
    {
        TodoDTO todoDTO = TodoDTO.builder()
                .tno(2L)
                .title("Modify Test")
                .dueDate(LocalDate.now())
                .build();

        todoService.modify(todoDTO);
    }

    @Test
    public void testRemove() throws Exception
    {
        Long tno = 3L;

        int cnt = todoService.remove(tno);

        log.info(tno + "remove cnt ->> " + cnt);
    }
}
