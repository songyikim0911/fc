<%--
  Created by IntelliJ IDEA.
  User: ksi64
  Date: 2021-08-19
  Time: 오전 11:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


<div>${boardDTO}</div>
<div>${pageDTO}</div>

<a href="/board/list.do?page=${pageDTO.page}&size=${pageDTO.size}">목록가기</a>
<form action = "/board/remove.do?bno=${boardDTO.bno}" method ="post">
    <button> 삭제 </button>
</form>



<form action="/board/modify.do" method="get">
    <div>
        <input type="hidden" name="bno" value=${boardDTO.bno}>
        <button type="submit">UPDATE</button>
    </div>
</form>

</body>
</html>
