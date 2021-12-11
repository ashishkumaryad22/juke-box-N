package ModelClass;

public class Playlist {
    int p_id;
    String playlist_name;
    int user_id;

    public Playlist(int p_id, String playlist_name, int user_id) {
        this.p_id = p_id;
        this.playlist_name = playlist_name;
        this.user_id = user_id;
    }

    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public String getPlaylist_name() {
        return playlist_name;
    }

    public void setPlaylist_name(String playlist_name) {
        this.playlist_name = playlist_name;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "Playlist{" +
                "p_id=" + p_id +
                ", playlist_name='" + playlist_name + '\'' +
                ", user_id=" + user_id +
                '}';
    }
}
