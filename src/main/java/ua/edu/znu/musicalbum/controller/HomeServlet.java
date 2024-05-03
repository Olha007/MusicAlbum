package ua.edu.znu.musicalbum.controller;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.edu.znu.musicalbum.model.Album;
import ua.edu.znu.musicalbum.model.AlbumArtistGroup;
import ua.edu.znu.musicalbum.model.Artist;
import ua.edu.znu.musicalbum.model.DTO.AlbumAssignment;
import ua.edu.znu.musicalbum.model.Song;
import ua.edu.znu.musicalbum.service.AlbumDaoImpl;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

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
        List<AlbumAssignment> musicAlbumDTOS = new ArrayList<>();
        for (Album album : albums) {
            AlbumAssignment musicAlbumDTO = new AlbumAssignment();
            musicAlbumDTO.setId(album.getId());
            musicAlbumDTO.setAlbumName(album.getAlbumName());
            Set<Song> songs = album.getSongs();
            StringBuilder songsNames = new StringBuilder();
            for (Song song : songs) {
                songsNames.append(song.getSongName()).append(", ");
            }
            musicAlbumDTO.setSongs(songsNames.toString());
            StringBuilder genres = new StringBuilder();
            for (Song song : songs) {
                genres.append(song.getGenre().getName()).append(", ");

            }
            musicAlbumDTO.setGenres(genres.toString());

            musicAlbumDTO.setReleaseYear(album.getReleaseYear());
            Collection<AlbumArtistGroup> albumArtistGroups = album.getAlbumArtistGroups();
            StringBuilder artists = new StringBuilder();
            for (AlbumArtistGroup albumArtistGroup : albumArtistGroups) {
                Artist artist = albumArtistGroup.getArtist();
                if (artist != null) {
                    artists.append(artist.getFirstName()).append(", ").append(artist.getLastName()).append("; ");
                }
            }
            musicAlbumDTO.setArtistName(artists.toString());
            /*Add Album groups to DTO*/
            StringBuilder groups = new StringBuilder();
            for (AlbumArtistGroup albumArtistGroup : albumArtistGroups) {
                if (albumArtistGroup.getGroup() != null) {
                    groups.append(albumArtistGroup.getGroup().getGroupName());
                }
            }
            musicAlbumDTO.setGroupName(groups.toString());
            musicAlbumDTOS.add(musicAlbumDTO);
        }

        String nextUrl = "home";
        request.setAttribute("musicAlbumDTOS", musicAlbumDTOS);
        request.setAttribute("nextUrl", nextUrl);
    }
}