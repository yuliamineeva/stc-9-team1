package ru.innopolis.stc9.t1.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by ssspokd on 10.07.2018.
 */
public interface LessonMarkController {
    public String getAllLessonsMark(@RequestParam(value = "lessonsMarkByLesson", required = false) String lessonsMarkByLesson,
                                    @RequestParam(value = "lessonsByUser", required = false) String lessonsByUser,
                                    @RequestParam(value = "lessonsByVisit", required = false) String lessonsByVisit,
                                    @RequestParam(value = "lessonsByMark", required = false) String lessonsByMark,Model model);
    public String addLessonMark(@RequestParam(value = "lessonMarkId", required = false) String lessonMarkId,
                                @RequestParam(value = "lessonsMarkByLessonOnCh", required = false) String lessonsMarkByLessonOnCh,
                                @RequestParam(value = "lessonsMarkByuser", required = false) String lessonsMarkByuser,
                                @RequestParam(value = "visit", required = false) String visit,
                                @RequestParam(value = "mark", required = false) String mark, Model model);

    public String editLessonMark(@RequestParam(value = "lessonMarkId", required = false) String lessonMarkId,
                                 @RequestParam(value = "lessonId", required = false) String lessonId,
                                 @RequestParam(value = "tutorId", required = false) String tutorId,
                                 @RequestParam(value = "visit", required = false) String visit,
                                 @RequestParam(value = "mark", required = false) String mark, Model model);


    public String deleteLessonMark(@RequestParam(value = "lessonMarkId", required = false) String lessonMarkId,
                                 @RequestParam(value = "lessonId", required = false) String lessonId,
                                 @RequestParam(value = "tutorId", required = false) String tutorId,
                                 @RequestParam(value = "visit", required = false) String visit,
                                 @RequestParam(value = "mark", required = false) String mark, Model model);
}
