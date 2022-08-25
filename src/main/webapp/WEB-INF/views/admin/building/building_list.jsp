<%@include file="/common/taglib.jsp"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<c:url var="searchURL" value="/admin/buildinglist" />
<c:url var="addURL" value="/admin/buildingedit" />
<c:url var="buildingAPI" value="/api/building" />
<c:url var="assignStaff" value="/api/building/assign" />


<html>
<head>
    <title>Tìm kiếm tòa nhà</title>
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
								<a href="#">Tables</a>
							</li>
							<li class="active">Simple &amp; Dynamic</li>
						</ul><!-- /.breadcrumb -->

					
					</div>
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
														<div class="col-sm-6">
															<div>
																<label for="name" style="color: black;">Tên tòa nhà</label>
																<form:input path="name" class="form-control"/>
															</div>
														</div>

														<div class="col-sm-6">
															<div>
																<label for="name" style="color: black;">Diện tích sàn </label>
																<form:input path="floorArea" class="form-control"/>

															</div>
														</div>

													</div>

													
													<div class="form-group">
	
															<div class="col-sm-2">
																<label for="name" style="color: black;">Quận </label>
																<div>
																	
																	<form:select path="district">
																		<form:option value="" label="---Chọn quận---"/>
																		<form:options items="${districtmaps}"/>
																		
																	</form:select>
																	
													
																</div>

															</div>
															<div class="col-sm-2">

															</div>
	
															<div class="col-sm-4">
																<div>
																	<label for="name" style="color: black;">Phường </label>
																	<form:input path="ward" class="form-control"/>
																</div>
															</div>

															<div class="col-sm-4">
																<div>
																	<label for="name" style="color: black;">Đường </label>
																	<form:input path="street" class="form-control"/>

																</div>
															</div>
	
													</div>

													<div class="form-group">
														<div class="col-sm-4">
															<div>
																<label for="name" style="color: black;">Số tầng hầm</label>
																<form:input path="numberOfBasement" class="form-control"/>

															</div>
														</div>

														<div class="col-sm-4">
															<div>
																<label for="name" style="color: black;">Hướng</label>
																<form:input path="direction" class="form-control"/>
															</div>
														</div>

														<div class="col-sm-4">
															<div>
																<label for="name" style="color: black;">Hạng</label>
																<form:input path="level" class="form-control"/>

															</div>
														</div>

													</div>

													<div class="form-group">
														<div class="col-sm-3">
															<div>
																<label for="name" style="color: black;">Diện tích từ</label>
																<form:input path="rentAreaFrom" class="form-control"/>

															</div>
														</div>

														<div class="col-sm-3">
															<div>
																<label for="name" style="color: black;">Diện tích đến</label>
																<form:input path="rentAreaTo" class="form-control"/>

															</div>
														</div>

														<div class="col-sm-3">
															<div>
																<label for="name" style="color: black;">Giá thuê từ</label>
																<form:input path="rentPriceFrom" class="form-control"/>

															</div>
														</div>

														<div class="col-sm-3">
															<div>
																<label for="name" style="color: black;">Giá thuê đến</label>
																<form:input path="rentPriceTo" class="form-control"/>

															</div>
														</div>

													</div>

													<div class="form-group">
	
														

														<div class="col-sm-4">
															<div>
																<label for="name" style="color: black;">Tên quản lý </label>
																<form:input path="managerName" class="form-control"/>

															</div>
														</div>

														<div class="col-sm-4">
															<div>
																<label for="name" style="color: black;">Điện thoại quản lý </label>
																<form:input path="managerPhone" class="form-control"/>

															</div>
														</div>

														<div class="col-sm-4">
															<label for="name" style="color: black;">Nhân viên quản lý </label>
															<div>
																
																 <form:select path="staff">
																	<form:option value="" label="---Chọn nhân viên quản lý---"/>
																	<form:options items="${staffmaps}"/>
																		
																</form:select>
																
															</div>
														</div>

														
													</div>
													
													

													<div class="form-group">
														<c:forEach var="item" items="${typemaps}">
															<div class="col-sm-2">
																<form:checkbox path="renttype"  value="${item.key}"></form:checkbox>
																<label for="vehicle1"> ${item.value}</label><br>
															</div>
														</c:forEach>
													</div>

													<div class="form-group">
														
														<div class="col-sm-1">
															<button id="searchBuilding" type="submit" class="btn btn-success btn-md dropdown-toggle">
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
										<a href="${addURL}">
											<button  type ="button" class="btn btn-white btn-info btn-bold" data-toggle="tooltip" title="Thêm tòa nhà">
												<i class="fa fa-plus-circle"  aria-hidden="true"></i>
											</button>
										</a>
										
										
										<button id="btnDelete" type="button" disabled
                                                    class="dt-button buttons-html5 btn btn-white btn-primary btn-bold"
                                                    data-toggle="tooltip"
                                                    title="Xóa bài viết" onclick="showAlertBeforeDelete()">
															<span>
																<i class="fa fa-trash-o bigger-110 pink"></i>
															</span>
                                        </button>
	
									</div>
								</div>
							</br>

								

								<div class="row" >
									<div class="col-xs-12">

										<div>
											<table id="buildingList" class="table table-striped table-bordered table-hover">
												<thead>
													<tr>
														<th class="center">
															<label class="pos-rel">
																<input type="checkbox" class="ace" />
																<span class="lbl"></span>
															</label>
														</th>
														<th>Ngày</th>
														<th>Tên sản phẩm</th>
														<th >Địa chỉ</th>
														<th> Tên quản lý</th>	
														<th >Số điện thoại</th>
														<th >D.T sàn</th>
														<th >D.T trống</th>
														<th >Giá thuê</th>
														<th >Phí dịch vụ</th>
														<th >Phí MG</th>

														<th>Thao tác</th>
													</tr>
												</thead>

												<tbody>

													<c:forEach var="item" items="${model.listResult}">
														<tr>
															<td class="center">
																<label class="pos-rel">
																	<input type="checkbox" class="ace" value="${item.id}" id="checkbox_${item.id}" />
																	<span class="lbl"></span>
																</label>
															</td>

															<td>
																${item.createdDate}
															</td>
															<td>${item.name}</td>
															<td>${item.address}</td>
															<td>${item.managerName}</td>
															<td>${item.managerPhone }</td>
															<td>${item.floorArea }</td>
															<td>${item.rentArea }</td>
															<td>${item.rentPrice }</td>
															<td >${item.serviceFee}</td>
														
															<td >
																<span>Expiring</span>
															</td>

															<td>
																<div class="hidden-sm hidden-xs action-buttons">
																
																	<button  type ="button" class="btn btn-xs btn-info" data-toggle="tooltip"
																			title="Giao tòa nhà" onclick="assignmentBuilding(${item.id})" >
																		<i class="fa fa-bars"></i>
																	</button>
																	
																	
																	<c:url var="updateBuildingURL" value="/admin/buildingedit">
																	<c:param name="id" value="${item.id}"/>															
																	</c:url>	
																	<a href='${updateBuildingURL}'>
																		 <button type ="button" class="btn btn-xs btn-info" data-toggle="tooltip" title="Sửa tòa nhà">
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
						<table class="table table-condensed" id="staffList">
							<thead>
							  <tr>
								<th style="text-align: center;">Chọn nhân viên</th>
								<th style="text-align: center;">Tên nhân viên</th>
								
							  </tr>
							</thead>
							
							<tbody>					
								<!-- <tr>
							  		<td class="text-center"><input type="checkbox" value='0'  id="checkbox_0" class="check-box-element"  /></td>
							 		<td class="text-center">ABC </td>
							  	</tr> -->	
							</tbody>
							
						  </table>

						  <input type="hidden" id="buildingId" name="buildingId" value=""/>

					</div>
					<div class="modal-footer">
						<button type="button" class="btn btn-default" id="btnAssignBuilding" >Giao tòa nhà</button>
					  	<button type="button" class="btn btn-default" data-dismiss="modal">Đóng</button>
					</div>
				  </div>
			  
				</div>
			</div>
		

		  <script>
			  function assignmentBuilding(buildingId)
			  {
					openModalAssginmentBuilding();
					loadStaff(buildingId);
					$('#buildingId').val(buildingId);
			  }

			  function loadStaff(buildingId) {
				  $.ajax({
					 type: "GET",
					// url: "${loadStaffAPI}/"+buildingId+"/staffs",
					 url: "${buildingAPI}/staffs/?buildingid="+buildingId+" ",
					 dataType: "json",
					  success : function(response) {
						  var row = '';
						  $.each(response.data, function (index,item) {
							  row += '<tr>';
							  row += '<td class="text-center"><input type="checkbox" value='+item.id+'  id="checkbox_'+item.id+'" class="check-box-element" '+item.checked+' /></td>';
							  row += '<td class="text-center">'+item.fullName+' </td>';
							  row += '</tr>';
                          });

						  $('#staffList tbody').html(row); // add html vào cái nơi mà mình chỉ định
                      },
                      error : function(response) {
                          console.log("fail");
                          console.log(response);
                      }

				  });
              }

			  function openModalAssginmentBuilding(){
					$('#assignmentBuildingModal').modal();
			  }

			  $('#btnAssignBuilding').click(function (e) {
					e.preventDefault();
					var data = {}; // khai bao data la chuoi json
					data['buildingId'] = $('#buildingId').val();
	
	                var staffs = $('#staffList').find('tbody input[type=checkbox]:checked').map(function () {
						return $(this).val(); // đứng tại checkbox lấy value của nó ra
	                }).get(); // trả ra 1 mảng staff
	
					data['staffs'] = staffs;
	
					assignStaff(data);
              });

              function assignStaff(data) {
                  $.ajax({
                      url : '${assignStaff}',
                      type : 'POST',
                      contentType : 'application/json',
                      data : JSON.stringify(data),
                      dataType : 'json',
                      success : function(response) {             
                          window.location.href = "${searchURL}";
                       
                      },
                      error : function(response) {
                          console.log("failed");
                          console.log(response);
                      }
                  });
              }
              
            
             
              function showAlertBeforeDelete(callback) {
                  swal({
                      title: "Xác nhận xóa",
                      text: "Bạn có chắc chắn xóa những dòng đã chọn",
                      type: "warning",
                      showCancelButton: true,
                      confirmButtonText: "Xác nhận",
                      cancelButtonText: "Hủy bỏ",
                      confirmButtonClass: "btn btn-success",
                      cancelButtonClass: "btn btn-danger"
                  }).then(function (res) {
                      if(res.value){
                    	 	event.preventDefault();
                    		var ids = $('#buildingList').find(' tbody input[type=checkbox]:checked').map(function () {
          			            return $(this).val();
          			        }).get();
          					deleteBuilding(ids);
          					
                      }else if(res.dismiss == 'cancel'){
                          console.log('cancel');
                      }
                  });
              }
      

      		
      		function deleteBuilding(data) {
      	        $.ajax({
      	            url: '${buildingAPI}',
      	            type: 'DELETE',
      	         	dataType: 'json',
      	            contentType: 'application/json',
      	            data: JSON.stringify(data),
      	            success: function (result) {
      	                window.location.href = "${searchURL}?message=delete_success";
      	            },
      	            error: function (error) {
      	            	window.location.href = "${searchURL}?message=error_system";
      	            }
      	        });
      	    }

      			

		  </script>	
	
</body>
</html>
