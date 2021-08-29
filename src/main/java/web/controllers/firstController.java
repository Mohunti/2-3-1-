package web.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.models.User;
import web.service.UserService;

@Controller
@RequestMapping("/users")
public class firstController {

    private final UserService userService;

    public firstController(UserService userService) {
        this.userService = userService;
        userService.add(new User(1, "Anton", "Shirshov", 24));
        userService.add(new User(2, "Oleg", "Olegov", 25));
        userService.add(new User(3, "Vadim", "Vadimov", 26));
        userService.add(new User(4, "Vlad", "Vladov", 27));
    }

    @GetMapping()
    public String printUsers(Model model) {
        model.addAttribute("users", userService.listUsers());
        return "users/index";
    }

    @GetMapping("/{id}")
    public String printUser(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.get(id));
        return "users/info";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        userService.add(user);
        return "redirect:/users";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute("user") User user) {
        return "users/new";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") int id, Model model) {
        model.addAttribute("user", userService.get(id));
        return "users/edit";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("user") User user, @PathVariable("id") int id) {
        userService.update(id, user);
        return "redirect:/users";
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") int id) {
        userService.delete(id);
        return "redirect:/users";
    }
}