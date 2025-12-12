package com.example.w2.controller;

import lombok.extern.log4j.Log4j2;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet("/login") // /loginにアクセスすると、このサーブレットが実行されます
@Log4j2 //Log4j2 を使えるようにする Lombok アノテーション  log.info(...) が使えます
public class LoginController extends HttpServlet { //HTTPリクエスト(GET/POST) を受け取れるサーブレットになる

    @Override //ユーザーが /login に GET アクセスしたときに、ログイン画面を表示する
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
                    throws ServletException, IOException
    {
        log.info("login get...........");

        req.getRequestDispatcher("/WEB-INF/login.jsp").forward(req, resp);

        ///WEB-INF/ の中にある JSP は 外部から直接アクセスできない ため、必ず サーブレット経由でフォワードして表示します。
    }

    //この doPost() は“ログイン処理”をしている
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
                            throws ServletException, IOException
    //<form action="/login" method="post">これで送られてきたデータを受け取るのが doPost()
    {
        log.info("login post...........");

        String mid = req.getParameter("mid");//フォームで入力された mid（ID）、mpw（パスワード） を受け取る
        String mpw = req.getParameter("mpw");

        String str = mid+mpw; //ID とパスワードを ただ文字列で連結しているだけ ※本来はこんな事しない

        HttpSession session = req.getSession(); //セッションを取得または作成する 「このユーザーのログイン状態を保存する場所」を作るイメージ

        session.setAttribute("loginInfo", str); //これでログイン情報（str）をセッションに保存する
        //次のページや次回アクセスでもログインしているか確認できる

        resp.sendRedirect("/todo/list"); //ログイン成功後に /todolist へ移動
    }
}
