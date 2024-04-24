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
import java.util.List;

@WebServlet("/SongsServlet")
public class SongsServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) {
        SongDaoImpl songDao = (SongDaoImpl) getServletContext().getAttribute("songDao");
        List<Song> songs = songDao.findAll();
        String nextUrl = "songs";
        request.setAttribute("songs", songs);
        request.setAttribute("nextUrl", nextUrl);
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException {
        String action = request.getParameter("action");
        SongDaoImpl songDao = (SongDaoImpl) getServletContext().getAttribute("songDao");
        long songId = Long.parseLong(request.getParameter("songId"));
        Song song = songDao.findById(songId);
        switch (action) {
            case "songEdit" -> {
                String songName = request.getParameter("songName");
                song.setSongName(songName);
                Integer durationMinutes = Integer.valueOf(request.getParameter("durationMinutes"));
                song.setDurationMinutes(durationMinutes);
                Integer durationSeconds = Integer.valueOf(request.getParameter("durationSeconds"));
                song.setDurationSeconds(durationSeconds);
                songDao.update(song);
            }
            case "songRemove" -> {
                songDao.delete(song);
            }
        }
        String nextUrl = "songs";
        request.setAttribute("nextUrl", nextUrl);
        response.sendRedirect(request.getContextPath() + "/SongsServlet");
    }
}

