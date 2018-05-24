<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>MyBasket - Shopping cart app</title>
	<meta name="viewport" content="width=device-width" >
	<link rel="stylesheet" type="text/css" href="webjars/bootstrap/3.3.7/css/bootstrap.css" >
	<script type="text/javascript" src="webjars/jquery/3.3.1/jquery.js" ></script>
	
</head>
<body>

	<div class="container">
	
		<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	
		<h1 class="alert alert-info">Product List</h1>
		<hr />
		<p class="lead">There are ${requestScope.count} products</p>
		
		<table class="table table-striped table-bordered table-hover table-condensed table-responsive">
			<thead>
				<tr>
					<th>Id</th>
					<th>Product description</th>
					<th>Quantity per unit</th>
					<th>Unit price</th>
					<th>Discount</th>
					<th>Category</th>
					<th>Brand</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${requestScope.products}" var="p">
				<tr>
					<td>${p.id}</td>
					<td>
					${p.description}</td>
					<td>${p.quantityPerUnit}</td>
					<td>${p.unitPrice}</td>
					<td>${p.discount==0 ? "Nil": p.discount}</td>
					<td>${p.category.name}</td>
					<td>${p.brand.name}</td>
				</tr>
				</c:forEach>
			</tbody>
			<tfoot>
				<tr>
					<td colspan="7">
						<a href="?page=${param.page==null || param.page==0? 0: param.page-1}" 
							class="btn btn-primary">Prev</a>
						
						<a href="?page=${param.page==null? 1 : (param.page>=Math.floorDiv(count, 10) ? param.page: param.page+1)  }" 
							class="btn btn-primary pull-right">Next</a>
					</td>
				</tr>
			</tfoot>
		</table>
	</div>

</body>
</html>











