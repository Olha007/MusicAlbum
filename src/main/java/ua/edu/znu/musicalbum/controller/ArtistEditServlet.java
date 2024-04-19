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

@WebServlet("/ArtistEditServlet")
public class ArtistEditServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doPost(HttpServletRequest request,
                          HttpServletResponse response)
            throws IOException {
        ArtistDaoImpl artistDao = (ArtistDaoImpl) getServletContext().getAttribute("artistDao");
        long artistId = Long.parseLong(request.getParameter("artistId"));
        Artist artist = artistDao.findById(artistId);
        String nextUrl = "artistedit";
        request.setAttribute("artist", artist);
        request.setAttribute("nextUrl", nextUrl);
        response.setContentType("text/html;charset=UTF-8");
    }
}
