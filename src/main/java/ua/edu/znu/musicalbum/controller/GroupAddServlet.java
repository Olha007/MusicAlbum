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

@WebServlet("/GroupAddServlet")
public class GroupAddServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) {
        String nextUrl = "groupadd";
        request.setAttribute("nextUrl", nextUrl);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException, ServletException {
        GroupDaoImpl groupDao = (GroupDaoImpl) getServletContext().getAttribute("groupDao");
        Group group = new Group();
        String groupName = request.getParameter("groupName");
        group.setGroupName(groupName);
        groupDao.create(group);
        String nextUrl = "groups";
        request.setAttribute("nextUrl", nextUrl);
        response.sendRedirect(request.getContextPath() + "/GroupsServlet");
    }
}
