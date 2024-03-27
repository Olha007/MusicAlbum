package ua.edu.znu.musicalbum.controller;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.edu.znu.musicalbum.model.Album;
import ua.edu.znu.musicalbum.model.DTO.MusicAlbumDTO;
import ua.edu.znu.musicalbum.service.AlbumDaoImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        List<MusicAlbumDTO> musicAlbumDTOS = new ArrayList<>();
        for (Album album : albums) {
            MusicAlbumDTO musicAlbumDTO = new MusicAlbumDTO();
            musicAlbumDTO.setId(album.getId());
            musicAlbumDTO.setAlbumName(album.getAlbumName());


            musicAlbumDTOS.add(musicAlbumDTO);
        }

        String nextUrl = "home";
        request.setAttribute("musicAlbumDTOS", musicAlbumDTOS);
        request.setAttribute("nextUrl", nextUrl);
    }
}