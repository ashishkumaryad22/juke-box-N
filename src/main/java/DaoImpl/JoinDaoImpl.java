package DaoImpl;

import DaoInterface.JoinDao;
import ModelClass.Podcast;
import ModelClass.Song;
import org.example.hellomaven.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JoinDaoImpl implements JoinDao {


    @Override
    public List<Song> getSongListByPlaylistID(int id) {
        List <Song> songs=new ArrayList<>();
        try(Connection con=new DBConnection().getConnection()){

            PreparedStatement prs=con.prepareStatement("select * from song as s join playdetails as pd on pd.item_id=s.song_id where pd.p_id=?");
            prs.setInt(1,id);
            ResultSet rs=prs.executeQuery();
            while(rs.next()){
                songs.add(new Song(rs.getInt(1),
                        rs.getString(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5),
                        rs.getString(6)));
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return songs;
    }


    @Override
    public List<Podcast> getPodsListByPlaylistID(int id) {
        List <Podcast> podcasts=new ArrayList<>();
        try(Connection con=new DBConnection().getConnection()){

            PreparedStatement prs=con.prepareStatement("select * from podcast as p join playdetails as pd on pd.item_id=p.pod_id where pd.p_id=?");
            prs.setInt(1,id);
            ResultSet rs=prs.executeQuery();
            while(rs.next()){
                podcasts.add(new Podcast(rs.getInt(1),
                        rs.getString(2)));
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return podcasts;
    }
}
