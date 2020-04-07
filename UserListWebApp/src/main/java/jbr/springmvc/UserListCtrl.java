package jbr.springmvc;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import jbr.springmvc.bean.UserObj;
import jbr.springmvc.dao.UserDAO;

@Controller
public class UserListCtrl {
	
	@Autowired    
    UserDAO dao;
	
	@GetMapping(value = "/")
	public String home(Model m) {
        List<UserObj> list=dao.getUsers();    
        m.addAttribute("list",list);  
		return "index";
	}

	@GetMapping(value = "/addEditUser")
	@ResponseBody
    public void addEditUser(@RequestParam String id, 
    		@RequestParam String name, 
    		@RequestParam String email, 
    		@RequestParam String mobile) {
		UserObj userObj = new UserObj();
		if(name!=null && !name.trim().isEmpty()) userObj.setNAME(name);
		if(email!=null && !email.trim().isEmpty()) userObj.setEMAIL(email);
		if(mobile!=null && !mobile.trim().isEmpty()) userObj.setMOBILE(mobile);
		if(id!=null && !id.trim().isEmpty()) {
			userObj.setID(id);
			dao.update(userObj);
		} else {
			dao.save(userObj);
		}
    }

	@GetMapping(value = "/deleteUser")
    @ResponseBody
    public String deleteUser(@RequestParam String userId) {
		dao.delete(Integer.parseInt(userId));
		return "SUCCESS";
    }

}
