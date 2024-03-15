package ua.edu.znu.musicalbum.model.DTO;

import lombok.Data;

@Data
public class MusicAlbumDTO {
    private Long id;
    private String albumName;
    private String genre;
    private Integer releaseYear;
    private String artistName;
    private String group;
}

