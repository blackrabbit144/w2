
<%--
  Created by IntelliJ IDEA.
  User: it
  Date: 2025-12-12
  Time: 오후 2:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%--//これは JSTL（JSP 標準タグライブラリ） の if 文です--%>
    <c:if test="${param.result == 'error'}">
        <h1>ログインエラー</h1>
    </c:if>

<%--ログイン情報を /login に POST するフォーム--%>
<form action="/login" method="post">
    <input type="text" name="mid"> <%--ユーザーID（mid = member id）--%>
    <input type="text" name="mpw"> <%--パスワード（mpw = member password）--%>
    <input type="checkbox" name="auto"><%--自動ログイン機能 ON/OFF--%>
    <button>LOGIN</button>
</form>
</body>
</html>

<%--
全体の流れ（サーバーとの連携）
1. ユーザーが ログインページにアクセスすると
→ LoginController の doGet() によってこの JSP が表示される

2. ここでフォームに入力して LOGIN を押す
→ /login に POST される
→ LoginController の doPost()が実行される

3. もしログイン失敗したら
→ /login?result=error にリダイレクト
→ <c:if> が発動して「ログインエラー」を表示する
--%>