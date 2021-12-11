package DaoInterface;


import ModelClass.Playlist;

import java.util.List;

public interface PlaylistDao {
    public List<Playlist> getAllPlaylists();


    public boolean deletebyID(int id);


    public void deletebyName(String name);


    public int getPlaylistIDByName(String name);


    public void addPlaylist(Playlist playlist);

    public int getPlaylistID();

    public List<Playlist> getAllPlaylistsByUserId(int user_id);

}
