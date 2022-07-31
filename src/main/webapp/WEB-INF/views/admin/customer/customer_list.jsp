<%@include file="/common/taglib.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<title>Quản lý khách hàng</title>
</head>
<body>

<div class="main-content">
	<div class="main-content-inner">
					
			<form:form  modelAttribute="modelSearch" id="formSubmit" method="get">		
					<div class="page-content">
						<div class="row">
							<div class="col-xs-12">
								
								<c:if test="${not empty message}">
									<div class="alert alert-${alert}">
			  							${message}
									</div>
								</c:if>
								
								
								<div class="widget-box">
									<div class="widget-header">
										<h4 class="widget-title">Tìm Kiếm</h4>
										
										<div class="widget-toolbar">
											<a href="#" data-action="collapse">
												<i class="ace-icon fa fa-chevron-up"></i>
											</a>
										</div>

										<div class="widget-body">
											<div class="widget-main">
												<div class="form-horizontal">

													<div class="form-group">
														<div class="col-sm-4">
															<div>
																<label for="name" style="color: black;">Tên khách hàng</label>
																<form:input path="fullName" class="form-control"/>
															</div>
														</div>

														<div class="col-sm-4">
															<div>
																<label for="phone" style="color: black;">Di động </label>
																<form:input path="phone" type="number" class="form-control"/>
															</div>
														</div>
														
															<div class="col-sm-4">
																<div>
																	<label for="email" style="color: black;">Email </label>
																	<form:input path="email" class="form-control"/>
																</div>
															</div>

													</div>

													
													<div class="form-group">
	
															<div class="col-sm-2">
																<div>
																	<label for="name" style="color: black;">Nhân viên phụ trách</label>												
																	<form:select path="staff"  class="form-control">
																		<form:option value="" label="---Chọn nhân viên quản lý---"/>
																		<form:options items="${staffmaps}"/>	
																	</form:select>
																</div>
															</div>
															
															<div class="col-sm-2 pull-right">
																<button id="searchCustomer" type="submit" class="btn btn-success btn-md dropdown-toggle">
																	Tìm kiếm
																	<i class="ace-icon fa fa-arrow-right icon-on-right"></i>
																</button>
															</div>
															
													</div>
														

												</div>

												
											</div>
										</div>


									</div>


								</div><!-- /.end  -->
							</div><!-- /.col -->
						</div><!-- /.row -->
								
								<!--hết-->
								<div class="row" style="margin-right: 0px;">
									<div class="pull-right">
										<button class="btn btn-white btn-info btn-bold" data-toggle="tooltip" title="Thêm khách hàng">
											<i class="fa fa-plus-circle"  aria-hidden="true"></i>
											
										</button>
										<button class="btn btn-white btn-info btn-bold" data-toggle="tooltip" title="Xóa khách hàng">
											<i class="fa fa-trash"  aria-hidden="true"></i>
											
										</button>
	
									</div>
								</div>
							</br>

								

								<div class="row" >
									<div class="col-xs-12">

										<div>
											<table id="simple-table" class="table table-striped table-bordered table-hover">
												<thead>
													<tr>
														<th class="center">
															<label class="pos-rel">
																<input type="checkbox" class="ace" />
																<span class="lbl"></span>
															</label>
														</th>
														<th>Tên</th>
														<th>Nhân viên quản lý</th>
														<th>Di động</th>
														<th>Email</th>	
														<th>Nhu cầu</th>
														<th >Người nhập</th>
														<th >Ngày nhập</th>
														<th >Tình trạng</th>
														

														<th>Thao tác</th>
													</tr>
												</thead>

												<tbody>
												 <c:forEach var="item" items="${model.listResult}">
												 
													<tr>
														<td class="center">
															<label class="pos-rel">
																<input type="checkbox" class="ace" />
																<span class="lbl"></span>
															</label>
														</td>

														<td>
															${item.fullName}
														</td>
														<td>${item.fullNameStaff}</td>
														<td>${item.phone}</td>
														<td>${item.email}</td>
														<td>$45</td>
														<td>$45</td>
														<td>$45</td>
														
														<td >
															<span>Expiring</span>
														</td>

														<td>
															<div class="hidden-sm hidden-xs action-buttons">
																<button class="btn btn-xs btn-info" data-toggle="tooltip"  type="button"
																title="Giao khách hàng" onclick="assignmentStaff(${item.id})">
																	<i class="fa fa-bars"></i>
																</button>

																<c:url var="updateCustomerURL" value="/admin/customeredit">
																	<c:param name="id" value="${item.id}"/>															
																</c:url>
																<a href='${updateCustomerURL}'>
																	<button class="btn btn-xs btn-info" type="button" data-toggle="tooltip" title="Sửa khách hàng">
																		<i class="ace-icon fa fa-pencil bigger-120"></i>
																	</button>
																</a>
																
																<button class="btn btn-xs btn-info" data-toggle="tooltip" title="Chi tiết khách hàng">
																	<i class="normal-icon ace-icon fa fa-eye white bigger-130"></i>
																</button>
																
															</div>

															<div class="hidden-md hidden-lg">
																<div class="inline pos-rel">
																	<button class="btn btn-minier btn-yellow dropdown-toggle" data-toggle="dropdown" data-position="auto">
																		<i class="ace-icon fa fa-caret-down icon-only bigger-120"></i>
																	</button>

																	<ul class="dropdown-menu dropdown-only-icon dropdown-yellow dropdown-menu-right dropdown-caret dropdown-close">
																		<li>
																			<a href="#" class="tooltip-info" data-rel="tooltip" title="View">
																				<span class="blue">
																					<i class="ace-icon fa fa-search-plus bigger-120"></i>
																				</span>
																			</a>
																		</li>

																		<li>
																			<a href="#" class="tooltip-success" data-rel="tooltip" title="Edit">
																				<span class="green">
																					<i class="ace-icon fa fa-pencil-square-o bigger-120"></i>
																				</span>
																			</a>
																		</li>

																		
																	</ul>
																</div>
															</div>
														</td>
													</tr>
												 </c:forEach>
												 
												</tbody>
											</table>

										</div>
									</div>
								</div>

		
							
					
					</div><!-- /.page-content -->
				</form:form>
					
				</div>
			</div><!-- /.main-content -->

		
	<div id="assignmentBuildingModal" class="modal fade" role="dialog">
			<div class="modal-dialog">
		  
			  <!-- Modal content-->
			  <div class="modal-content">
				<div class="modal-header">
				  <button type="button" class="close" data-dismiss="modal">&times;</button>
				  <h4 class="modal-title">Danh sách nhân viên</h4>
				</div>
				<div class="modal-body">
					<table class="table table-condensed">
						<thead>
						  <tr>
							<th style="text-align: center;">Chọn nhân viên</th>
							<th style="text-align: center;">Tên nhân viên</th>
							
						  </tr>
						</thead>
						<tbody>
						  <tr>
							<td><input type="checkbox" value="1" id="checkbox" /></td>
							<td style="text-align: center;">Nguyễn Văn A</td>
							
						  </tr>
						  <tr>
							<td><input type="checkbox" value="2"  id="checkbox"/></td>
							<td style="text-align: center;">Nguyễn Văn B</td>
						
						  </tr>
						  <tr>
							<td><input type="checkbox" value="3"  id="checkbox"/></td>
							<td style="text-align: center;">Nguyễn Văn C</td>
							
						  </tr>
						</tbody>
					  </table>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" >Giao tòa nhà</button>
				  <button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
				</div>
			  </div>
		  
			</div>
		  </div>

		  <script>
			  function assignmentStaff()
			  {
					openModalAssginmentBuilding();
			  }

			  function openModalAssginmentBuilding(){
					$('#assignmentBuildingModal').modal();
			  }
		  </script>
		  
		  
	
</body>
</html>