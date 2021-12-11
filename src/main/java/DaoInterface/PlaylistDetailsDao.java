package DaoInterface;

import ModelClass.PlaylistDetails;

import java.util.List;

public interface PlaylistDetailsDao {
    public List<PlaylistDetails> displayDetailsOfPlaylistbyId(int id);

    public int getLastplaydetails_id();

    public boolean checkIfExists(int item_id, int playlist_id);

    public void addItemToPlaylist(int item_id, int playlist_id, int detail_id);

    public List<PlaylistDetails> shuffledPlaylist(List<PlaylistDetails> playdetails);

}
