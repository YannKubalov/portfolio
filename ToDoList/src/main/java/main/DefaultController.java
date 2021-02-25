package main;

import main.model.Task;
import main.model.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


import java.util.ArrayList;


@Controller
public class DefaultController {
    @Autowired
    TaskService taskService;

    @RequestMapping("/")
    public String index(Model model) {
        ArrayList<Task> tasks = taskService.getAllTasks();
        model.addAttribute("tasks", tasks);
        model.addAttribute("tasksCount", tasks.size());
        return "index";
    }
}
