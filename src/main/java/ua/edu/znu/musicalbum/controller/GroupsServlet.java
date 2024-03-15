package ua.edu.znu.musicalbum.controller;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.edu.znu.musicalbum.model.Group;
import ua.edu.znu.musicalbum.service.GroupDaoImpl;

import java.io.IOException;
import java.util.List;

@WebServlet("/GroupsServlet")
public class GroupsServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) {
        GroupDaoImpl groupDao = (GroupDaoImpl) getServletContext().getAttribute("groupDao");
        List<Group> groups = groupDao.findAll();
        String nextUrl = "groups";
        request.setAttribute("groups", groups);
        request.setAttribute("nextUrl", nextUrl);
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException {
        String action = request.getParameter("action");
        GroupDaoImpl groupDao = (GroupDaoImpl) getServletContext().getAttribute("groupDao");
        long groupId = Long.parseLong(request.getParameter("groupId"));
        Group group = groupDao.findById(groupId);
        switch (action) {
            case "groupEdit" -> {
                String groupName = request.getParameter("groupName");
                group.setGroupName(groupName);
                groupDao.update(group);
            }
            case "groupRemove" -> {
                groupDao.delete(group);
            }
        }
        String nextUrl = "groups";
        request.setAttribute("nextUrl", nextUrl);
        response.sendRedirect(request.getContextPath() + "/GroupsServlet");
    }
}

