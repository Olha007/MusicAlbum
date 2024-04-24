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

@WebServlet("/AlbumsServlet")
public class AlbumsServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) throws ServletException, IOException {
        AlbumDaoImpl albumDao = (AlbumDaoImpl) getServletContext().getAttribute("albumDao");
        List<Album> albums = albumDao.findAll();
        String nextUrl = "albums";
        request.setAttribute("albums", albums);
        request.setAttribute("nextUrl", nextUrl);
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");
        AlbumDaoImpl albumDao = (AlbumDaoImpl) getServletContext().getAttribute("albumDao");
        long albumId = Long.parseLong(request.getParameter("albumId"));
        Album album = albumDao.findById(albumId);

        switch (action) {
            case "albumEdit" -> {
                String albumName = request.getParameter("albumName");
                album.setAlbumName(albumName);
                Integer releaseYear = Integer.valueOf(request.getParameter("releaseYear"));
                album.setReleaseYear(releaseYear);
                albumDao.update(album);
            }
            case "albumRemove" -> {
                albumDao.delete(album);
            }
        }
        String nextUrl = "albums";
        request.setAttribute("nextUrl", nextUrl);
        response.sendRedirect(request.getContextPath() + "/AlbumsServlet");
    }
}

