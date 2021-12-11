package DaoImpl;

import DaoInterface.PlaylistDao;
import ModelClass.Playlist;
import org.example.hellomaven.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaylistDaoImpl implements PlaylistDao {



    @Override
    public List<Playlist> getAllPlaylists() {
     List<Playlist> playlists=new ArrayList<>();
        try(Connection con=new DBConnection().getConnection()){
            PreparedStatement prs=con.prepareStatement("select * from playlist");
            ResultSet rs=prs.executeQuery();
            while(rs.next()){
                playlists.add(new Playlist(rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3)));
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return playlists;
    }

    @Override
    public boolean deletebyID(int id) {
        try(Connection con=new DBConnection().getConnection()){
            PreparedStatement prs=con.prepareStatement("delete from playlist where p_id=?");
            prs.setInt(1,id);
            int rows=prs.executeUpdate();
            if(rows>0){
                System.out.println("\nPlaylist removed");
                return true;
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return false;

    }

    @Override
    public void deletebyName(String name) {
        try(Connection con=new DBConnection().getConnection()){
            PreparedStatement prs=con.prepareStatement("delete from playlist where playlist_name=?");
            prs.setString(1,name);
            int rows=prs.executeUpdate();
            if(rows>0){
                System.out.println("Playlist removed");
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }

    @Override
    public int getPlaylistIDByName(String name) {
        int id=0;
        try(Connection con=new DBConnection().getConnection()){
            PreparedStatement prs=con.prepareStatement("select p_id from playlist where playlist_name=?");
            prs.setString(1,name);
            ResultSet rs=prs.executeQuery();
            rs.next();
            id=rs.getInt(1);
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return id;
    }


    @Override
    public void addPlaylist(Playlist p) {
        try(Connection con=new DBConnection().getConnection()){
            PreparedStatement prs=con.prepareStatement("insert into playlist values(?,?,?)");
            prs.setInt(1, p.getP_id());
            prs.setString(2,p.getPlaylist_name());
            prs.setInt(3,p.getUser_id());
            int rows=prs.executeUpdate();
            if(rows>0){
                System.out.println("\nPlaylist created");
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }
    }

    @Override
    public int getPlaylistID() {
        try (Connection con = DBConnection.getConnection()) {
            Statement stmnt = con.createStatement();
            ResultSet rs=stmnt.executeQuery("select p_id from playlist order by p_id desc limit 1");
            rs.next();
            int play_id=rs.getInt(1);
            return play_id;

        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    @Override
    public List<Playlist> getAllPlaylistsByUserId(int user_id) {
        List<Playlist> playlists=new ArrayList<>();
        try(Connection con=new DBConnection().getConnection()){
            PreparedStatement prs=con.prepareStatement("select * from playlist where user_id='"+user_id+"' ");
            ResultSet rs=prs.executeQuery();
            while(rs.next()){
                playlists.add(new Playlist(rs.getInt(1),
                        rs.getString(2),
                        rs.getInt(3)));
            }
        }
        catch(SQLException e){
            System.out.println(e);
        }
        return playlists;
    }
}
