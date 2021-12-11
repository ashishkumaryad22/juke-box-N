package DaoInterface;

import ModelClass.Podcast;
import ModelClass.Song;

import java.util.List;

public interface JoinDao {

    public List<Song> getSongListByPlaylistID(int id);
    public List<Podcast> getPodsListByPlaylistID(int id);
}
