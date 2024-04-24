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

@WebServlet("/AlbumAddServlet")
public class AlbumAddServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) {
        String nextUrl = "albumadd";
        request.setAttribute("nextUrl", nextUrl);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException, ServletException {
        AlbumDaoImpl albumDao = (AlbumDaoImpl) getServletContext().getAttribute("albumDao");
        Album album = new Album();
        String albumName = request.getParameter("albumName");
        album.setAlbumName(albumName);
        Integer releaseYear = Integer.valueOf(request.getParameter("releaseYear"));
        album.setReleaseYear(releaseYear);
        albumDao.create(album);

        String nextUrl = "albums";
        request.setAttribute("nextUrl", nextUrl);
        response.sendRedirect(request.getContextPath() + "/AlbumsServlet");
    }
}
