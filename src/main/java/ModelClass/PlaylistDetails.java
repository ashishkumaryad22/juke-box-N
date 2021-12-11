package ModelClass;

public class PlaylistDetails {
    int detail_id;
    int p_id;
    int item_id;

    public PlaylistDetails(int detail_id, int p_id, int item_id) {
        this.detail_id = detail_id;
        this.p_id = p_id;
        this.item_id = item_id;
    }

    public PlaylistDetails() {
    }

    public int getDetail_id() {
        return detail_id;
    }

    public void setDetail_id(int detail_id) {
        this.detail_id = detail_id;
    }

    public int getP_id() {
        return p_id;
    }

    public void setP_id(int p_id) {
        this.p_id = p_id;
    }

    public int getItem_id() {
        return item_id;
    }

    public void setItem_id(int item_id) {
        this.item_id = item_id;
    }

    @Override
    public String toString() {
        return "PlaylistDetails{" +
                "detail_id=" + detail_id +
                ", p_id=" + p_id +
                ", item_id=" + item_id +
                '}';
    }
}
