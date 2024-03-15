package ua.edu.znu.musicalbum.controller;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.SneakyThrows;
import ua.edu.znu.musicalbum.model.Album;
import ua.edu.znu.musicalbum.service.AlbumDaoImpl;

import java.io.IOException;
import java.util.List;

@WebServlet("/AlbumsServlet")
public class AlbumsServlet extends HttpServlet {

    private AlbumDaoImpl albumDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        albumDao = new AlbumDaoImpl();
    }

    @SneakyThrows
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        List<Album> albums = albumDao.findAll();
        request.setAttribute("albums", albums);
        request.getRequestDispatcher("/albums.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String action = request.getParameter("action");
        long albumId = Long.parseLong(request.getParameter("albumId"));
        Album album = albumDao.findById(albumId);

        switch (action) {
            case "edit":
                String albumName = request.getParameter("albumName");
                album.setAlbumName(albumName);
                albumDao.update(album);
                break;
            case "delete":
                albumDao.delete(album);
                break;
            default:
                break;
        }

        response.sendRedirect(request.getContextPath() + "/AlbumsServlet");
    }
}


