<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="${pageContext.request.contextPath}/member/join" method="post">
<p>
	이메일 주소:<br/><input type="text" name="email" value="${param.email }">
	<c:if test="${errors.email }">이메일주소를 입력하세요</c:if>
	<c:if test="${errors.duplicateId }">이미 사용중인 아이디 입니다.</c:if>
</p>
<p>
	이름:<br/><input type="text" name="name" value="${param.name }">
	<c:if test="${errors.name }">이름을 입력하세요</c:if>
</p>
<p>
	비밀번호:<br/><input type="password" name="passwd">
	<c:if test="${errors.passwd }">비밀번호를 입력하세요</c:if>
</p>
<p>
	비밀번호:<br/><input type="password" name="confirmpasswd" >
	<c:if test="${errors.confirmpasswd }">비밀번호를 입력하세요</c:if>
	<c:if test="${errors.notMatch}">암호와 확인이 일치하지 않습니다.</c:if>
</p>

<input type="submit" value="가입">
	<a href="${pageContext.request.contextPath}/member/main">[메인으로]</a>
</form>
</body>
</html>