
<%@include file="/common/taglib.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %> 
<c:url var="transactionAPI" value="/api/customer" />
<c:url var="editCustomerURL" value="/admin/customeredit" />
   
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Thêm giao dịch</title>
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
								Thông tin giao dịch
								
							</h1>
						</div><!-- /.page-header -->

						<div class="row">
							<div class="col-xs-12">
								
								
									<c:if test="${not empty message}">
											<div class="alert alert-${alert}">${message}</div>
									</c:if> 
									
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-left " for="form-field-1" > Ghi chú </label>
										<div class="col-sm-9">
											<form:input path="note" class="form-control"/>
										</div>
									</div>
									
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-left" for="form-field-1" > Loại giao dịch </label>
										<div class="col-sm-2" >	
											
											<form:select path="type">
												<form:option value="" label="---Chọn loại giao dịch---"/>
												<form:options items="${transactiontypemaps}"/>							
											</form:select>
										
										</div>
									</div>

			
									<form:hidden path="customerId" id="customerId" method="POST" />
									<div class="form-group">
										<div class="col-md-offset-3 col-md-9">
											
											<button class="btn btn-info" type="button" id="btnAddTransaction">
												<i class="ace-icon  bigger-110"></i>
													Thêm giao dịch
											</button>	
										
											
										</div>
									</div>

								

							</div><!-- /.col -->
						</div><!-- /.row -->
	
						

							
					 </form:form>
					</div><!-- /.page-content -->
				</div>
			</div><!-- /.main-content -->

	<script>
	
		$('#btnAddTransaction').click(function(e) {
			e.preventDefault(); // nhận biết đc ta sẽ submit vào url nào, bắt buộc có
			var data = {};
			
			var formData = $('#formSubmit').serializeArray();
			$.each(formData, function(i, v) { // duyệt tất cả các ptu trong form và lấy giá trị 
                 data["" + v.name + ""] = v.value;
			});
			addTransaction(data);
			
		});

	
		function addTransaction(data) {
			$.ajax({
				url : "${transactionAPI}/transaction?customerId="+${param.customerId}+"",
				type : 'POST',
				contentType : 'application/json',
				data : JSON.stringify(data),
				dataType : 'json',
				success : function(result) { // result là cái model trả ra nè 
					window.location.href = "${editCustomerURL}?id="+result.customerId+"&message=insert_success";
				},
				error : function(error) {
					window.location.href = "${editCustomerURL}?message=error_system";
				}
			});
		}
	
		
	</script>

</body>
</html>