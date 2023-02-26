package com.laptrinhjavaweb.controller.admin;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.laptrinhjavaweb.constant.SystemConstant;
import com.laptrinhjavaweb.dto.BuildingDTO;
import com.laptrinhjavaweb.dto.request.SearchDTO;
import com.laptrinhjavaweb.service.IBuildingService;
import com.laptrinhjavaweb.service.IUserService;
import com.laptrinhjavaweb.utils.MessageUtils;

@Controller(value="buildingControllerOfAdmin")
public class BuildingController {

	@Autowired
	private IBuildingService buildingService;
	
	@Autowired
	private IUserService userService;
	
	@Autowired
	private MessageUtils messageUtils;
	
	@RequestMapping(value="/admin/buildinglist",method = RequestMethod.GET)
	public ModelAndView buildingList(@ModelAttribute("modelSearch") SearchDTO searchDTO ) {
		
		BuildingDTO buildingDTO = new BuildingDTO();
		ModelAndView mav = new ModelAndView("admin/building/building_list");
		
		buildingDTO.setListResult(buildingService.findBuilding(searchDTO));

		mav.addObject(SystemConstant.MODEL, buildingDTO);
		mav.addObject("staffmaps", userService.getUserMaps());
		mav.addObject("districtmaps", buildingService.getDistricts());
		mav.addObject("typemaps",buildingService.getBuildingTypes());
		
		return mav;
		
	}
	
	

	@RequestMapping(value = "/admin/buildingedit", method = RequestMethod.GET)
	public ModelAndView editBuilding(@RequestParam(value = "id", required = false) Long id, 
									HttpServletRequest request) { // vì 1 giao dien cho 2 form, id luc co luc ko nen ta de required = false
		
		ModelAndView mav = new ModelAndView("admin/building/building_edit");
		BuildingDTO model = new BuildingDTO();
		
		if(id != null)
		{
			model = buildingService.findById(id);
			
		}
		
		if(request.getParameter("message")!= null)
		{
			Map<String, String> message = messageUtils.getMessage(request.getParameter("message"));		
			// ném lỗi custom ra 
			/*Map<String, String> message  = new HashMap<>();
			message.put("alert", "danger");
			message.put("message", request.getParameter("message"));*/
			
			mav.addObject("message", message.get("message"));
			mav.addObject(SystemConstant.ALERT, message.get("alert"));
		}
		
		mav.addObject(SystemConstant.MODEL, model); // ta có các key của  district và type, sau khi chạy xong thì nó sẽ nhận giá trị với key đã có
		mav.addObject("districtmaps", buildingService.getDistricts());
		mav.addObject("typemaps",buildingService.getBuildingTypes());
		
		
		return mav;
	}
	
	
	@RequestMapping(value = "/admin/buildingdetail", method = RequestMethod.GET)
	public ModelAndView buildingDetail(@RequestParam(value = "id") Long id, 
										HttpServletRequest request) {
		ModelAndView mav = new ModelAndView("admin/building/building_detail");
		BuildingDTO model = new BuildingDTO();
		model = buildingService.findById(id);
		mav.addObject(SystemConstant.MODEL, model); 
		mav.addObject("districtmaps", buildingService.getDistricts());
		mav.addObject("typemaps",buildingService.getBuildingTypes());
		return mav;
		
	}
	
	
	
	
}
