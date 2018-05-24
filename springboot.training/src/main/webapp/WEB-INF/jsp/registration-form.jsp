<%@ include file="header.jsp"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<div class="row">
	<div class="col-sm-2"></div>
	<div class="col-sm-8">
		<div class="row">
			<p class="lead text-center">New customers please register here</p>
			<hr />
			<form:form modelAttribute="customer" cssClass="form-horizontal">
				<div class="form-group">
					<label class="col-sm-3 control-label">Name</label>
					<div class="col-sm-7">
						<form:input path="name" cssClass="form-control" />
						<form:errors path="name" cssClass="err" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">Email</label>
					<div class="col-sm-7">
						<form:input type="email" path="email" cssClass="form-control" />
						<form:errors path="email" cssClass="err" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">Phone number</label>
					<div class="col-sm-7">
						<form:input path="phone" cssClass="form-control" />
						<form:errors path="phone" cssClass="err" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">Password</label>
					<div class="col-sm-7">
						<form:password path="password" cssClass="form-control" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">Address</label>
					<div class="col-sm-7">
						<form:input path="address" cssClass="form-control" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">City</label>
					<div class="col-sm-7">
						<form:input path="city" cssClass="form-control" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">State</label>
					<div class="col-sm-7">
						<form:input path="state" cssClass="form-control" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label">Country</label>
					<div class="col-sm-7">
						<form:input path="country" cssClass="form-control" />
					</div>
				</div>
				<div class="form-group">
					<label class="col-sm-3 control-label"></label>
					<div class="col-sm-7">
						<button class="btn btn-primary">Save</button>
					</div>
				</div>

			</form:form>

		</div>
	</div>
	<div class="col-sm-2"></div>
</div>


<%@ include file="footer.jsp"%>