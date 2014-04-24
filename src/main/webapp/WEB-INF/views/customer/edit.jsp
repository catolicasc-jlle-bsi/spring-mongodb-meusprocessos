<!DOCTYPE html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>  
<html lang="pt_BR">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">
    <link rel="shortcut icon" href="../../assets/ico/favicon.ico">

    <title>Admin > Meus Processos</title>

    <!-- Latest compiled and minified CSS -->
	<link rel="stylesheet"
		href="<c:url value="/resources/css/bootstrap.min.css" />">
	
	<!-- Optional theme -->
	<link rel="stylesheet"
		href="<c:url value="/resources/css/bootstrap-theme.min.css" />">
	
	<!-- Latest compiled and minified JavaScript -->
	<script src="<c:url value="/resources/js/jquery.min.js" />"></script>
	<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
	
	
	<!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
	<!--[if lt IE 9]>
      <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
      <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->
    
    <style>
    /*
 * Base structure
 */

/* Move down content because we have a fixed navbar that is 50px tall */
body {
  padding-top: 50px;
}


/*
 * Global add-ons
 */

.sub-header {
  padding-bottom: 10px;
  border-bottom: 1px solid #eee;
}


/*
 * Sidebar
 */

/* Hide for mobile, show later */
.sidebar {
  display: none;
}
@media (min-width: 768px) {
  .sidebar {
    position: fixed;
    top: 51px;
    bottom: 0;
    left: 0;
    z-index: 1000;
    display: block;
    padding: 20px;
    overflow-x: hidden;
    overflow-y: auto; /* Scrollable contents if viewport is shorter than content. */
    background-color: #f5f5f5;
    border-right: 1px solid #eee;
  }
}

/* Sidebar navigation */
.nav-sidebar {
  margin-right: -21px; /* 20px padding + 1px border */
  margin-bottom: 20px;
  margin-left: -20px;
}
.nav-sidebar > li > a {
  padding-right: 20px;
  padding-left: 20px;
}
.nav-sidebar > .active > a {
  color: #fff;
  background-color: #428bca;
}


/*
 * Main content
 */

.main {
  padding: 20px;
}
@media (min-width: 768px) {
  .main {
    padding-right: 40px;
    padding-left: 40px;
  }
}
.main .page-header {
  margin-top: 0;
}


/*
 * Placeholder dashboard ideas
 */

.placeholders {
  margin-bottom: 30px;
  text-align: center;
}
.placeholders h4 {
  margin-bottom: 0;
}
.placeholder {
  margin-bottom: 20px;
}
.placeholder img {
  display: inline-block;
  border-radius: 50%;
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
					<li><a href="${pageContext.request.contextPath}">Inicio</a></li>
				</ul>
				<ul class="nav navbar-nav navbar-right">
					<li><a href="#">${sessionScope.user.name}</a></li>
					<li><a href="<c:url value="/j_spring_security_logout" />">Sair</a></li>
				</ul>
			</div>
		</div>
	</div>
	
    <div class="container-fluid">
      <div class="row">
        <div class="col-sm-3 col-md-2 sidebar">
          <ul class="nav nav-sidebar">
            <li><a href="${pageContext.request.contextPath}/process/">Processo</a></li>
            <li><a href="${pageContext.request.contextPath}/customer/">Cliente</a></li>
            <li><a href="${pageContext.request.contextPath}/employee/">Funcionário</a></li>
          </ul>
        </div>

			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
					
				<h1 class="page-header">Cliente</h1>
					
				<div class="panel panel-default">
					
					<div class="container">
						<div class="col-md-8" style="margin-top: 20px;">			
							<c:if test="${not empty param.errorMsg}">	
								<div class="alert alert-danger">${param.errorMsg}</div>
							</c:if>
							<form action="${pageContext.request.contextPath}/customer/save"
								class="form" method="post" role="form">
								
								<input value="${customer.id}" type="hidden" name="id" />
								
								<div class="row">
									<div class="form-group">
										<label for="name">Nome</label> <input value="${customer.name}" type="text"
											class="form-control" id="name"
											placeholder="Escreva o nome aqui" name="name"
											required="required" size="30">
									</div>
								</div>
								<div class="row">
									<div class="form-group">
										<label for="cnpj">CNPJ/CPF</label> <input value="${customer.cnpj}" type="text"
											class="form-control" id="cnpj"
											placeholder="Escreva o cnpj ou cpf aqui" name="cnpj"
											required="required" size="30">
									</div>
								</div>
								<div class="row">
									<div class="form-group">
										<label for="email">E-mail</label> <input value="${customer.email}" type="email"
											class="form-control" id="email"
											placeholder="Escreva o e-mail aqui" size="30" name="email"
											type="email" required="required">
									</div>
								</div>
								<div class="row">
									<div class="form-group">
										<label for="phone">Fone</label> <input value="${customer.phone}" type="text"
											class="form-control" id="phone"
											placeholder="Escreva o fone aqui" name="phone"
											required="required" size="30">
									</div>
								</div>
								<div class="row">
									<div class="form-group">
										<label for="observation">Observação</label>
										<textarea class="form-control" id="observation"
											name="observation">${customer.observation}</textarea>
									</div>
								</div>
								<div class="row">
									<div style="margin-bottom: 30px;">
										<input class="btn btn-primary" name="commit" type="submit"
											value="Salvar">
									</div>
								</div>
							</form>
						</div>
					</div>
	
				</div>
			</div>
		</div>
    </div>
    
  </body>
</html>
