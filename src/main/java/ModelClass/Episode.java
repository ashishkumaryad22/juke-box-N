package ModelClass;

public class Episode {
    int e_id;
    int episode_no;
    String e_name;
    String upload_date;
    String duration;

    public Episode(int e_id, int episode_no, String e_name, String upload_date, String duration) {
        this.e_id = e_id;
        this.episode_no = episode_no;
        this.e_name = e_name;
        this.upload_date = upload_date;
        this.duration = duration;
    }

    public Episode() {
    }

    public int getE_id() {
        return e_id;
    }

    public void setE_id(int e_id) {
        this.e_id = e_id;
    }

    public int getEpisode_no() {
        return episode_no;
    }

    public void setEpisode_no(int episode_no) {
        this.episode_no = episode_no;
    }

    public String getE_name() {
        return e_name;
    }

    public void setE_name(String e_name) {
        this.e_name = e_name;
    }

    public String getUpload_date() {
        return upload_date;
    }

    public void setUpload_date(String upload_date) {
        this.upload_date = upload_date;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }



    @Override
    public String toString() {
        return "Episode{" +
                "e_id=" + e_id +
                ", episode_no=" + episode_no +
                ", e_name='" + e_name + '\'' +
                ", upload_date='" + upload_date + '\'' +
                ", duration='" + duration +"}";
    }
}
