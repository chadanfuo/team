<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title> 메인</title>
</head>
<body>


<c:if test="${! empty authUser}">
	${authUser.name }님, 안녕하세요.
	<a href="${pageContext.request.contextPath}/member/logout">[로그아웃하기]</a>
</c:if>	
	<c:if test="${empty authUser}">
	<a href="${pageContext.request.contextPath}/member/join">[회원가입하기]</a>
	<a href="${pageContext.request.contextPath}/member/login">[로그인하기]</a>
</c:if>


</body>
</html>