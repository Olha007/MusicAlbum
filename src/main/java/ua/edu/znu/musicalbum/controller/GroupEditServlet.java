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

@WebServlet("/GroupEditServlet")
public class GroupEditServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException {
        GroupDaoImpl groupDao = (GroupDaoImpl) getServletContext().getAttribute("groupDao");
        long groupId = Long.parseLong(request.getParameter("groupId"));
        Group group = groupDao.findById(groupId);
        String nextUrl = "groupedit";
        request.setAttribute("group", group);
        request.setAttribute("nextUrl", nextUrl);
        response.setContentType("text/html;charset=UTF-8");
    }
}