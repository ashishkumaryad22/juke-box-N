package ModelClass;

import java.util.List;

public class Podcast {
    int pod_id;
    String pod_name;

    public Podcast(int pod_id, String pod_name) {
        this.pod_id = pod_id;
        this.pod_name = pod_name;
    }

    public Podcast() {
    }

    public int getPod_id() {
        return pod_id;
    }

    public void setPod_id(int pod_id) {
        this.pod_id = pod_id;
    }

    public String getPod_name() {
        return pod_name;
    }

    public void setPod_name(String pod_name) {
        this.pod_name = pod_name;
    }


    @Override
    public String toString() {
        return "Podcast{" +
                "pod_id=" + pod_id +
                ", pod_name='" + pod_name + '\'' +
                '}';
    }
}
