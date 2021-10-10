<%--
  Created by IntelliJ IDEA.
  User: ksi64
  Date: 2021-08-18
  Time: 오후 5:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<script>

    let num = '${param.bno}'

    if(num){
        alert(num)
        window.history.replaceState(null, '', 'board/list');
    }

</script>


<h1>List Page</h1>

<h4>${pageMaker}</h4>

<!--input태그 : 사용자가 입력하기 위해 쓰는 태그 -->
<!--hidden 태그 : 화면에서 보이지는 않으나, form으로 submit을 할 때 같이 정보를 넘길 수 있음.-->
<form action="/board/list.do" method="get">
    <input type ="hidden" name="page" value="1">
    <select name="size">
        <option value="10" ${pageMaker.size ==10?"selected":""}>10</option> <!--selected 체크되어있는상태,
        단, 20/50/100를 체크했다면, 이후에도 그게 체크되는 기능 필요-->
        <option value="20" ${pageMaker.size ==20?"selected":""}>20</option>
        <option value="50" ${pageMaker.size ==50?"selected":""}>50</option>
        <option value="100" ${pageMaker.size ==100?"selected":""}>100</option>
    </select>
    <button type ="submit">적용</button>
</form>


<u1>
    <c:forEach items = "${dtoList}" var="dto">
    <li>
        <div>
            <div>${dto.bno}</div>
            <div><a href="/board/read.do?bno=${dto.bno}&page=${pageMaker.page}&size=${pageMaker.size}">${dto.title}</a></div>
            <div>${dto.viewcount}</div>
        </div>
            </li>
    </c:forEach>
</u1>

<style>
    .pageList {
        list-style : none;
        display: flex;
        flex-direction:row;
    }
    .pageList li{
        margin-left: 0.3em;
        background-color:green;
        font-family: "Roboto Light";
        border: 1px solid greenyellow;
    }
    .current {
        font-size: large;
    }

</style>

<ul class="pageList">

    <c:if test="${pageMaker.prev}">
        <li><a href="/board/list.do?page=${pageMaker.start -1}&size=${pageMaker.size}">PREV</a></li>
    </c:if>

    <c:forEach begin="${pageMaker.start}" end="${pageMaker.end}" var="page">
        <li class="${page == pageMaker.page ? "curent":""}"><a href="/board/list.do?page=${page}&size=${pageMaker.size}">${page}</a></li>
    <!--current - 현재 페이지 인 값에 강조하는 3항 연산자.page가 pageMaker와 같다면 유효 하는것으로.-->
    </c:forEach>

    <c:if test="${pageMaker.next}">
        <li><a href="/board/list.do?page=${pageMaker.end+1}&size=${pageMaker.size}">NEXT</a></li>
    </c:if>


</ul>


</body>
</html>
