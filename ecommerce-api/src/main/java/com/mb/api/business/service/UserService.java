package com.mb.api.business.service;

import java.util.Map;
import com.mb.api.web.model.LoginModel;
import com.mb.api.web.model.RegisterModel;
import com.mb.api.web.model.UpdateUserModel;

public interface UserService {
	
	String registerNewUser(RegisterModel registerModel);
	String registerNewAdmin(RegisterModel registerModel);
	Map<String, Object> login(LoginModel loginModel);
	
	String updateUser(UpdateUserModel updateUserModel);
	
}
