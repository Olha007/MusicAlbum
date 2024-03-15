package ua.edu.znu.musicalbum.controller;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.edu.znu.musicalbum.model.Genre;
import ua.edu.znu.musicalbum.service.GenreDaoImpl;

import java.io.IOException;
import java.util.List;

@WebServlet("/GenresServlet")
public class GenresServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) {
        GenreDaoImpl genreDao = (GenreDaoImpl) getServletContext().getAttribute("genreDao");
        List<Genre> genres = genreDao.findAll();
        String nextUrl = "genres";
        request.setAttribute("genres", genres);
        request.setAttribute("nextUrl", nextUrl);
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException {
        String action = request.getParameter("action");
        GenreDaoImpl genreDao = (GenreDaoImpl) getServletContext().getAttribute("genreDao");
        long genreId = Long.parseLong(request.getParameter("genreId"));
        Genre genre = genreDao.findById(genreId);
        switch (action) {
            case "genreEdit" -> {
                String genreName = request.getParameter("genreName");
                genre.setName(genreName);
                genreDao.update(genre);
            }
            case "genreRemove" -> {
                genreDao.delete(genre);
            }
        }
        String nextUrl = "genres";
        request.setAttribute("nextUrl", nextUrl);
        response.sendRedirect(request.getContextPath() + "/GenresServlet");
    }
}

