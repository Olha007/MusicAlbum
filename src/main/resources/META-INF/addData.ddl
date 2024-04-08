SET
SQL_SAFE_UPDATES = 0;

LOCK TABLES `album_artist_group` WRITE;
DELETE FROM `album_artist_group`;
UNLOCK TABLES;

LOCK
TABLES `album` WRITE;
DELETE
FROM `album`;
UNLOCK
TABLES;

LOCK
TABLES `songs` WRITE;
DELETE
FROM `songs`;
UNLOCK
TABLES;

LOCK
TABLES `genre` WRITE;
DELETE
FROM `genre`;
UNLOCK
TABLES;

LOCK
TABLES `group` WRITE;
DELETE
FROM `group`;
UNLOCK
TABLES;

LOCK
TABLES `artist` WRITE;
DELETE
FROM `artist`;
UNLOCK
TABLES;

LOCK
TABLES `users` WRITE;
DELETE
FROM `users`;
UNLOCK
TABLES;

LOCK
TABLES `album` WRITE;
INSERT INTO album (album_id, album_name, release_year)
VALUES (1, 'Album 1', 2020),
       (2, 'Album 2', 2021),
       (3, 'Album 3', 1990);
UNLOCK
TABLES;

LOCK
TABLES `songs` WRITE;
INSERT INTO songs (song_id, song_name, duration_minutes, duration_seconds)
VALUES (1, 'Songs 1', 3, 30),
       (2, 'Songs 2', 4, 15);
UNLOCK
TABLES;

LOCK
TABLES `genre` WRITE;
INSERT INTO Genre (genre_id, genre_name)
VALUES (1, 'Genre 1'),
       (2, 'Genre 2');
UNLOCK
TABLES;

LOCK
TABLES `group` WRITE;
INSERT INTO `Group` (group_id, group_name)
VALUES (1, 'Group 1'),
       (2, 'Group 2');
UNLOCK
TABLES;

LOCK
TABLES `artist` WRITE;
INSERT INTO Artist (artist_id, first_name, last_name)
VALUES (1, 'John', 'Doe'),
       (2, 'Jane', 'Doe');
UNLOCK
TABLES;

LOCK
    TABLES `album_artist_group` WRITE;
INSERT INTO album_artist_group (album_id, artist_id, group_id)
VALUES (1, 2, 1);
UNLOCK
    TABLES;

LOCK
    TABLES `album_songs` WRITE;
INSERT INTO album_songs (album_id, song_id)
VALUES (1, 1),
       (1, 2),
       (2, 1);
UNLOCK
    TABLES;

LOCK
TABLES `users` WRITE;
INSERT INTO `users` (id, `username`, password)
VALUES (1, 'foo', 'bar');
UNLOCK
TABLES;

SET
SQL_SAFE_UPDATES = 1;