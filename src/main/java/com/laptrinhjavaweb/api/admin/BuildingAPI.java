package com.laptrinhjavaweb.api.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.laptrinhjavaweb.dto.request.BuildingAssignmentRequest;
import com.laptrinhjavaweb.dto.BuildingEdit;
import com.laptrinhjavaweb.dto.response.ResponseDTO;
import com.laptrinhjavaweb.service.IBuildingService;
import com.laptrinhjavaweb.service.IUserService;

@RestController(value="buildingAPIOfAdmin")
@RequestMapping("/api/building")
public class BuildingAPI {
	
	@Autowired
	private IBuildingService buildingService;
	
	@Autowired
	private IUserService userService;
	
	@PostMapping
	public BuildingEdit createBuilding(@RequestBody BuildingEdit buildingEdit)
	{
		return buildingService.saveBuilding(buildingEdit);
		
	}
	
	@PutMapping
	public BuildingEdit updateBuilding(@RequestBody BuildingEdit buildingEdit)
	{
		
		return buildingService.saveBuilding(buildingEdit);
	}
	
	
	//@GetMapping("/{buildingid}/staffs") // kh nên sdung cái này vì kh biết id của thằng nào
	@GetMapping("/staffs") // dont nest resource  ,TH này ta vừa findbyId vừa FindAll được luôn, nhưng vì là web nên ta đưa findAll sang controller
	public ResponseDTO loadStaff( @RequestParam(value = "buildingid", required = false) long id)
	{
		ResponseDTO result = new ResponseDTO();
		result.setData(userService.findStaffAssgins(id));
		result.setMessage("success");
		return result;
	}
	
	@PostMapping("/assign")
	public ResponseDTO assginBuilding(@RequestBody BuildingAssignmentRequest buildingAssignmentRequest)
	{
		ResponseDTO result = new ResponseDTO();
		buildingService.assignmentBuilding(buildingAssignmentRequest);
		result.setMessage("success");
		return result;
	}
	
	@DeleteMapping
	public void deleteBuilding(@RequestBody Long[] ids)
	{
		buildingService.deleteBuilding(ids);
	}
	
	
	
}
