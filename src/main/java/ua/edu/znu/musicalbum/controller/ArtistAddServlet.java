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

@WebServlet("/ArtistAddServlet")
public class ArtistAddServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest request,
                         HttpServletResponse response) {
        String nextUrl = "artistadd";
        request.setAttribute("nextUrl", nextUrl);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException, ServletException {
        ArtistDaoImpl artistDao = (ArtistDaoImpl) getServletContext().getAttribute("artistDao");
        Artist artist  = new Artist();
        String artistFirstName = request.getParameter("artistFirstName");
        artist.setFirstName(artistFirstName);
        String artistLastName = request.getParameter("artistLastName");
        artist.setLastName(artistLastName);
        artistDao.create(artist);

//        artist = artistDao.findByName(artistFirstName, artistLastName);

        String nextUrl = "artists";
        request.setAttribute("nextUrl", nextUrl);
        response.sendRedirect(request.getContextPath() + "/ArtistsServlet");
    }
}
