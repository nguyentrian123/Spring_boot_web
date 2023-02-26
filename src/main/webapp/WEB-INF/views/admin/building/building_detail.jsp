<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="/common/taglib.jsp"%>
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Chi tiết tòa nhà</title>
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
										<label class="col-sm-3 control-label no-padding-left " for="form-field-1"  > Tên sản phẩm </label>
										<div class="col-sm-9">
											<form:input path="name" class="form-control" disabled="true" />
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
										<label class="col-sm-3 control-label no-padding-left" for="form-field-1"  disabled="true" > Phường </label>
										<div class="col-sm-9">
											<form:input path="ward" class="form-control"/>
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-left" for="form-field-1" > Đường </label>
										<div class="col-sm-9">
											<form:input path="street" class="form-control"  disabled="true" />
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-left" for="form-field-1" > Kết cấu </label>
										<div class="col-sm-9">
											<form:input path="structure" class="form-control"  disabled="true" />
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-left" for="form-field-1" > Số tầng hầm </label>
										<div class="col-sm-9">
											<form:input path="numberOfBasement" class="form-control"  disabled="true" />
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-left" for="form-field-1" > Diện tích sàn </label>
										<div class="col-sm-9">
											<form:input path="floorArea" class="form-control"  disabled="true" />
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-left" for="form-field-1" > Hướng </label>
										<div class="col-sm-9">
											<form:input path="direction" class="form-control"  disabled="true" />
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-left" for="form-field-1" > Hạng </label>
										<div class="col-sm-9">
											<form:input path="level" class="form-control"  disabled="true" />
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-left" for="form-field-1" > Diện tích thuê </label>
										<div class="col-sm-9">
											<form:input path="rentArea" class="form-control"  disabled="true" />
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-left" for="form-field-1" > Mô tả diện tích </label>
										<div class="col-sm-9">
											<form:textarea path="" class="form-control" rows="3" required="required"  disabled="true" ></form:textarea>
											
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-left" for="form-field-1" > Giá thuê </label>
										<div class="col-sm-9">
											<form:input path="rentPrice" class="form-control"  disabled="true" />
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-left" for="form-field-1" > Mô tả giá </label>
										<div class="col-sm-9">
											
											<form:textarea path="rentPriceDescription" class="form-control" rows="3" required="required"  disabled="true" ></form:textarea>
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-left" for="form-field-1" > Phí dịch vụ </label>
										<div class="col-sm-9">
											<form:input path="serviceFee" class="form-control"  disabled="true" />
											
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-left" for="form-field-1" > Phí ô tô </label>
										<div class="col-sm-9">
											<form:input path="carFee" class="form-control"  disabled="true" />
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-left" for="form-field-1" > Phí mô tô </label>
										<div class="col-sm-9">
											<form:input path="motorbikeFee" class="form-control"  disabled="true" />
											
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-left" for="form-field-1" > Phí ngoài giờ </label>
										<div class="col-sm-9">
											<form:input path="overtimeFee" class="form-control"  disabled="true" />
											
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-left" for="form-field-1" > Tiền điện</label>
										<div class="col-sm-9">
											<form:input path="electricityFee" class="form-control"  disabled="true" />
										
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-left" for="form-field-1" > Đặt cọc </label>
										<div class="col-sm-9">
											<form:input path="deposit" class="form-control"  disabled="true" />
											
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-left" for="form-field-1" > Thanh toán </label>
										<div class="col-sm-9">
											<form:input path="payment" class="form-control"  disabled="true" />
											
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-left" for="form-field-1" > Thời hạn thuê </label>
										<div class="col-sm-9">
											<form:input path="renttime" class="form-control"  disabled="true" />
											
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-left" for="form-field-1" >Thời gian trang trí </label>
										<div class="col-sm-9">
											<form:input path="decorationTime" class="form-control"  disabled="true" />
											
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-left" for="form-field-1" >Tên quản lý </label>
										<div class="col-sm-9">
											<form:input path="managerName" class="form-control"  disabled="true" />
											
										</div>
									</div>

									<div class="form-group">		
										<label class="col-sm-3 control-label no-padding-left" for="form-field-1" >Số điện thoại quản lý </label>
										<div class="col-sm-9">
											<form:input path="managerPhone" class="form-control"  disabled="true" />
											
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-left" for="form-field-1" >Phí môi giới </label>
										<div class="col-sm-9">
											<form:input path="brokerageFee" class="form-control"  disabled="true" />
											
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
											<form:textarea path="note" class="form-control" rows="3" required="required"  disabled="true"  ></form:textarea>
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-left" for="form-field-1" >Link sản phẩm</label>
										<div class="col-sm-9">
											<form:input path="linkOfBuilding" class="form-control"  disabled="true" />
											
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-left" for="form-field-1" >Bản đồ </label>
										<div class="col-sm-9">
											<form:input path="map" class="form-control"  disabled="true" />
											
										</div>
									</div>

									<div class="form-group">
										<label class="col-sm-3 control-label no-padding-left" for="form-field-1" >Hình đại diện </label>
										<div class="col-sm-9">
											<input type="file" id="form-field-1" class="form-control" />
										</div>
									</div>
								
								

								</form:form>	
							


							
								
							</div><!-- /.col -->
						</div><!-- /.row -->
					</div><!-- /.page-content -->
				</div>
			</div><!-- /.main-content -->

</body>
</html>