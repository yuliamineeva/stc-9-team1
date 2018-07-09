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
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Controller
public class LessonController {
    private final static Logger logger = Logger.getLogger(LessonController.class);

    @Autowired
    private LessonService lessonService;

    @Autowired
    private GroupService groupService;

    @Autowired
    private UserServiceH userService;

    @RequestMapping(value = "/lessons", method = RequestMethod.GET)
    public String getLessons(@RequestParam(value = "lessonsByGroup", required = false) String lessonsByGroup,
                             @RequestParam(value = "lessonsByDate", required = false) String lessonsByDate,
                             @RequestParam(value = "lessonsByTutor", required = false) String lessonsByTutor, Model model) {
        ArrayList<Group> allGroups = groupService.getAllGroups();
        Set<Date> dates = lessonService.getAllDatesFromLessons();
        List<UserH> allTutors = userService.getAllUsersByType(1);
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


    @RequestMapping(value = "/lessons_add", method = RequestMethod.GET)
    public String addLessonPage(Model model) {
        ArrayList<Group> allGroups = groupService.getAllGroups();
        List<UserH> allTutors = userService.getAllUsersByType(1);
        model.addAttribute("allGroups", allGroups);
        model.addAttribute("allTutors", allTutors);
        return "lessons/lessons_add";
    }

    @RequestMapping(value = "/lessons_add", method = RequestMethod.POST)
    public String addLesson(@RequestParam(value = "lessonTopic", required = false) String lessonTopic,
                            @RequestParam(value = "lessonDate", required = false) String lessonDate,
//                            @RequestParam(value = "lessonDate", required = false) @DateTimeFormat(pattern="yyyy-MM-dd") Date lessonDate,
                            @RequestParam(value = "lessonTime", required = false) Time lessonTime,
                            @RequestParam(value = "lessonGroup", required = false) String lessonGroup,
                            @RequestParam(value = "lessonTutor", required = false) String lessonTutor, Model model) {
        ArrayList<Group> allGroups = groupService.getAllGroups();
        List<UserH> allTutors = userService.getAllUsersByType(1);
        model.addAttribute("allGroups", allGroups);
        model.addAttribute("allTutors", allTutors);
        if (lessonTopic.isEmpty() || lessonGroup == null || lessonTutor == null) {
            model.addAttribute("message", "EmptyField");
        } else {
            Group group = groupService.getGroupById(Integer.parseInt(lessonGroup));
            UserH tutor = userService.getUser(Integer.parseInt(lessonTutor));
            Date date = new Date();
            try {
                date = new SimpleDateFormat("yyyy-MM-dd").parse(lessonDate);
                date = new Date(date.getTime() + 24 * 60 * 60 * 1000);
            } catch (ParseException e) {
                logger.error("error to parse date", e);
            }
            boolean result = lessonService.addLesson(new Lesson(lessonTopic, date, lessonTime, group, tutor));
            if (result) {
                return getLessons("all", "all", "all", model);
            } else {
                model.addAttribute("message", "errAddLesson");
            }
        }
        return "lessons/lessons_add";
    }

    @RequestMapping(value = "/lessons_edit", method = RequestMethod.GET)
    public String editLessonPage(@RequestParam(value = "lesson_id", required = false) String lesson_id, Model model) {
        ArrayList<Group> allGroups = groupService.getAllGroups();
        List<UserH> allTutors = userService.getAllUsersByType(1);
        model.addAttribute("allGroups", allGroups);
        model.addAttribute("allTutors", allTutors);
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
        ArrayList<Group> allGroups = groupService.getAllGroups();
        List<UserH> allTutors = userService.getAllUsersByType(1);
        model.addAttribute("allGroups", allGroups);
        model.addAttribute("allTutors", allTutors);
        if (lessonTopic.isEmpty() || lessonGroup == null || lessonTutor == null || lessonTime == null) {
            model.addAttribute("message", "EmptyField");
            model.addAttribute("lesson", lessonService.getLessonById(Integer.valueOf(lessonId)));
        } else {
            Group group = groupService.getGroupById(Integer.parseInt(lessonGroup));
            UserH tutor = userService.getUser(Integer.parseInt(lessonTutor));
            Date date = new Date();
            try {
                date = new SimpleDateFormat("yyyy-MM-dd").parse(lessonDate);
                date = new Date(date.getTime() + 24 * 60 * 60 * 1000);
            } catch (ParseException e) {
                logger.error("error to parse date", e);
            }
            boolean result = lessonService.updateLesson(new Lesson(Integer.parseInt(lessonId), lessonTopic, date, lessonTime, group, tutor));
            if (result) {
                return getLessons("all", "all", "all", model);
            } else {
                model.addAttribute("message", "errEditLesson");
            }
        }
        return "lessons/lessons_edit";
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
        List<Lesson> lessons = lessonService.getAllLessons();
        model.addAttribute("lessons", lessons);
        return "lessons/lessons_delete";
    }
}
