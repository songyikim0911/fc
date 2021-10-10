<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form action="/board/modify.do" method="post">
    <input type="hidden" name="bno" value=${boardDTO.bno}>
    <input type="text" name="title" value="제목">
    <input type="text" name="content" value="내용">
    <button type="submit">UPDATE</button>
</form>

</body>
</html>