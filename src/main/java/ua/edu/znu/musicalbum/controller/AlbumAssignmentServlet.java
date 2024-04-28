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
import java.util.List;
import java.util.Set;

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
        SongDaoImpl songDao = (SongDaoImpl) getServletContext().getAttribute("songDao");
//        GenreDaoImpl genreDao = (GenreDaoImpl) getServletContext().getAttribute("genreDao");
        ArtistDaoImpl artistDao = (ArtistDaoImpl) getServletContext().getAttribute("artistDao");
        GroupDaoImpl groupDao = (GroupDaoImpl) getServletContext().getAttribute("groupDao");

        switch (action) {
            case "albumSelect" -> {
            }
            case "assignArtist" -> {
                Long artistId = Long.valueOf(request.getParameter("selectedArtist"));
                albumDao.assignArtist(albumId, artistId);
            }
            case "removeArtist" -> {
                Long artistId = Long.valueOf(request.getParameter("selectedArtist"));
                albumDao.removeArtist(albumId, artistId);
            }
//            case "assignGroup" -> {
//                Long groupId = Long.valueOf(request.getParameter("selectedGroup"));
//                Group group = groupDao.findById(groupId);
//                album.assignGroup(group);
//                albumDao.update(album);
//            }
//            case "addSong" -> {
//                Long songId = Long.valueOf(request.getParameter("selectedSong"));
//                Song song = songDao.findById(songId);
//                album.addSong(song);
//                albumDao.update(album);
//            }
//            case "removeSong" -> {
//                Long songId = Long.valueOf(request.getParameter("selectedSong"));
//                album.removeSong(songId);
//                albumDao.update(album);
//            }
//            case "updateAlbum" -> {
//                String albumName = request.getParameter("albumName");
//                int releaseYear = Integer.parseInt(request.getParameter("releaseYear"));
//                album.setAlbumName(albumName);
//                album.setReleaseYear(releaseYear);
//                albumDao.update(album);
//            }
        }

        Album album = albumDao.findById(albumId);
        List<Song> availableSongs = songDao.findAll();
        Set<Song> albumSongs = album.getSongs();
        availableSongs.removeAll(albumSongs);

//        List<Genre> availableGenres = genreDao.findAll();

        List<Artist> availableArtists = artistDao.findAll();
        List<Artist> albumArtists = album.getAlbumArtistGroups().stream()
                .map(AlbumArtistGroup::getArtist)
                .toList();
        availableArtists.removeAll(albumArtists);

        List<Group> availableGroups = groupDao.findAll();
        List<Group> albumGroups = album.getAlbumArtistGroups().stream()
                .map(AlbumArtistGroup::getGroup)
                .toList();
        availableGroups.removeAll(albumGroups);

        String nextUrl = "albumassignment";
        request.setAttribute("album", album);
        request.setAttribute("albumArtists", albumArtists);
        request.setAttribute("otherArtists", availableArtists);
//        request.setAttribute("otherGroups", availableGroups);
//        request.setAttribute("otherSongs", availableSongs);
//        request.setAttribute("availableGenres", availableGenres);


        request.setAttribute("nextUrl", nextUrl);

        response.setContentType("text/html;charset=UTF-8");
    }
}
