package ua.edu.znu.musicalbum.controller;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.edu.znu.musicalbum.model.Song;
import ua.edu.znu.musicalbum.service.SongDaoImpl;

import java.io.IOException;

@WebServlet("/SongEditServlet")
public class SongEditServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException {
        SongDaoImpl songDao = (SongDaoImpl) getServletContext().getAttribute("songDao");
        long songId = Long.parseLong(request.getParameter("songId"));
        Song artist = songDao.findById(songId);
        String nextUrl = "songedit";
        request.setAttribute("song", artist);
        request.setAttribute("nextUrl", nextUrl);
        response.setContentType("text/html;charset=UTF-8");
    }
}