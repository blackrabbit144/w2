package com.example.w2.controller;


import com.example.w2.dto.TodoDTO;
import com.example.w2.service.TodoService;
import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "todoRegisterController", value = "/todo/register")//GET/POST の処理を行うサーブレット
@Log4j2
public class TodoRegisterController extends HttpServlet {
    private TodoService todoService = TodoService.INSTANCE;
    private final DateTimeFormatter DATEFORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException
    {
        log.info("/todo/register GET .......");

        HttpSession session = req.getSession();

        //新しいセッションかどうか確認
        if(session.isNew())
        {
            log.info("JSESSIONID クッキーが新しく作られたユーザー");
            resp.sendRedirect("/login");
            return;
        } //新しいセッション（つまりログインしていない）場合 → /login へリダイレクト

        //セッションに loginInfo が存在するか確認
        if(session.getAttribute("loginInfo") == null)
        {
            log.info("ログインした情報がないユーザー");
            resp.sendRedirect("/login");
            return;
        }//ログイン済みでない場合も /login へリダイレクト
        //777777777712111777245252
        //ログイン済みなら JSP へフォワード
        req.getRequestDispatcher("/WEB-INF/todo/register.jsp").forward(req, resp);
    }

    //doPost() の処理（フォーム送信後）
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
    {
        //フォームデータを受け取って TodoDTO を作成
        TodoDTO todoDTO = TodoDTO.builder()
                .title(request.getParameter("title"))
                .dueDate(LocalDate.parse(request.getParameter("dueDate"),DATEFORMATTER))
                .build();
        log.info("/todo/register POST .......");
        log.info(todoDTO);
        try {
            //TodoService.INSTANCE で登録処理（DB 保存）を実行
            todoService.register(todoDTO);
        } catch (Exception e) {
            e.printStackTrace();
        }//登録後 /todolist へ移動
        response.sendRedirect("/todo/list");
        //画面をリロードして二重登録を防止するために リダイレクト
    }
}
