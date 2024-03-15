package ua.edu.znu.musicalbum.model;

import lombok.*;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "album")

public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "album_id", nullable = false)
    private Long id;

    @Column(name = "album_name")
    private String albumName;

    @Column(name = "release_year")
    private Integer releaseYear;

    @OneToMany(mappedBy = "album", cascade = CascadeType.MERGE)
    @JoinTable(name = "album_songs",
            joinColumns = @JoinColumn(name = "album_id"),
            inverseJoinColumns = @JoinColumn(name = "song_id"))
    private Set<Song> songs = new LinkedHashSet<>();
}
