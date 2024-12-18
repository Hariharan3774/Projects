package backend.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import backend.dao.WorkerRepository;
import backend.dao.custom;
import backend.dao.test;
import backend.dao.user;
import backend.entity.UserCustomData;
import backend.entity.Worker;
import backend.entity.addcar;
import backend.entity.customcar;
import backend.entity.testdriveEntity;
import backend.entity.userdata;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller

public class UserController {
	@Autowired
	private user repo;
	
	@Autowired
	private WorkerRepository workerrepo;
	
	@Autowired
	private backend.dao.car carrepo;
	
	@Autowired
	private custom customrepo;
	
	@Autowired
	private test testrepo;
	
	@GetMapping("/signup")
	public String adduser(Model model) {
		model.addAttribute("user", new userdata());
		return "carvilla-v1.0/sign-up";
	}
	
	@GetMapping("/forget")
	public String forget() {
		return "carvilla-v1.0/forgot-password";
	}
	
	@GetMapping("/admin")
	public String admin(Model model) {
		List<Worker> worker = workerrepo.findAll();
		List<userdata> user = repo.findAll();
		model.addAttribute("usersize", user.size());
		List<addcar> car = carrepo.findAll();
		int solded = 0;
		int revenue = 0;
		for(addcar car1: car) {
			solded+=car1.getSold();
			revenue+=(car1.getSold()*car1.getPrice());
		}
		model.addAttribute("revenue", revenue);
		model.addAttribute("solded", solded);
		model.addAttribute("worker", worker);
		return "carvilla-v1.0/Users/admin";
	}
	
	@GetMapping("/user")
	public String user(Model model) {
		List<userdata> user = repo.findAll();
		model.addAttribute("user", user);
		return "carvilla-v1.0/Users/user";
	}
	
	@GetMapping("/details")
	public String details(Model model) {
	    List<userdata> users = repo.findAll();
	    List<UserCustomData> userCustomDataList = new ArrayList<>();

	    for (userdata user1 : users) {
	        customcar custom = customrepo.findByEmail(user1.getEmail());
	        testdriveEntity test = testrepo.findByEmail(user1.getEmail());
	        UserCustomData userCustomData = new UserCustomData(user1.getFname() + " " + user1.getLname(), user1.getEmail(), custom, test);
	        userCustomDataList.add(userCustomData);
	    }

	    model.addAttribute("userCustomDataList", userCustomDataList);
	    return "carvilla-v1.0/Users/details";
	}
	
	@GetMapping("/car")
	public String car(Model model) {
		List<addcar> car = carrepo.findAll();
		model.addAttribute("car", car);
		return "carvilla-v1.0/Users/car";
	}
	
	

	@GetMapping("/addworker")
	public String addworker(Model model) {
		model.addAttribute("worker", new Worker());
		return "carvilla-v1.0/Users/AddWorker";
	}
	
	@PostMapping("/workeradded")
	public String workeradded(Worker work) {
		workerrepo.save(work);
		return "redirect:/admin";
	}
	
	@PostMapping("/updateWorkerPhone")
    @ResponseBody
    public Map<String, Object> updateWorkerPhone(@RequestBody Map<String, Object> payload) {
        Map<String, Object> response = new HashMap<>();
        try {
            int id = (int) payload.get("id");
            Long phone = Long.parseLong((String) payload.get("phone"));
            Worker worker = workerrepo.findById(id);
            System.out.print(worker);
            if (worker != null) {
                worker.setPhone(phone);
                workerrepo.save(worker);
                response.put("success", true);
            } else {
                response.put("success", false);
                response.put("message", "Worker not found");
            }
        } catch (Exception e) {
            response.put("success", false);
            response.put("message", e.getMessage());
        }
        return response;
    }
	
	@PostMapping("/deleteworker")
	public String deleteworker(@RequestParam int id) {
		workerrepo.deleteById(id);
		return "redirect:/admin";
	}
	
	@PostMapping("/useradded")
	public String useradded(userdata user) {
		repo.save(user);
		return "redirect:/loginPage";
	}
	
	@GetMapping("/loginPage")
	public String loginPage() {
		return "carvilla-v1.0/login";
	}
	
	@GetMapping("/logout")
	public String logout(HttpServletResponse response, HttpServletRequest request) {
	    Cookie[] cookies = request.getCookies();
	    if (cookies != null) {
	        for (Cookie cookie : cookies) {
	            if ("username".equals(cookie.getName()) || "password".equals(cookie.getName())) {
	                cookie.setValue(null);
	                cookie.setMaxAge(0);
	                response.addCookie(cookie);
	            }
	        }
	    }
	    return "redirect:/loginPage";
	}
	
	@PostMapping("/authentication")
	public String authentication(@RequestParam String username, @RequestParam String password, HttpServletResponse response, Model model ) {
		userdata user = repo.findByEmail(username);
		if(user!=null && user.getPass().equals(password)) {
			Cookie usernameCookie = new Cookie("username", username);
            Cookie passwordCookie = new Cookie("password", password);
            usernameCookie.setMaxAge(5 * 24 * 60 * 60);
            response.addCookie(usernameCookie);
            response.addCookie(passwordCookie);
			return "redirect:/home?username="+username;
		}
		model.addAttribute("error", "Invalid username or password");
		return "redirect:/loginPage";
	}
}
