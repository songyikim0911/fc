<%--
  Created by IntelliJ IDEA.
  User: ksi64
  Date: 2021-08-19
  Time: 오후 12:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


    <form action ="/board/register.do" method="post">
        <input type="text" name="title" value="샘플제목">
        <textarea name="content"> 샘플 내용</textarea>
        <input type="text" name="writer" value="샘플 글쓴이">
        <button type ="submit">등록</button>
    </form>

</body>
</html>
