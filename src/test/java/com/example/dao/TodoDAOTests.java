package com.example.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.example.w2.dao.TodoDAO;
import com.example.w2.domain.TodoVO;

import java.time.LocalDate;
import java.util.List;


public class TodoDAOTests {

    private TodoDAO todoDAO;

    @BeforeEach
    public void ready() {
        todoDAO = new TodoDAO();
    }

    @Test
    public void testTime() throws Exception {

        System.out.println(todoDAO.getTime());
    }

    @Test
    public void testTime2() throws Exception {

        System.out.println("time2 -> " + todoDAO.getTime2() );
    }

    @Test
    public void testInsert() throws Exception {
        TodoVO todoVO = TodoVO.builder()
                .title("Sample Title...")
                .dueDate(LocalDate.of(2021,12,31))
                .build();

        int cnt = todoDAO.insert(todoVO);

        System.out.println("insert success count : " + cnt);
    }

    @Test
    public void testList() throws Exception {

        List<TodoVO> list = todoDAO.selectAll();

        list.forEach(vo -> System.out.println(vo));
    }

    @Test
    public void testSelectOne() throws Exception {

        Long tno = 1L;

        TodoVO vo = todoDAO.selectOne(tno);

        System.out.println(vo);
    }

    @Test
    public void testDelete() throws Exception {

        Long tno = 1L;

        int cnt = todoDAO.delecteOne(tno);

        System.out.println("success count: " + cnt );
    }

    @Test
    public void testUpdateOne() throws Exception {
        TodoVO todoVO = TodoVO.builder()
                .tno(2L)
                .title("sample Title...")
                .dueDate(LocalDate.of(2021,12,31))
                .finished(true)
                .build();

        int cnt = todoDAO.updateOne(todoVO);

        System.out.println("update success count: " + cnt );
    }
}
