package ua.edu.znu.musicalbum.controller;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.edu.znu.musicalbum.model.Album;
import ua.edu.znu.musicalbum.service.AlbumDaoImpl;

import java.io.IOException;
import java.util.List;

/**
 * The start point for the authenticated user.
 */
@WebServlet("/HomeServlet")
public class HomeServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        doPost(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException {
        response.setContentType("text/html;charset=UTF-8");
        AlbumDaoImpl albumDao = (AlbumDaoImpl) getServletContext().getAttribute("albumDao");
        List<Album> albums = albumDao.findAll();
        String nextUrl = "home"; // Встановлюємо наступний URL для переходу
        request.setAttribute("albums", albums); // Передаємо список альбомів у запит
        request.setAttribute("nextUrl", nextUrl); // Передаємо наступний URL для переходу
    }
}

