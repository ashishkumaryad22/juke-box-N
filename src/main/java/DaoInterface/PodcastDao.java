package DaoInterface;

import ModelClass.Podcast;

import java.util.List;

public interface PodcastDao {
    public List<Podcast> getAllPodcasts();

    public boolean CheckIfPodIdPresent(int id);

    public String getPodcastNameById(int id);
}
