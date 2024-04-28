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
import java.util.List;

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
        GenreDaoImpl genreDao = (GenreDaoImpl) getServletContext().getAttribute("genreDao");
        long songId = Long.parseLong(request.getParameter("songId"));
        Song song = songDao.findById(songId);
        Genre genre = song.getGenre();
        List<Genre> otherGenres = genreDao.findAll();
        otherGenres.remove(genre);
        String nextUrl = "songedit";
        request.setAttribute("song", song);
        request.setAttribute("otherGenres", otherGenres);
        request.setAttribute("nextUrl", nextUrl);
        response.setContentType("text/html;charset=UTF-8");
    }
}