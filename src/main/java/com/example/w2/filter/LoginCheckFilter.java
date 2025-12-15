package com.example.w2.filter; //ログインしていないユーザーを /todo 以下に入れさせないための関所
//フィルターって何？ Servlet / Controller の前に割り込んで処理をする仕組み  for ログインチェック

import lombok.extern.log4j.Log4j2;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = {"/todo/*"}) //todo で始まるすべてのURLに対して実行されるフィルター
@Log4j2 // → log.info() が使える
public class LoginCheckFilter implements Filter {
    @Override //doFilter メソッド（超重要）
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) //リクエストが来るたびに毎回呼ばれる
                    throws IOException, ServletException {

        log.info("Login check filter...."); //「あ、フィルター通ったな」と分かるデバッグ用ログ

         //ServletRequest → HttpServletRequest に変換
        HttpServletRequest req = (HttpServletRequest) request; //HttpServletRequest にするとセッション取得、パラメータ取得、URL取得
        HttpServletResponse resp = (HttpServletResponse) response;

        //セッション取得 -> ブラウザごとの ログイン情報入れ物
        HttpSession session = req.getSession();

        //ログインチェックの核心部分
        if(session.getAttribute("loginInfo") == null) //loginInfo は ログイン成功時に保存した情報 それが null ＝ ログインしていない
        {
            resp.sendRedirect("/login"); //なので：login にリダイレクト

            return; //return; で処理終了（＝ controller に行かせない）
        }
        //ログイン済みの場合
        chain.doFilter(request, response); //フィルター → コントローラ → JSPという通常ルートに戻る
    }
}
