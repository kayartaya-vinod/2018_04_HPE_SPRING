<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta name="viewport" content="width=device-width;initial-scale=1;min-scale=1;max-scale=1" >
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>MyBasket</title>

<style>
	.err {
		color: red;
		font-size: .8em;
	}
</style>

<link rel="stylesheet" type="text/css" href="/webjars/bootstrap/3.3.7/css/bootstrap.min.css">

</head>
<body>
	<div class="container">
		<div>
			<h1>MyBasket</h1>
			<form action="">
				<input type="search" name="keyword" class="form-control" placeholder="search...">
			</form>
			<br>
		</div>
		<div class="row">
			<div class="col-md-3">
			
				<a href="/home" class="btn btn-primary btn-block">All Prodcuts</a>
				<a href="/view-cart" class="btn btn-success btn-block">View cart</a>
			
				<br />
			
				<p class="lead text-center">Brands</p>
				<ul class="list-group">
					<c:forEach items="${requestScope.brands}" var="b">
					<a href="/home?filter-by=brand&id=${b.id}" class="list-group-item">${b.name}</a>
					</c:forEach>
				</ul>
				
				<p class="lead text-center">Categories</p>
				<ul class="list-group">
					<c:forEach items="${requestScope.categories}" var="c">
					<a href="/home?filter-by=category&id=${c.id}" class="list-group-item">${c.name}</a>
					</c:forEach>
				</ul>
			</div>
			<div class="col-md-9">