package ua.edu.znu.musicalbum.controller;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.edu.znu.musicalbum.model.Genre;
import ua.edu.znu.musicalbum.model.Song;
import ua.edu.znu.musicalbum.service.GenreDaoImpl;
import ua.edu.znu.musicalbum.service.SongDaoImpl;

import java.io.IOException;


@WebServlet("/SongAddServlet")
public class SongAddServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) {
        GenreDaoImpl genreDao = (GenreDaoImpl) getServletContext().getAttribute("genreDao");
        String nextUrl = "songadd";
        request.setAttribute("genres", genreDao.findAll());
        request.setAttribute("nextUrl", nextUrl);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException, ServletException {
        SongDaoImpl songDao = (SongDaoImpl) getServletContext().getAttribute("songDao");
        GenreDaoImpl genreDao = (GenreDaoImpl) getServletContext().getAttribute("genreDao");
        Song song = new Song();
        String songName = request.getParameter("songName");
        song.setSongName(songName);
        int durationMinutes = Integer.parseInt(request.getParameter("durationMinutes"));
        song.setDurationMinutes(durationMinutes);
        int durationSeconds = Integer.parseInt(request.getParameter("durationSeconds"));
        song.setDurationSeconds(durationSeconds);
        Long genreId = Long.valueOf(request.getParameter("selectedGenre"));
        Genre songGenre = genreDao.findById(genreId);
        song.setGenre(songGenre);
        songDao.create(song);
        String nextUrl = "songs";
        request.setAttribute("nextUrl", nextUrl);
        response.sendRedirect(request.getContextPath() + "/SongsServlet");
    }
}