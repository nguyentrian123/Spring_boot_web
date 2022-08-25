<%@include file="/common/taglib.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:url var="buildingAPI" value="/api/building" />
<c:url var="listBuildingURL" value="/admin/buildinglist" />
<c:url var="editBuildingURL" value="/admin/buildingedit" />

<html>
<head>
    <title>Title</title>
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


						<div class="page-header">
							<h1>
								Thông tin Tòa nhà
								
							</h1>
						</div><!-- /.page-header -->

						<div class="row">
							<div class="col-xs-12">
								
								<c:if test="${not empty message}">
									<div class="alert alert-${alert}">${message}</div>
								</c:if> 
								
								<form:form  modelAttribute="model" role="form"  id="formSubmit" method="get" class="form-horizontal">
								
									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-left " for="form-field-1" > Tên sản phẩm </label>
										<div class="col-sm-9">
											<form:input path="name" class="form-control"/>
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-left" for="form-field-1" > Quận </label>
										<div class="col-sm-2" >	
											
											<form:select path="districtCode">
												<form:option value="" label="---Chọn quận---"/>
												<form:options items="${districtmaps}"/>							
											</form:select>
										
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-left" for="form-field-1" > Phường </label>
										<div class="col-sm-9">
											<form:input path="ward" class="form-control"/>
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-left" for="form-field-1" > Đường </label>
										<div class="col-sm-9">
											<form:input path="street" class="form-control"/>
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-left" for="form-field-1" > Kết cấu </label>
										<div class="col-sm-9">
											<form:input path="structure" class="form-control"/>
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-left" for="form-field-1" > Số tầng hầm </label>
										<div class="col-sm-9">
											<form:input path="numberOfBasement" class="form-control"/>
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-left" for="form-field-1" > Diện tích sàn </label>
										<div class="col-sm-9">
											<form:input path="floorArea" class="form-control"/>
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-left" for="form-field-1" > Hướng </label>
										<div class="col-sm-9">
											<form:input path="direction" class="form-control"/>
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-left" for="form-field-1" > Hạng </label>
										<div class="col-sm-9">
											<form:input path="level" class="form-control"/>
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-left" for="form-field-1" > Diện tích thuê </label>
										<div class="col-sm-9">
											<form:input path="rentArea" class="form-control"/>
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-left" for="form-field-1" > Mô tả diện tích </label>
										<div class="col-sm-9">
											<form:textarea path="" class="form-control" rows="3" required="required"></form:textarea>
											
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-left" for="form-field-1" > Giá thuê </label>
										<div class="col-sm-9">
											<form:input path="rentPrice" class="form-control"/>
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-left" for="form-field-1" > Mô tả giá </label>
										<div class="col-sm-9">
											
											<form:textarea path="rentPriceDescription" class="form-control" rows="3" required="required"></form:textarea>
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-left" for="form-field-1" > Phí dịch vụ </label>
										<div class="col-sm-9">
											<form:input path="serviceFee" class="form-control"/>
											
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-left" for="form-field-1" > Phí ô tô </label>
										<div class="col-sm-9">
											<form:input path="carFee" class="form-control"/>
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-left" for="form-field-1" > Phí mô tô </label>
										<div class="col-sm-9">
											<form:input path="motorbikeFee" class="form-control"/>
											
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-left" for="form-field-1" > Phí ngoài giờ </label>
										<div class="col-sm-9">
											<form:input path="overtimeFee" class="form-control"/>
											
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-left" for="form-field-1" > Tiền điện</label>
										<div class="col-sm-9">
											<form:input path="electricityFee" class="form-control"/>
										
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-left" for="form-field-1" > Đặt cọc </label>
										<div class="col-sm-9">
											<form:input path="deposit" class="form-control"/>
											
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-left" for="form-field-1" > Thanh toán </label>
										<div class="col-sm-9">
											<form:input path="payment" class="form-control"/>
											
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-left" for="form-field-1" > Thời hạn thuê </label>
										<div class="col-sm-9">
											<form:input path="renttime" class="form-control"/>
											
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-left" for="form-field-1" >Thời gian trang trí </label>
										<div class="col-sm-9">
											<form:input path="decorationTime" class="form-control"/>
											
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-left" for="form-field-1" >Tên quản lý </label>
										<div class="col-sm-9">
											<form:input path="managerName" class="form-control"/>
											
										</div>
									</div>

									<div class="form-group">		
										<label class="col-sm-3 control-label no-padding-left" for="form-field-1" >Số điện thoại quản lý </label>
										<div class="col-sm-9">
											<form:input path="managerPhone" class="form-control"/>
											
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-left" for="form-field-1" >Phí môi giới </label>
										<div class="col-sm-9">
											<form:input path="brokerageFee" class="form-control"/>
											
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-left" for="form-field-1" >Loại sản phẩm </label>
										<div class="col-sm-9">
											
											<c:forEach var="item" items="${typemaps}">
												<form:checkbox path="type" value="${item.key}"/>  
												<label for="vehicle1"> ${item.value}</label><br>	
											</c:forEach>

										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-left" for="form-field-1" >Ghi chú </label>
										<div class="col-sm-9">
											<form:textarea path="note" class="form-control" rows="3" required="required"></form:textarea>
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-left" for="form-field-1" >Link sản phẩm</label>
										<div class="col-sm-9">
											<form:input path="linkOfBuilding" class="form-control"/>
											
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-left" for="form-field-1" >Bản đồ </label>
										<div class="col-sm-9">
											<form:input path="map" class="form-control"/>
											<input type="text" id="form-field-1" class="form-control" />
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-left" for="form-field-1" >Hình đại diện </label>
										<div class="col-sm-9">
											<input type="file" id="form-field-1" class="form-control" />
										</div>
									</div>

									<form:hidden path="id" id="buildingId" method="POST" />
									<div class="clearfix form-actions">
										<div class="col-md-offset-3 col-md-9">
											<c:if test="${empty model.id}">
												<button class="btn btn-info" type="button" id="btnAddOrUpdateBuilding">
													<i class="ace-icon  bigger-110"></i>
													Thêm tòa nhà
												</button>
											</c:if>
											<c:if test="${not empty model.id }">
												<button class="btn btn-info" type="button" id="btnAddOrUpdateBuilding">
													<i class="ace-icon  bigger-110"></i>
													Chỉnh sửa
												</button>
											</c:if>
											

										
											<button class="btn btn-info" type="button">
												<i class="ace-icon  bigger-110"></i>
												Hủy
											</button>
										</div>
									</div>

								</form:form>	
							


							
								
							</div><!-- /.col -->
						</div><!-- /.row -->
					</div><!-- /.page-content -->
				</div>
			</div><!-- /.main-content -->
			
	<script>
	
		$('#btnAddOrUpdateBuilding').click(function(e) {
			e.preventDefault(); // nhận biết đc ta sẽ submit vào url nào, bắt buộc có
			var data = {};
			var types = [];
			
			var formData = $('#formSubmit').serializeArray();
			$.each(formData, function(i, v) { // duyệt tất cả các ptu trong form và lấy giá trị 
				if(v.name == 'type')
				{
                    types.push(v.value);
				}
				else
				{
                    data["" + v.name + ""] = v.value;
				}

			});
            data['type'] = types;
			var id = $('#buildingId').val();
			
			if (id == "") {
				addBuilding(data);
			} else {
				updateBuilding(data,id );
			}
		});

	
	
		function addBuilding(data) {
			$.ajax({
				url : '${buildingAPI}',
				type : 'POST',
				contentType : 'application/json',
				data : JSON.stringify(data),
				dataType : 'json',
				success : function(result) { // result là cái model trả ra nè 
					window.location.href = "${editBuildingURL}?id="+result.id+"&message=insert_success";
				},
				error : function(error) {
					window.location.href = "${editBuildingURL}?message=error_system";
				}
			});
		}
	
		function updateBuilding(data,id) {
			$.ajax({
				url : '${buildingAPI}',
				type : 'PUT',
				contentType : 'application/json',
				data : JSON.stringify(data),
				dataType : 'json',
				success : function(result) { // result là cái model trả ra nè 
					window.location.href = "${editBuildingURL}?id="+result.id+"&message=update_success";
				},
				error : function(error) {
					window.location.href = "${editBuildingURL}?id="+id+"&message=error_system";
				}
			});
		}
	</script>

</body>
</html>
