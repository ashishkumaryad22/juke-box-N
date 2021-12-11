package DaoImpl;

import DaoInterface.SongDao;
import ModelClass.Song;
import org.example.hellomaven.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SongDaoImpl implements SongDao {
    List<Song> songs=new ArrayList<>();

    public List<Song> getAllSongs() {
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement prs = con.prepareStatement("select * from song");
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
        catch (SQLException e){
            System.out.println(e);
        }
        return songs;
    }

    @Override
    public String getSongNAmeById(int id) {
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement prs = con.prepareStatement("select song_name from song where song_id=?");
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

    /*@Override
    public int getSongIdbyName(String song_name) {
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement prs = con.prepareStatement("select song_id from song where song_name=?");
            prs.setString(1,song_name);
            ResultSet rs=prs.executeQuery();

            while(rs.next()){
                return rs.getInt(1);
            }
        }
        catch (SQLException e){
            System.out.println(e);
        }
        return 0;
    }*/

    @Override
    public boolean CheckIfSongIdPresent(int id) {
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


}
