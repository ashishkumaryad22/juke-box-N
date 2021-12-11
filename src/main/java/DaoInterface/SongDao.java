package DaoInterface;

import ModelClass.Song;

import java.util.List;

public interface SongDao {
    public List<Song> getAllSongs();

    //public int getSongIdbyName(String song_name);

    public String getSongNAmeById(int id);

    public boolean CheckIfSongIdPresent(int id);
}
