package practice.web;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.ServletRequest;

import com.fasterxml.jackson.databind.util.Named;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import practice.Student;
import practice.data.StudentRepository;

@RestController
@RequestMapping(path = "/student", produces="application/json")
@CrossOrigin(origins="*")
public class StudentController {
    private StudentRepository stuRepo;

    public StudentController(StudentRepository stuRepo) {
        this.stuRepo = stuRepo;
    }

    @GetMapping
    public String showIndex(Model model) {
        model.addAttribute("page", "Quản lý sinh viên");
        return ("student.html");
    }

    @GetMapping("/addStudent")
    public String showAddStudent(ServletRequest request, Model model) {
        model.addAttribute("add", new Student());
        model.addAttribute("page", "Thêm sinh viên");
        Map<String,String[]> paramMap = request.getParameterMap();
        if(paramMap.containsKey("error")){
            model.addAttribute("msg", "Có lỗi xảy ra");
        }
        if(paramMap.containsKey("formatError")){
            model.addAttribute("msg", "Sai định dạng");
        }
        return "addStudent";
    }

    // @PostMapping("/addStudent")
    // public String addStudentProcess(Model model, ServletRequest request, Student student) {
    //     try {
    //         System.out.println(student.getName());
    //         System.out.println(student.getStudentId());
    //         System.out.println(student.getDOB());
    //         System.out.println(student.getAddress());
    //         stuRepo.save(student);
    //     } catch (Exception e) {
    //         return "redirect:/student/addStudent?error";
    //     }
    //     return "redirect:/student/findStudent?add";
    // }

    @PostMapping(path = "/addStudent", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Student addStudentProcess(@RequestBody Student student) {
        try {
            System.out.println(student.getName());
            System.out.println(student.getStudentId());
            System.out.println(student.getDOB());
            System.out.println(student.getAddress());
            stuRepo.save(student);
        } catch (Exception e) {
            return null;
        }
        return student;
    }

    // @GetMapping("/findStudent")
    // public String showFindStudent(Model model, ServletRequest request) {
    //     model.addAttribute("page", "Tìm sinh viên");
    //     Map<String,String[]> paramMap = request.getParameterMap();
    //     if(paramMap.containsKey("error")){
    //         model.addAttribute("msg", "Có lỗi xảy ra");
    //     }   
    //     if(paramMap.containsKey("formatError")){
    //         model.addAttribute("msg", "Nhập sai định dạng tên");
    //     }
    //     if(paramMap.containsKey("edit")){
    //         model.addAttribute("msg", "Sửa thông tin thành công");
    //     }
    //     if(paramMap.containsKey("delete")){
    //         model.addAttribute("msg", "Xóa sinh viên thành công");
    //     }
    //     if(paramMap.containsKey("add")){
    //         model.addAttribute("msg", "Thêm sinh viên thành công");
    //     }
    //     ArrayList<Student> listStu = (ArrayList<Student>) stuRepo.findAll();
    //     model.addAttribute("students", listStu);
    //     model.addAttribute("student", new Student());
    //     return "findStudent";
    // }

    @GetMapping("/findStudent")
    public Iterable<Student> showFindStudent(Model model, ServletRequest request) {
        Iterable listStu = stuRepo.findAll();
        // model.addAttribute("students", listStu);
        // model.addAttribute("student", new Student());
        return listStu;
    }


    // @PostMapping("/findStudent")
    // public String findStudentProcess(Model model, String name){
    //     boolean valid = false;
    //     System.out.println(name);
    //     if(name != null){
    //         try{
    //             Integer.parseInt(name);
    //         }
    //         catch(NumberFormatException e){
    //             valid = true;
    //         }
    //     }
    //     if(valid==true){
    //         try{
    //             String named = "%"+name+"%";
    //             ArrayList<Student> listStudent = (ArrayList<Student>) stuRepo.findAllByName(named);
    //             System.out.println(listStudent.size());
    //             model.addAttribute("students", listStudent);
    //             model.addAttribute("student", new Student());
    //             model.addAttribute("studentName", name);
    //         }
    //         catch(Exception e){
    //             return "findStudent?error";
    //         }
    //     }
    //     if(valid == false){
    //         return "findStudent?formatError";
    //     }
    //     return "findStudent";
    // }

    @GetMapping("/findStudent/{name}")
    public Iterable<Student> findStudentProcess(Model model, @PathVariable("name") String name){
        boolean valid = false;
        Iterable<Student> listFound;
        if(name != null){
            try{
                Integer.parseInt(name);
            }
            catch(NumberFormatException e){
                valid = true;
            }
        }
        if(valid==true){
            try{
                String named = "%"+name+"%";
                listFound = stuRepo.findAllByName(named);
                return listFound;
            }
            catch(Exception e){
                return null;
            }
        }
        if(valid == false){
            return null;
        }
        return null;
    }

    @GetMapping("/editStudent/{id}")
    public Student showEditStudent(Model model, ServletRequest request, @PathVariable("id") int id){
        try {
            Student s = stuRepo.findById(id).get();
            return s;
            
        } catch (Exception e) {
            return null;
        }
    }

    // @PostMapping("/editStudent")
    // public String editStudentProcess(Model model, ServletRequest request, Student student) {
    //     boolean valid = false;
    //     try {
    //         Integer.parseInt(student.getName());
    //         Integer.parseInt(student.getStudentId());
    //     } catch (NumberFormatException e) {
    //         valid = true;
    //         // return "redirect:/student/findStudent?formatError";
    //     }
    //     if(valid == false){
    //         return "redirect:/student/findStudent?formatError";
    //     }
    //     else if (valid == true) {
    //         Student studentSave = stuRepo.findById(student.getId()).get();
    //         studentSave.setName(student.getName());
    //         studentSave.setStudentId(student.getStudentId());
    //         studentSave.setDOB(student.getDOB());
    //         studentSave.setAddress(student.getAddress());
    //         try {
    //             stuRepo.save(studentSave);
    //         } catch (Exception e) {
    //             return "redirect:/student/findStudent?error";
    //         }
    //     }
        
    //     return "redirect:/student/findStudent?edit";
    // }

    @PostMapping(path = "/editStudent", consumes = "application/json")
    public Student editStudentProcess(Model model, @RequestBody Student student) {
        boolean valid = false;
        try {
            Integer.parseInt(student.getName());
            Integer.parseInt(student.getStudentId());
        } catch (NumberFormatException e) {
            valid = true;
        }
        if(valid == false){
            return null;
        }
        else if (valid == true) {
            Student studentSave = stuRepo.findById(student.getId()).get();
            studentSave.setName(student.getName());
            studentSave.setStudentId(student.getStudentId());
            studentSave.setDOB(student.getDOB());
            studentSave.setAddress(student.getAddress());
            try {
                stuRepo.save(studentSave);
            } catch (Exception e) {
                return null;
            }
        }
        
        return student;
    }

    @DeleteMapping("/deleteStudent/{id}")
    public String deleteStudent(Model model, Student student, @PathVariable("id") int id){
        try{
            System.out.println(id);
            stuRepo.deleteById(id);
        }
        catch(Exception e){
            return "OK";
        }
        return "Failed";
    }

}
