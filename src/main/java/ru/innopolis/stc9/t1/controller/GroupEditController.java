package ru.innopolis.stc9.t1.controller;

import org.apache.log4j.Logger;
import ru.innopolis.stc9.t1.pojo.Group;
import ru.innopolis.stc9.t1.service.GroupService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class GroupEditController extends HttpServlet {
    private final static Logger logger = Logger.getLogger(GroupEditController.class);
    private GroupService groupService = new GroupService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        req.getRequestDispatcher("/group_edit.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String typeRequest = req.getParameter("submit");
        switch(typeRequest){
            case "Добавить":
                String groupName = req.getParameter("groupName");
                Group group = null;
                try{
                    group = new Group(-1,groupName);
                    boolean result = groupService.addGroup(group);
                    if(result){
                        resp.sendRedirect(req.getContextPath() + "/groups");
                    }else{
                        resp.sendRedirect(req.getContextPath() + "/groups/edit?act=add&result=addErr");
                    }
                }catch(Exception ex){
                    logger.error("Error to add group",ex);
                    resp.sendRedirect(req.getContextPath() + "/groups/edit?act=add&result=addErr");
                }
                break;
            case "Изменить":
                groupName = req.getParameter("groupName");
                String groupId = req.getParameter("groupId");
                try{
                    group = new Group(Integer.valueOf(groupId),groupName);
                    boolean result = groupService.updateGroup(group);
                    if(result){
                        resp.sendRedirect(req.getContextPath() + "/groups");
                    }else{
                        resp.sendRedirect(req.getContextPath() + "/groups/edit?act=edit&group_id="+groupId+"&result=editErr");
                    }
                }catch(Exception ex){
                    logger.error("Error to edit group",ex);
                    resp.sendRedirect(req.getContextPath() + "/groups/edit?act=edit&group_id="+groupId+"&result=editErr");
                }
                break;
            case "Удалить":
                groupId = req.getParameter("groupId");
                try{
                    boolean result = groupService.deleteGroup(Integer.valueOf(groupId));
                    if(result){
                        resp.sendRedirect(req.getContextPath() + "/groups");
                    }else{
                        resp.sendRedirect(req.getContextPath() + "/groups/edit?act=delete&group_id="+groupId+"&result=delErr");
                    }
                }catch(Exception ex){
                    logger.error("Error to delete group",ex);
                    resp.sendRedirect(req.getContextPath() + "/groups/edit?act=delete&group_id="+groupId+"&result=delErr");
                }
                break;
        }
    }
}
