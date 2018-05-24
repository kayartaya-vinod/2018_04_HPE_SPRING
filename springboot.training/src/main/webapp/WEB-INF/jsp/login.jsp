<%@ include file="./header.jsp"%>
<div class="row">
	<h3 class="text-center">Existing users login from here:</h3>
	<br> <br>
	<div class="col-md-2"></div>
	<div class="col-md-8">
		<c:if test="${param.logout!=null}">
			<p class="alert alert-success">You have been logged out</p>
		</c:if>
		<c:if test="${param.error!=null}">
			<p class="alert alert-danger">There was an error, please try again</p>
		</c:if>

		<form name="form" action="/login" method="POST">
			<input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
				
			<div class="form-group">
				<label class="control-label" for="mobile">Email id</label> <input
					type="text" class="form-control" name="username">
			</div>

			<div class="form-group">
				<label class="control-label" for="mobile">Password</label> <input
					type="password" class="form-control" name="password">
			</div>

			<div>
				<button id="btnLogin" class="btn btn-primary">Login</button>
				<a class="lead pull-right" href="/register">New users, register
					here</a>
			</div>
		</form>
	</div>
	<div class="col-md-2"></div>
</div>
<br>
<br>
<br>
<%@ include file="./footer.jsp"%>