package backend.Controller;

import java.util.Random;

import backend.dao.user;
import backend.entity.userdata;
import backend.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class EmailController {

    @Autowired
    private EmailService emailService;

    @Autowired
    private user repo;

    private String generatedOtp;
    private String username;

    public static String generateOTP() {
        Random random = new Random();
        int otp = 100000 + random.nextInt(900000);
        return String.valueOf(otp);
    }

    @PostMapping("/sendOTP")
    public String sendEmail(@RequestParam String toemail, Model model) {
        username = toemail;
        userdata user = repo.findByEmail(username);
        if (user == null) {
            model.addAttribute("error", "User not found.");
            return "ForgetPassword";
        }
        else {
        	generatedOtp = generateOTP();
            String otpMessage = "Your OTP is " + generatedOtp;
            emailService.sendSimpleEmail(toemail, "OTP from Car Mafia", otpMessage);
            return "redirect:/forget";
        }
    }

    @PostMapping("/verifyOTP")
    public String verifyOTP(@RequestParam String otp, Model model) {
        if (username == null) {
            model.addAttribute("error", "Username is not set.");
            return "redirect:/forget";
        }

        userdata user = repo.findByEmail(username);
        if (user == null) {
            model.addAttribute("error", "User not found.");
            return "redirect:/forget";
        }

        if (generatedOtp.equals(otp)) {
            String password = user.getPass();
            emailService.sendSimpleEmail(username, "Password from Car Mafia", "Your Password is " + password);
            return "redirect:/loginPage";
        }

        model.addAttribute("error", "OTP is incorrect");
        return "ForgetPassword";
    }
}