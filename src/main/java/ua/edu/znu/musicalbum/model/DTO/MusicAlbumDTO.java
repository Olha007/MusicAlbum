package ua.edu.znu.musicalbum.model.DTO;
import lombok.Data;
/**
 * DTO for displaying album information on the home page.
 * Used in the {@link ua.edu.znu.musicalbum.controller.HomeServlet}.
 */
@Data
public class MusicAlbumDTO {
    private Long id;
    private String albumName;
    private String genres;
    private Integer releaseYear;
    private String artistName;
    private String groupName;
}



