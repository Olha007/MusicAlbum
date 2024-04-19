package ua.edu.znu.musicalbum.controller.listener;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import ua.edu.znu.musicalbum.service.*;

@WebListener
public class MusicAlbumContextListener implements ServletContextListener {
    private AlbumDaoImpl albumDao;
    private ArtistDaoImpl artistDao;
    private GenreDaoImpl genreDao;
    private GroupDaoImpl groupDao;
    private SongDaoImpl songDao;
    private UserDaoImpl userDao;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContextListener.super.contextInitialized(sce);

        albumDao = new AlbumDaoImpl();
        artistDao = new ArtistDaoImpl();
        genreDao = new GenreDaoImpl();
        groupDao = new GroupDaoImpl();
        songDao = new SongDaoImpl();
        userDao = new UserDaoImpl();

        ServletContext app = sce.getServletContext();
        app.setAttribute("albumDao", albumDao);
        app.setAttribute("artistDao", artistDao);
        app.setAttribute("genreDao", genreDao);
        app.setAttribute("groupDao", groupDao);
        app.setAttribute("songDao", songDao);
        app.setAttribute("userDao", userDao);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContextListener.super.contextDestroyed(sce);

        albumDao = null;
        artistDao = null;
        genreDao = null;
        groupDao = null;
        songDao = null;
        userDao = null;
    }
}

