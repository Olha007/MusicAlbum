package ua.edu.znu.musicalbum.controller;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.edu.znu.musicalbum.model.Artist;
import ua.edu.znu.musicalbum.service.ArtistDaoImpl;


import java.io.IOException;
import java.util.List;

@WebServlet("/ArtistsServlet")
public class ArtistsServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) {
        ArtistDaoImpl artistDao = (ArtistDaoImpl) getServletContext().getAttribute("artistDao");
        List<Artist> artists = artistDao.findAll();
        String nextUrl = "artists";
        request.setAttribute("artists", artists);
        request.setAttribute("nextUrl", nextUrl);
        response.setContentType("text/html;charset=UTF-8");
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException {
        String action = request.getParameter("action");
        ArtistDaoImpl artistDao = (ArtistDaoImpl) getServletContext().getAttribute("artistDao");
        long artistId = Long.parseLong(request.getParameter("artistId"));
        Artist artist = artistDao.findById(artistId);
        switch (action) {
            case "artistEdit" -> {
                String artistFirstName = request.getParameter("artistFirstName");
                artist.setFirstName(artistFirstName);
                String artistLastName = request.getParameter("artistLastName");
                artist.setLastName(artistLastName);
                artistDao.update(artist);
            }
            case "artistRemove" -> {
                artistDao.delete(artist);
            }
        }
        String nextUrl = "artists";
        request.setAttribute("nextUrl", nextUrl);
        response.sendRedirect(request.getContextPath() + "/ArtistsServlet");
    }
}

