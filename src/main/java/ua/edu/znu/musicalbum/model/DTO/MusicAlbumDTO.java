package ua.edu.znu.musicalbum.model.DTO;

import lombok.Data;

import java.util.Set;

/**
 * DTO for displaying album information on the home page.
 * Used in the {@link ua.edu.znu.musicalbum.controller.HomeServlet}.
 */
@Data
public class MusicAlbumDTO {
    private Long id;
    private String albumName;
    private String genreId;
    private Integer releaseYear;
    private String artistName;
    private String group;

}


