package DaoInterface;

import ModelClass.Episode;

import java.util.List;

public interface EpisodeDao {
    public List<Episode> getAllEpisodes();

    public List<Episode> getAllEpisodesbypID(int p_id);

    public List<Episode> getAllEpisodesByName(String name);

    public List<Episode> getAllEpisodesByDate(String podcast);
}
