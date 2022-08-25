<%@include file="/common/taglib.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:url var="customerAPI" value="/api/customer" />
<c:url var="editCustomerURL" value="/admin/customeredit" />
<!DOCTYPE html>
<html>
<head>

<title>Chỉnh sửa</title>
</head>
<body>
	
	<div class="main-content">
				<div class="main-content-inner">
					<div class="breadcrumbs" id="breadcrumbs">
						<script type="text/javascript">
							try{ace.settings.check('breadcrumbs' , 'fixed')}catch(e){}
						</script>

						<ul class="breadcrumb">
							<li>
								<i class="ace-icon fa fa-home home-icon"></i>
								<a href="#">Home</a>
							</li>

							<li>
								<a href="#">Forms</a>
							</li>
							<li class="active">Form Elements</li>
						</ul><!-- /.breadcrumb -->

					</div>

					<div class="page-content">
					 <form:form  modelAttribute="model" role="form"  id="formSubmit" method="get" class="form-horizontal">

						<div class="page-header">
							<h1>
								Thông tin khách hàng
								
							</h1>
						</div><!-- /.page-header -->

						<div class="row">
							<div class="col-xs-12">
								
								
									<c:if test="${not empty message}">
											<div class="alert alert-${alert}">${message}</div>
									</c:if> 
									
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-left " for="form-field-1" > Tên đầy đủ </label>
										<div class="col-sm-9">
											<form:input path="fullName" class="form-control"/>
										</div>
									</div>

							

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-left" for="form-field-1" > Số điện thoại </label>
										<div class="col-sm-9">
											<form:input path="phone" class="form-control"/>
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-left" for="form-field-1" > Email </label>
										<div class="col-sm-9">
											<form:input path="email" class="form-control"/>
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-left" for="form-field-1" > Tên công ty </label>
										<div class="col-sm-9">
											<form:input path="companyName" class="form-control"/>
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-left" for="form-field-1" > Nhu cầu </label>
										<div class="col-sm-9">
											<form:input path="need" class="form-control"/>
										</div>
									</div>


									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-left" for="form-field-1" > Ghi chú </label>
										<div class="col-sm-9">
											<form:textarea path="note" class="form-control" rows="3" ></form:textarea>
										</div>
									</div>
									
									<form:hidden path="id" id="customerId" method="POST" />
									<div class="form-group">
										<div class="col-md-offset-3 col-md-9">
											<c:if test="${empty model.id}">
												<button class="btn btn-info" type="button" id="btnAddOrUpdateCustomer">
													<i class="ace-icon  bigger-110"></i>
													Thêm khách hàng
												</button>	
											</c:if>
											
											<c:if test="${not empty model.id}">
												<button class="btn btn-info" type="button" id="btnAddOrUpdateCustomer">
													<i class="ace-icon  bigger-110"></i>
													Cập nhập khách hàng
												</button>	
											</c:if>		
										</div>
									</div>

								

							</div><!-- /.col -->
						</div><!-- /.row -->
	
						
						
						<c:forEach var="item" items="${transactiontypemaps}">
						
							<div class="page-header">
								<c:if test="${not empty param.id }">
									<h1>${item.value}</h1>
									<c:url var="addTransactionURL" value="/admin/transaction">
											<c:param name="customerId" value="${param.id}"/>	
											<c:param name="type" value="${item.key}"/>														
									</c:url>
									<a href="${addTransactionURL}">
										<button class="btn btn-white btn-info btn-bold" type="button" data-toggle="tooltip" title="Thêm giao dịch">
										<i class="fa fa-plus-circle"  aria-hidden="true"></i>				
									</button>
									</a>
								</c:if>
								<c:if test="${empty param.id }">
									<h1>${item.value}</h1>
								</c:if>	
							</div>
							
							<div class="row" >
									<div class="col-xs-12">
	
										<div>
											<table id="transactionList" class="table table-striped table-bordered table-hover">
												<thead>
													<tr>
														<th>Ngày</th>
														<th>Ghi chú</th>
															
													</tr>
												</thead>
												
												
												<tbody>
													 <c:forEach var="test" items="${transactions}"> 
													 	<c:if test="${item.key == test.type}">
													 		<tr>
																<td>${test.createdDate}</td>
																<td>${test.note}</td>
															</tr>
													 	</c:if>
														
													 </c:forEach> 
												</tbody> 
												
												
											</table>
	
										</div>
									</div>
							</div>
							
						</c:forEach>
							
					 </form:form>
					</div><!-- /.page-content -->
				</div>
			</div><!-- /.main-content -->
		
		<script>
	
		$('#btnAddOrUpdateCustomer').click(function(e) {
			e.preventDefault(); // nhận biết đc ta sẽ submit vào url nào, bắt buộc có
			var data = {};
			
			var formData = $('#formSubmit').serializeArray();
			$.each(formData, function(i, v) { // duyệt tất cả các ptu trong form và lấy giá trị 
                 data["" + v.name + ""] = v.value;
			});
			var id = $('#customerId').val();
			
			if (id == "") {
				addBuilding(data);
			} else {
				updateBuilding(data,id );
			}
		});

	
	
		function addBuilding(data) {
			$.ajax({
				url : '${customerAPI}',
				type : 'POST',
				contentType : 'application/json',
				data : JSON.stringify(data),
				dataType : 'json',
				success : function(result) { // result là cái model trả ra nè 
					window.location.href = "${editCustomerURL}?id="+result.id+"&message=insert_success";
				},
				error : function(error) {
					window.location.href = "${editCustomerURL}?message=error_system";
				}
			});
		}
	
		function updateBuilding(data,id) {
			$.ajax({
				url : '${customerAPI}',
				type : 'PUT',
				contentType : 'application/json',
				data : JSON.stringify(data),
				dataType : 'json',
				success : function(result) { // result là cái model trả ra nè 
					window.location.href = "${editCustomerURL}?id="+result.id+"&message=update_success";
				},
				error : function(error) {
					window.location.href = "${editCustomerURL}?id="+id+"&message=error_system";
				}
			});
		}
	</script>
		
			
			
</body>
</html>