package backend.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.time.format.DateTimeFormatter;
import java.time.LocalDateTime;
import backend.dao.car;
import backend.dao.custom;
import backend.dao.test;
import backend.dao.user;
import backend.entity.userdata;
import backend.entity.addcar;
import backend.entity.customcar;
import backend.entity.testdriveEntity;
@Controller
public class CarController {
	
	@Autowired
	private user repo1;
	
	@Autowired
	private car repo;
	
	@Autowired
	test testrepo;
	
	@Autowired
	custom customrepo;
	
	@GetMapping("/addcar")
	public String addcar(Model model) {
		model.addAttribute("car", new addcar());
		return "carvilla-v1.0/Users/addcar";
	}
	
	@PostMapping("/caradded")
	public String caradded(addcar car) {
		repo.save(car);
		return "redirect:/addcar";
	}
	
	@GetMapping("/bmwPage")
	public String bmwPage(@RequestParam("username") String username, Model model) {
		userdata user = repo1.findByEmail(username);
		model.addAttribute("name", user.getFname());
		model.addAttribute("username", username);
		return "carvilla-v1.0/carspage/bmw";
	}
	
	@GetMapping("/chevroletPage")
	public String  chevroletPage(@RequestParam("username") String username, Model model) {
		userdata user = repo1.findByEmail(username);
		model.addAttribute("name", user.getFname());
		model.addAttribute("username", username);
		return "carvilla-v1.0/carspage/chevrolet";
	} 
	
	@GetMapping("/ferrariPage")
	public String ferrariPage(@RequestParam("username") String username, Model model) {
		userdata user = repo1.findByEmail(username);
		model.addAttribute("name", user.getFname());
		model.addAttribute("username", username);
		return "carvilla-v1.0/carspage/ferrari";
	}
	
	@GetMapping("/testpage")
	public String testpge(@RequestParam("username") String username ,Model model) {
		List<addcar> car = repo.findAll();
		model.addAttribute("username", username);
		model.addAttribute("test", new testdriveEntity());
		model.addAttribute("car", car);
		return "carvilla-v1.0/testdrive";
	}
	
	@GetMapping("/home")
	public String home(@RequestParam("username") String username, Model model) {
		userdata user = repo1.findByEmail(username);
		model.addAttribute("name", user.getFname());
		model.addAttribute("username",username );
		List<addcar> car = repo.findAll();
		customcar custom = customrepo.findByEmail(username);
		testdriveEntity test = testrepo.findByEmail(username);
		model.addAttribute("car", car);
		model.addAttribute("custom", custom);
		model.addAttribute("test", test);
		return"carvilla-v1.0/index";
	}
	
	@GetMapping("/custom")
	public String custom(@RequestParam("carid") int carid, @RequestParam("username") String username, @RequestParam("carmodel") String carmodel, @RequestParam("carname") String carname, @RequestParam("carprice") String carprice,Model model) {
		model.addAttribute("model", carmodel);
		model.addAttribute("username", username);
		model.addAttribute("name", carname);
		model.addAttribute("price", carprice);
		model.addAttribute("carid", carid);
		return "carvilla-v1.0/custom";
	}
	
	
	@PostMapping("/addcustom")
	public String addcustom(@RequestParam("name") String carname,
							@RequestParam("username") String username,
							@RequestParam("model") String carmodel,
							@RequestParam("color") String carcolor,
							@RequestParam("wheel") String carwheel,
							@RequestParam("interior") String carinterior,
							@RequestParam("spoiler") String carspoiler,
							@RequestParam("price") Long carprice,
							@RequestParam("carid") int carid,
							customcar custom,
							addcar car ) {
		
		custom.setName(carname);
		custom.setModel(carmodel);
		custom.setColor(carcolor);
		custom.setWheel(carwheel);
		custom.setInterior(carinterior);
		custom.setSpoiler(carspoiler);
		custom.setPrice(carprice);
		custom.setEmail(username);
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDateTime now = LocalDateTime.now();
		String formattedDate = dtf.format(now);
		custom.setDate(formattedDate);
		customrepo.save(custom);
		addcar car1 = repo.findByCarid(carid);
		car1.setSold(car1.getSold()+1);
		repo.save(car1);
		return "redirect:/home?username="+username;
	}
	
	
	
	
	@PostMapping("/addtest")
	public String addtest(@RequestParam("username") String username,testdriveEntity test) {
		test.setEmail(username);
		testrepo.save(test);
		return "redirect:/testpage";
	}
}
