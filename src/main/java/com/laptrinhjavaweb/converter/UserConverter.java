package com.laptrinhjavaweb.converter;

import com.laptrinhjavaweb.dto.UserDTO;
import com.laptrinhjavaweb.dto.response.StaffResponseDTO;
import com.laptrinhjavaweb.entity.UserEntity;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class UserConverter {

    @Autowired
    private ModelMapper modelMapper;

    public UserDTO convertToDto (UserEntity entity){
        UserDTO result = modelMapper.map(entity, UserDTO.class);
        return result;
    }

    public UserEntity convertToEntity (UserDTO dto){
        UserEntity result = modelMapper.map(dto, UserEntity.class);
        return result;
    }
    
    public StaffResponseDTO convertToResponseChecked(UserEntity entity)
    {
    	StaffResponseDTO staff = new StaffResponseDTO();
    	staff.setFullName(entity.getFullName());
    	staff.setId(entity.getId());
    	staff.setChecked("checked");
    	return  staff;
    }
    
    public StaffResponseDTO convertToResponseNotChecked(UserEntity entity)
    {
    	StaffResponseDTO staff = new StaffResponseDTO();
    	staff.setFullName(entity.getFullName());
    	staff.setId(entity.getId());
    	return  staff;
    }
    
}
