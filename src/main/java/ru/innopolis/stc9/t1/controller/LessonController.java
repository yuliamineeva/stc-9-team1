package ru.innopolis.stc9.t1.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.innopolis.stc9.t1.db.dao.GroupDAOImpl;
import ru.innopolis.stc9.t1.pojo.Group;
import ru.innopolis.stc9.t1.pojo.Lesson;
import ru.innopolis.stc9.t1.pojo.User;
import ru.innopolis.stc9.t1.service.GroupService;
import ru.innopolis.stc9.t1.service.LessonService;
import ru.innopolis.stc9.t1.service.UserService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Controller
public class LessonController {
    private final static Logger logger = Logger.getLogger(LessonController.class);
    private LessonService lessonService = new LessonService();
    private GroupService groupService = new GroupService();
    private UserService userService = new UserService();

    @RequestMapping(value = "/lessons", method = RequestMethod.GET)
    public String getLessons(@RequestParam(value = "lessonsByGroup", required = false) String lessonsByGroup,
                             @RequestParam(value = "lessonsByDate", required = false) String lessonsByDate,
                             @RequestParam(value = "lessonsByTutor", required = false) String lessonsByTutor, Model model) {
        ArrayList<Group> allGroups = groupService.getAllGroups();
        Set<Date> dates = lessonService.getAllDatesFromLessons();
        List<User> allTutors = userService.getAllUsersByType(1);
        model.addAttribute("allGroups", allGroups);
        model.addAttribute("dates", dates);
        model.addAttribute("allTutors", allTutors);
        List<Lesson> lessons = lessonService.getAllLessons();
        model.addAttribute("lessons", lessons);
        List<Lesson> listLessonsByGroup;
        List<Lesson> listLessonsByDate;
        List<Lesson> listLessonsByTutor;
        if (lessonsByGroup != null && !lessonsByGroup.equals("all")) {
            listLessonsByGroup = lessonService.getLessonsByGroup(Integer.parseInt(lessonsByGroup));
            model.addAttribute("listLessonsByGroup", listLessonsByGroup);
            model.addAttribute("lessonsByGroup", lessonsByGroup);
            lessons = null;
            model.addAttribute("lessons", lessons);
        } else if (lessonsByDate != null && !lessonsByDate.equals("all")) {
            listLessonsByDate = lessonService.getLessonsByDate(lessonsByDate);
            model.addAttribute("listLessonsByDate", listLessonsByDate);
            model.addAttribute("lessonsByDate", lessonsByDate);
            lessons = null;
            model.addAttribute("lessons", lessons);
        } else if (lessonsByTutor != null && !lessonsByTutor.equals("all")) {
            listLessonsByTutor = lessonService.getLessonsByTutor(Integer.parseInt(lessonsByTutor));
            model.addAttribute("listLessonsByTutor", listLessonsByTutor);
            model.addAttribute("lessonsByTutor", lessonsByTutor);
            lessons = null;
            model.addAttribute("lessons", lessons);
        } else {
            lessons = lessonService.getAllLessons();
            model.addAttribute("lessons", lessons);
        }
        return "lessons/lessons";
    }

    @RequestMapping("/lessons_add")
    public String addLesson() {
        return "lessons/lessons_add";
    }

    @RequestMapping("/lessons_edit")
    public String editLesson() {
        return "lessons/lessons_edit";
    }

    @RequestMapping("/lessons_delete")
    public String deleteLesson() {
        return "lessons/lessons_delete";
    }
}
