-- init.sql
CREATE TABLE IF NOT EXISTS songs (
    song_id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    artist VARCHAR(255) NOT NULL,
    album VARCHAR(255) NOT NULL,
    length VARCHAR(255) NOT NULL,
    resource_id VARCHAR(255) NOT NULL,
    year VARCHAR(255) NOT NULL
);

INSERT INTO songs(song_id, name, artist, album, length, resource_id, year)
SELECT 0, 'Beat It', 'Michael Jackson', 'Thriller', '258', '0', '1982'
WHERE NOT EXISTS (SELECT s.song_id FROM songs s WHERE s.song_id = 0);
