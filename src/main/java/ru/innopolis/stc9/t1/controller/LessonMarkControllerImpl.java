package ru.innopolis.stc9.t1.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.innopolis.stc9.t1.entities.UserH;
import ru.innopolis.stc9.t1.pojo.Lesson;
import ru.innopolis.stc9.t1.pojo.LessonMark;
import ru.innopolis.stc9.t1.service.LessonMarkService;
import ru.innopolis.stc9.t1.service.LessonService;
import ru.innopolis.stc9.t1.service.UserServiceH;

import java.util.List;

@Controller
public class LessonMarkControllerImpl implements LessonMarkController{
    private final static Logger logger = Logger.getLogger(LessonMarkControllerImpl.class);

    @Autowired
    private LessonMarkService lessonMarkService;

    @Autowired
    private  LessonService lessonService;

    @Autowired
    private UserServiceH userServiceH;

    @Override
    @RequestMapping(value = "/lessonMark", method = RequestMethod.GET)
    public String getAllLessonsMark(@RequestParam(value = "lessonsMarkByLesson", required = false) String lessonsMarkByLesson,
                                    @RequestParam(value = "lessonsByUser", required = false) String lessonsByUser,
                                    @RequestParam(value = "lessonsByVisit", required = false) String lessonsByVisit,
                                    @RequestParam(value = "lessonsByMark", required = false) String lessonsByMark, Model model) {
        List<LessonMark> lessonMark =  lessonMarkService.getAllLessonMark();
        List<Lesson> allLessons = lessonService.getAllLessons();
        model.addAttribute("allLessons", allLessons);
        model.addAttribute("lessonMark",lessonMark);
        return "lessonMark/lessonMark";
    }

    @Override
    @RequestMapping(value = "/lessonMark_add", method = RequestMethod.POST)
    public String addLessonMark(@RequestParam(value = "lessonMarkId", required = false) String lessonMarkId,
                                @RequestParam(value = "lessonsMarkByLessonOnCh", required = false) String lessonsMarkByLessonOnCh,
                                @RequestParam(value = "lessonsMarkByuser", required = false) String lessonsMarkByuser,
                                @RequestParam(value = "visit", required = false) String visit,
                                @RequestParam(value = "mark", required = false) String mark, Model model) {
        if(lessonsMarkByLessonOnCh != null && lessonsMarkByuser != null && visit !=null && mark != null){
            try{
                boolean visits = false;
                if(visit.equals("on"))
                {
                    visits = true;
                }
                LessonMark lessonMark =  new LessonMark(
                        Integer.valueOf(lessonsMarkByLessonOnCh), Integer.valueOf(lessonsMarkByuser),visits,
                        Float.valueOf(mark));
                boolean result = lessonMarkService.addLessonMark(lessonMark);
                if (result){
                    model.addAttribute("lessonMark",lessonMarkService.getAllLessonMark());
                    return "lessonMark/lessonMark_add";
                }
            }
            catch (Exception e) {
                logger.error("Error to edit lessonMark", e);
            }
        }
        model.addAttribute("result", "addErr");
        return "error";
    }


    @RequestMapping(value = "/lessonMarkAdd", method = RequestMethod.GET)
    public String addLessonMark(@RequestParam(value = "lessonsMarkByLesson", required = false) String lessonsMarkByLesson,
                                @RequestParam(value = "lessonsByUser", required = false) String lessonsByUser,
                                @RequestParam(value = "lessonsByVisit", required = false) String lessonsByVisit,
                                @RequestParam(value = "lessonsByMark", required = false) String lessonsByMark, Model model) {
        List<LessonMark> lessonMark =  lessonMarkService.getAllLessonMark();
        List<Lesson> allLessons = lessonService.getAllLessons();
        List<UserH> alluser =  userServiceH.getAllUsers();
        model.addAttribute("allLessons", allLessons);
        model.addAttribute("lessonMark",lessonMark);
        model.addAttribute("alluser", alluser);
        return "lessonMark/lessonMarkAdd";
    }

    @RequestMapping(value = "/lessonMarkEdit", method = RequestMethod.GET)
    public String editLessonMark(@RequestParam(value = "lessonsMarkByLesson", required = false) String lessonsMarkByLesson,
                                 @RequestParam(value = "lessonsByUser", required = false) String lessonsByUser,
                                 @RequestParam(value = "lessonsByVisit", required = false) String lessonsByVisit,
                                 @RequestParam(value = "lessonsByMark", required = false) String lessonsByMark, Model model) {
        List<LessonMark> lessonMark =  lessonMarkService.getAllLessonMark();
        List<Lesson> allLessons = lessonService.getAllLessons();
        List<UserH> alluser =  userServiceH.getAllUsers();
        model.addAttribute("allLessons", allLessons);
        model.addAttribute("lessonMark",lessonMark);
        model.addAttribute("alluser", alluser);
        return "lessonMark/lessonMarkEdit";
    }

    @Override
    @RequestMapping(value = "/lessonMark_edit", method = RequestMethod.POST)
    public String editLessonMark(@RequestParam(value = "lessonMark_id", required = false) String lessonMark_id,
                                 @RequestParam(value = "lessonId", required = false) String lessonId,
                                 @RequestParam(value = "tutorId", required = false) String tutorId,
                                 @RequestParam(value = "visit", required = false) String visit,
                                 @RequestParam(value = "mark", required = false) String mark, Model model) {
        if(lessonId != null && tutorId != null && visit !=null && mark != null && lessonMark_id != null ){
            try{
                LessonMark lessonMark =  new LessonMark(Integer.valueOf(lessonMark_id));
                boolean result = lessonMarkService.updateLessonMark(lessonMark);
                if (result){
                    model.addAttribute("lessonMark",lessonMarkService.getAllLessonMark());
                    return "lessonMark/lessonMark_edit";
                }
            }
            catch (Exception e) {
                logger.error("Error to edit lessonMark", e);
            }
        }
        model.addAttribute("result", "editErr");
        return "error";
    }

    @Override
    @RequestMapping(value = "/lessonMark_delete", method = RequestMethod.POST)
    public String deleteLessonMark(@RequestParam(value = "lessonMark_id", required = false) String lessonMark_id,
                                   @RequestParam(value = "lessonId", required = false) String lessonId,
                                   @RequestParam(value = "tutorId", required = false) String tutorId,
                                   @RequestParam(value = "visit", required = false) String visit,
                                   @RequestParam(value = "mark", required = false) String mark, Model model) {
        if( lessonMark_id != null ){
            //lessonId != null && tutorId != null && visit !=null && mark != null &&
            try{
                LessonMark lessonMark =  new LessonMark(Integer.valueOf(lessonMark_id));
                boolean result = lessonMarkService.deleteLessonMark(lessonMark);
                if (result){
                    model.addAttribute("lessonMark",lessonMarkService.getAllLessonMark());
                    return "lessonMark/lessonMark";
                }
            }
            catch (Exception e) {
                logger.error("Error to edit lessonMark", e);
            }
        }
        model.addAttribute("result", "editErr");
        return "error";
    }
}
