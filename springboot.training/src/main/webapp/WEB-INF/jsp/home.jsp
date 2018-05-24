<%@ include file="header.jsp" %>


<c:forEach items="${requestScope.products}" var="p">
	<div class="col-md-3" style="min-height: 400px;">
		<img src="/images/products/${p.picture}"
			class="img img-responsive">
			
		<div style="min-height: 125px;">
			<p class="lead">${p.description}</p>
			<p>${p.quantityPerUnit}
				<br>Rs. ${p.unitPrice}
			</p>
		</div>
		
		<form action="/add-to-cart">
			<input type="hidden" name="product_id" value="${p.id}" />
			<div class="col-md-8">
				<input type="number" name="quantity" value="1" class="form-control">
			</div>
			<div class="col-md-4">
				<button class="btn btn-warning">Add</button>
			</div>
		</form>
	</div>
</c:forEach>


<%@ include file="footer.jsp" %>
