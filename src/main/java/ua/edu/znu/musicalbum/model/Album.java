package ua.edu.znu.musicalbum.model;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Entity
@Table(name = "albums")
public class Album {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "album_id", nullable = false)
    private Long id;
    @Column(name = "album_name", nullable = false)
    private String albumName;
    @Column(name = "release_year", nullable = false)
    private Integer releaseYear;
    @ManyToMany
    @JoinTable(name = "album_songs",
            joinColumns = @JoinColumn(name = "album_id"),
            inverseJoinColumns = @JoinColumn(name = "song_id"))
    private Set<Song> songs = new LinkedHashSet<>();

    @OneToMany(mappedBy = "album", cascade = CascadeType.PERSIST, orphanRemoval = true)
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private Collection<AlbumArtistGroup> albumArtistGroups;






    public void addSong(Song song) {
        this.songs.add(song);
    }

    public void removeSong(Long songId) {
        this.songs.removeIf(song -> song.getId().equals(songId));
    }

    public void assignArtist(Artist artist) {
        AlbumArtistGroup aag = new AlbumArtistGroup();
        aag.setAlbum(this);
        aag.setArtist(artist);
        this.albumArtistGroups.add(aag);
    }

    public void assignGroup(Group group) {
        this.albumArtistGroups = this.albumArtistGroups.stream()
                .filter(aag -> aag.getGroup() == null || !aag.getGroup().equals(group))
                .collect(Collectors.toSet());

        AlbumArtistGroup aag = new AlbumArtistGroup();
        aag.setAlbum(this);
        aag.setGroup(group);
        this.albumArtistGroups.add(aag);
    }
}
