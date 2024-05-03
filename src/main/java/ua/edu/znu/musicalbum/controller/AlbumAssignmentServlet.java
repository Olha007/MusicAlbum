package ua.edu.znu.musicalbum.controller;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.edu.znu.musicalbum.model.*;
import ua.edu.znu.musicalbum.service.*;

import java.io.IOException;
import java.util.*;

@WebServlet("/AlbumAssignmentServlet")
public class AlbumAssignmentServlet extends HttpServlet {

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action") == null
                ? (String) request.getAttribute("action") : request.getParameter("action");
        Long albumId = request.getParameter("albumId") == null
                ? (Long) request.getAttribute("albumId") : Long.valueOf(request.getParameter("albumId"));

        AlbumDaoImpl albumDao = (AlbumDaoImpl) getServletContext().getAttribute("albumDao");
        ArtistDaoImpl artistDao = (ArtistDaoImpl) getServletContext().getAttribute("artistDao");
        GroupDaoImpl groupDao = (GroupDaoImpl) getServletContext().getAttribute("groupDao");
        SongDaoImpl songDao = (SongDaoImpl) getServletContext().getAttribute("songDao");

        switch (action) {
            case "albumSelect" -> {
            }
            case "assignArtist" -> {
                Long artistId = Long.valueOf(request.getParameter("selectedArtist"));
                artistDao.assignArtist(albumId, artistId);
            }
            case "removeArtist" -> {
                Long artistId = Long.valueOf(request.getParameter("selectedArtist"));
                artistDao.removeArtist(albumId, artistId);
            }
            case "assignGroup" -> {
                Long groupId = Long.valueOf(request.getParameter("selectedGroup"));
                groupDao.assignGroup(albumId, groupId);
            }
            case "removeGroup" -> {
                Long groupId = Long.valueOf(request.getParameter("selectedGroup"));
                groupDao.removeGroup(albumId, groupId);
            }
            case "assignSong" -> {
                Long songId = Long.valueOf(request.getParameter("selectedSong"));
                songDao.assignSong(albumId, songId);
            }
            case "removeSong" -> {
                Long songId = Long.valueOf(request.getParameter("selectedSong"));
                songDao.removeSong(albumId, songId);
            }
        }

        Album album = albumDao.findById(albumId);

        List<Artist> availableArtists = artistDao.findAll();
        Collection<AlbumArtistGroup> albumArtistGroup = album.getAlbumArtistGroups();
        List<Artist> albumArtists = albumArtistGroup.stream()
                .map(AlbumArtistGroup::getArtist)
                .filter(Objects::nonNull)
                .toList();
        availableArtists.removeAll(albumArtists);

        List<Group> availableGroups = groupDao.findAll();
        List<Group> albumGroups = album.getAlbumArtistGroups().stream()
                .map(AlbumArtistGroup::getGroup)
                .filter(Objects::nonNull)
                .toList();
        availableGroups.removeAll(albumGroups);

        List<Song> availableSongs = songDao.findAll();
        Set<Song> albumSongs = album.getSongs();
        availableSongs.removeAll(albumSongs);

        String nextUrl = "albumassignment";
        request.setAttribute("album", album);
        request.setAttribute("albumArtists", albumArtists);
        request.setAttribute("otherArtists", availableArtists);
        request.setAttribute("albumGroups", albumGroups);
        request.setAttribute("otherGroups", availableGroups);
        request.setAttribute("albumSongs", albumSongs);
        request.setAttribute("otherSongs", availableSongs);

        request.setAttribute("nextUrl", nextUrl);
        response.setContentType("text/html;charset=UTF-8");
    }
}
