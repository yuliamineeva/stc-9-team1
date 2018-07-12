package ru.innopolis.stc9.t1;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.innopolis.stc9.t1.db.dao.*;
import ru.innopolis.stc9.t1.service.GroupService;
import ru.innopolis.stc9.t1.service.LessonService;
import ru.innopolis.stc9.t1.service.UserServiceH;


@Configuration
@PropertySource(value = {"classpath:datasource-config.properties"})
public class TestContext {

    @Bean
    public LessonService lessonsService() {
        return Mockito.mock(LessonService.class);
    }

    @Bean
    public GroupService groupService() {
        return Mockito.mock(GroupService.class);
    }

    @Bean
    public UserServiceH userService() {
        return Mockito.mock(UserServiceH.class);
    }

    @Bean
    public LessonDAO lessonDAO() {
        return Mockito.mock(LessonDAOImplHib.class);
    }

    @Bean
    public GroupDAO groupDao() {
        return Mockito.mock(GroupDAOImplH.class);
    }

    @Bean
    public UserDAO_H userDao() {
        return Mockito.mock(UserDAOImplH.class);
    }

    @Bean
    public ErrorMsgHandler errorMsgHandler() {
        return Mockito.mock(ErrorMsgHandler.class);
    }

//    @Bean
//    public LessonMarkDAO lessonMarkDAO(){
//        return Mockito.mock(LessonMarkDAOImplHib.class);
//    }
}
