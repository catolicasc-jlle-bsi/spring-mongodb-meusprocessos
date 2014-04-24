<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<html lang="pt_BR">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">

<title>Meus Processos</title>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet"
	href="<c:url value="/resources/css/bootstrap.min.css" />">

<!-- Optional theme -->
<link rel="stylesheet"
	href="<c:url value="/resources/css/bootstrap-theme.min.css" />">

<!-- Latest compiled and minified JavaScript -->
<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>


<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

<style>
body {
	padding-top: 40px;
	padding-bottom: 40px;
	background-color: #eee;
}

.form-signin {
	max-width: 330px;
	padding: 15px;
	margin: 0 auto;
}

.form-signin .form-signin-heading,.form-signin .checkbox {
	margin-bottom: 10px;
}

.form-signin .checkbox {
	font-weight: normal;
}

.form-signin .form-control {
	position: relative;
	height: auto;
	-webkit-box-sizing: border-box;
	-moz-box-sizing: border-box;
	box-sizing: border-box;
	padding: 10px;
	font-size: 16px;
}

.form-signin .form-control:focus {
	z-index: 2;
}

.form-signin input[type="email"] {
	margin-bottom: -1px;
	border-bottom-right-radius: 0;
	border-bottom-left-radius: 0;
}

.form-signin input[type="password"] {
	margin-bottom: 10px;
	border-top-left-radius: 0;
	border-top-right-radius: 0;
}
</style>

</head>

<body>
	<div class="navbar navbar-inverse navbar-fixed-top" role="navigation">
		<div class="container">
			<div class="navbar-header">
				<button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
					<span class="sr-only">Toggle navigation</span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
					<span class="icon-bar"></span>
				</button>
				<a class="navbar-brand" href="#">Meus Processos</a>
			</div>
			<div class="collapse navbar-collapse">
				<ul class="nav navbar-nav">
					<li class="active"><a href="${pageContext.request.contextPath}">Inicio</a></li>
					<li><a href="${pageContext.request.contextPath}/login/novo">Cadastre-se</a></li>
					<li><a href="#comofunciona">Como funciona</a></li>
				</ul>
			</div>
		</div>
	</div>

	<div class="container">
		<div class="signing-form" style="margin-top: 60px;">
			<c:if test="${not empty error}">
				<div class="alert alert-danger">Ocorreu um erro no seu acesso, tente novamente.<br /> Causa: ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}</div>			
			</c:if>
			<c:if test="${not empty param.succMsg}">
				<div class="alert alert-success">${param.succMsg}</div>
			</c:if>
			<c:if test="${not empty errorMsg}">
				<div class="alert alert-danger">${errorMsg}</div>
			</c:if>
			
			<form class="form-signin" role="form" action="<c:url value='j_spring_security_check' />" method='POST'>
				<h2 class="form-signin-heading"></h2>
				<input type="email" class="form-control" id="username" name="j_username" placeholder="E-mail" type="text" required autofocus>
				<input class="form-control" id="password" name="j_password" placeholder="Senha" type="password" required>
				<input class="btn btn-primary form-control" name="commit" type="submit" value="Entrar">
			</form>
		</div>
	</div>

</body>
</html>