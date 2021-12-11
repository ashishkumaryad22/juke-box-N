package DaoImpl;

import DaoInterface.PlaylistDetailsDao;
import ModelClass.PlaylistDetails;
import org.example.hellomaven.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PlaylistdetailsDaoImpl implements PlaylistDetailsDao {
    List <PlaylistDetails> playdetails=new ArrayList<>();
    @Override
    public List<PlaylistDetails> displayDetailsOfPlaylistbyId(int id) {
        try(Connection con=new DBConnection().getConnection()){
            PreparedStatement prs=con.prepareStatement("select * from playdetails where p_id=?");
            prs.setInt(1, id);
            ResultSet rs=prs.executeQuery();
            while(rs.next()){
                playdetails.add(new PlaylistDetails(rs.getInt(1),rs.getInt(2),rs.getInt(3)));
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return playdetails;
    }

    @Override
    public int getLastplaydetails_id() {
        try(Connection con=new DBConnection().getConnection()){
            PreparedStatement prs=con.prepareStatement("select detail_id from playdetails order by detail_id desc limit 1");
            ResultSet rs=prs.executeQuery();
            while(rs.next()){
                return rs.getInt(1);
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return 0;
    }

    @Override
    public boolean checkIfExists(int item_id, int playlist_id) {
        try(Connection con=new DBConnection().getConnection()){
            PreparedStatement prs=con.prepareStatement("select item_id from playdetails where p_id=?");
            prs.setInt(1,playlist_id);
            ResultSet rs=prs.executeQuery();
            while(rs.next()){
                int returned=rs.getInt(1);
                if(returned==item_id){
                    return true;
                }
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return false;
    }

    @Override
    public void addItemToPlaylist(int item_id, int playlist_id, int detail_id) {
        try(Connection con=new DBConnection().getConnection()){
            int intial_size_OfPlaylist=playdetails.size();
            PreparedStatement prs=con.prepareStatement("insert into playdetails values(?,?,?)");
            int incremented=detail_id+1;
            prs.setInt(1,incremented);
            prs.setInt(2,playlist_id);
            prs.setInt(3, item_id);
            int rows=prs.executeUpdate();
            if(rows>0){
                System.out.println("Item added to playlist");
            }

        }
        catch(SQLException e){
            System.out.println(e);
        }
    }

    @Override
    public List<PlaylistDetails> shuffledPlaylist(List<PlaylistDetails> playdetails) {
        Collections.shuffle(playdetails);
        return playdetails;
    }
}


