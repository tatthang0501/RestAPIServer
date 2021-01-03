package practice.web;

import javax.servlet.ServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import practice.data.MotorbikeRepository;
import practice.data.StudentRepository;

@Controller
@RequestMapping("/motorbike")
public class MotorbikeController {
    private MotorbikeRepository motoRepo;
    private StudentRepository stuRepo;

    MotorbikeController(MotorbikeRepository motoRepo, StudentRepository stuRepo){
        this.motoRepo = motoRepo;
        this.stuRepo = stuRepo;
    }

    @GetMapping
    public String showMotorbikeManager(Model model, ServletRequest request){
        model.addAttribute("page", "Quản lý xe");
        return "motorbike.html";
    }

    @GetMapping("/findMotorbike")
    public String showFindMotorbike(Model model, ServletRequest request){
        model.addAttribute("page", "Tìm xe");
        return "findMotorbike";
    }
}
