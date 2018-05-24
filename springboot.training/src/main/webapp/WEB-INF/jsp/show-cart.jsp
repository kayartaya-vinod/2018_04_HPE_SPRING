<%@ include file="header.jsp" %>

<c:choose>
	<c:when test="${lineitems==null}">
	<h2 class="alert alert-danger text-center">Your cart is empty!</h2>
	</c:when>
	
	<c:otherwise>
	
	<p>
		<a href="/checkout" class="btn btn-success">Checkout</a>
	</p>
	<table class="table table-striped table-hover table-bordered table-condensed">
		<thead>
			<tr>
				<th>Sl no</th>
				<th>Product description</th>
				<th>Unit price</th>
				<th>Quantity</th>
				<th>Total</th>
				<th>Action</th>
			</tr>
		</thead>
		
		<tbody>
			<c:forEach items="${requestScope.lineitems}" var="li" varStatus="status">
				<tr>
					<td>${status.index+1}</td>
					<td>${li.product.description}</td>
					<td>${li.unitPrice}</td>
					<td>${li.quantity}</td>
					<td>${li.unitPrice * li.quantity}</td>
					<td>
						<a href="/remove-from-cart?id=${li.product.id}">
						<span class="glyphicon glyphicon-trash"></span>
						</a>
					</td>	
				</tr>
			</c:forEach>
		</tbody>
	</table>
	<p>
		<a href="/checkout" class="btn btn-success">Checkout</a>
	</p>
	</c:otherwise>
</c:choose>

<%@ include file="footer.jsp" %>