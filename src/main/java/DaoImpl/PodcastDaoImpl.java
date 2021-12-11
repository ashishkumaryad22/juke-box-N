package DaoImpl;

import DaoInterface.PodcastDao;
import ModelClass.Podcast;
import org.example.hellomaven.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PodcastDaoImpl implements PodcastDao {
    List<Podcast> pods=new ArrayList<>();

    public List<Podcast> getAllPodcasts(){
        try(Connection con=new DBConnection().getConnection()){
            PreparedStatement prs=con.prepareStatement("select * from podcast");
            ResultSet rs=prs.executeQuery();
            while(rs.next()){
                pods.add(new Podcast(rs.getInt(1),
                        rs.getString(2)));
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return pods;
    }

    @Override
    public boolean CheckIfPodIdPresent(int id) {
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement prs = con.prepareStatement("select * from song where song_id='"+id+"' ");
            ResultSet rs=prs.executeQuery();
            while(rs.next()){
                int id_check=rs.getInt(1);
                if(id_check!=0){
                    return true;
                }
            }
        }
        catch (SQLException e){
            System.out.println(e);
        }
        return false;
    }

    @Override
    public String getPodcastNameById(int id) {
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement prs = con.prepareStatement("select pod_name from podcast where pod_id=?");
            prs.setInt(1,id);
            ResultSet rs=prs.executeQuery();
            while(rs.next()){
                return rs.getString(1);
            }
        }
        catch (SQLException e){
            System.out.println(e);
        }
        return null;
    }
}
