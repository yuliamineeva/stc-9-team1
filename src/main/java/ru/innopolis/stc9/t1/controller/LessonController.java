package ru.innopolis.stc9.t1.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.innopolis.stc9.t1.entities.UserH;
import ru.innopolis.stc9.t1.pojo.Group;
import ru.innopolis.stc9.t1.pojo.Lesson;
import ru.innopolis.stc9.t1.service.GroupService;
import ru.innopolis.stc9.t1.service.LessonService;
import ru.innopolis.stc9.t1.service.UserServiceH;

import java.sql.Time;
import java.util.Date;


@Controller
public class LessonController {
    private final static Logger logger = Logger.getLogger(LessonController.class);
    private LessonService lessonService;
    private GroupService groupService;
    private UserServiceH userService;

    @Autowired
    public LessonController(LessonService lessonService, GroupService groupService, UserServiceH userService) {
        this.lessonService = lessonService;
        this.groupService = groupService;
        this.userService = userService;
    }

    @RequestMapping(value = "/lessons", method = RequestMethod.GET)
    public String getLessons(@RequestParam(value = "lessonsByGroup", required = false) String lessonsByGroup,
                             @RequestParam(value = "lessonsByDate", required = false) String lessonsByDate,
                             @RequestParam(value = "lessonsByTutor", required = false) String lessonsByTutor, Model model) {
        model.addAttribute("allGroups", groupService.getAllGroups());
        model.addAttribute("dates", lessonService.getAllDatesFromLessons());
        model.addAttribute("allTutors", userService.getAllUsersByType(1));
        if (lessonsByGroup != null && !lessonsByGroup.equals("all")) {
            model.addAttribute("lessonsByGroup", lessonsByGroup);
            model.addAttribute("lessons", lessonService.getLessonsByGroup(Integer.parseInt(lessonsByGroup)));
        } else if (lessonsByDate != null && !lessonsByDate.equals("all")) {
            model.addAttribute("lessonsByDate", lessonsByDate);
            model.addAttribute("lessons", lessonService.getLessonsByDate(lessonsByDate));
        } else if (lessonsByTutor != null && !lessonsByTutor.equals("all")) {
            model.addAttribute("lessonsByTutor", lessonsByTutor);
            model.addAttribute("lessons", lessonService.getLessonsByTutor(Integer.parseInt(lessonsByTutor)));
        } else {
            model.addAttribute("lessons", lessonService.getAllLessons());
        }
        return "lessons/lessons";
    }

    @RequestMapping(value = "/lessons_add", method = RequestMethod.GET)
    public String addLessonPage(Model model) {
        model.addAttribute("allGroups", groupService.getAllGroups());
        model.addAttribute("allTutors", userService.getAllUsersByType(1));
        return "lessons/lessons_add";
    }

    @RequestMapping(value = "/lessons_add", method = RequestMethod.POST)
    public String addLesson(@RequestParam(value = "lessonTopic", required = false) String lessonTopic,
                            @RequestParam(value = "lessonDate", required = false) String lessonDate,
                            @RequestParam(value = "lessonTime", required = false) Time lessonTime,
                            @RequestParam(value = "lessonGroup", required = false) String lessonGroup,
                            @RequestParam(value = "lessonTutor", required = false) String lessonTutor, Model model) {
        model.addAttribute("allGroups", groupService.getAllGroups());
        model.addAttribute("allTutors", userService.getAllUsersByType(1));
        if (lessonTopic.isEmpty() || lessonGroup == null || lessonTutor == null) {
            model.addAttribute("message", "EmptyField");
            return "lessons/lessons_add";
        }
        Group group = groupService.getGroupById(Integer.parseInt(lessonGroup));
        UserH tutor = userService.getUser(Integer.parseInt(lessonTutor));
        Date date = lessonService.parseStringDate(lessonDate);
        boolean result = lessonService.addLesson(new Lesson(lessonTopic, date, lessonTime, group, tutor));
        if (result) {
            return getLessons("all", "all", "all", model);
        } else {
            model.addAttribute("message", "errAddLesson");
            return "lessons/lessons_add";
        }
    }

    @RequestMapping(value = "/lessons_edit", method = RequestMethod.GET)
    public String editLessonPage(@RequestParam(value = "lesson_id", required = false) String lesson_id, Model model) {
        model.addAttribute("allGroups", groupService.getAllGroups());
        model.addAttribute("allTutors", userService.getAllUsersByType(1));
        if (lesson_id != null) {
            model.addAttribute("lesson", lessonService.getLessonById(Integer.valueOf(lesson_id)));
        }
        return "lessons/lessons_edit";
    }

    @RequestMapping(value = "/lessons_edit", method = RequestMethod.POST)
    public String editLesson(@RequestParam(value = "lessonTopic", required = false) String lessonTopic,
                             @RequestParam(value = "lessonId", required = false) String lessonId,
                             @RequestParam(value = "lessonDate", required = false) String lessonDate,
                             @RequestParam(value = "lessonTime", required = false) Time lessonTime,
                             @RequestParam(value = "lessonGroup", required = false) String lessonGroup,
                             @RequestParam(value = "lessonTutor", required = false) String lessonTutor, Model model) {
        model.addAttribute("allGroups", groupService.getAllGroups());
        model.addAttribute("allTutors", userService.getAllUsersByType(1));
        if (lessonTopic.isEmpty() || lessonGroup == null || lessonTutor == null || lessonTime == null) {
            model.addAttribute("message", "EmptyField");
            model.addAttribute("lesson", lessonService.getLessonById(Integer.valueOf(lessonId)));
            return "lessons/lessons_edit";
        }
        Group group = groupService.getGroupById(Integer.parseInt(lessonGroup));
        UserH tutor = userService.getUser(Integer.parseInt(lessonTutor));
        Date date = lessonService.parseStringDate(lessonDate);
        boolean result = lessonService.updateLesson(new Lesson(Integer.parseInt(lessonId), lessonTopic, date, lessonTime, group, tutor));
        if (result) {
            return getLessons("all", "all", "all", model);
        } else {
            model.addAttribute("message", "errEditLesson");
            return "lessons/lessons_edit";
        }
    }


    @RequestMapping(value = "/lessons_delete", method = RequestMethod.GET)
    public String getDeleteLessonPage(
            @RequestParam(value = "lesson_id", required = false) String lesson_id, Model model) {
        if (lesson_id != null) {
            Lesson deleteLesson = lessonService.getLessonById(Integer.valueOf(lesson_id));
            model.addAttribute("deleteLesson", deleteLesson);
        }
        return "lessons/lessons_delete";
    }

    @RequestMapping(value = "/lessons_delete", method = RequestMethod.POST)
    public String deleteLesson(@RequestParam(value = "lessonId", required = false) String lessonId, Model model) {
        if (lessonId != null) {
            boolean result = lessonService.deleteLessonById(Integer.valueOf(lessonId));
            model.addAttribute("lessonId", lessonId);
            if (result) {
                model.addAttribute("message", "successfully");
            } else {
                model.addAttribute("message", "errDeleteLesson");
            }
        }
        model.addAttribute("lessons", lessonService.getAllLessons());
        return "lessons/lessons_delete";
    }
}
