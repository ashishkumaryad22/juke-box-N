package DaoImpl;

import DaoInterface.EpisodeDao;
import ModelClass.Episode;
import org.example.hellomaven.DBConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EpisodeDaoImpl implements EpisodeDao {
    List<Episode> episodes=new ArrayList<>();


    @Override
    public List<Episode> getAllEpisodes() {
        try(Connection con=new DBConnection().getConnection()){
            PreparedStatement prs=con.prepareStatement("select * from episode");
            ResultSet rs=prs.executeQuery();
            while(rs.next()){
                episodes.add(new Episode(rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)));
            }
        }
        catch (SQLException e){
            System.out.println(e);
        }
        return episodes;
    }

    @Override
    public List<Episode> getAllEpisodesbypID(int p_id) {
        try(Connection con=new DBConnection().getConnection()){
            PreparedStatement prs=con.prepareStatement("select * from episode where pod_id=?");
            prs.setInt(1,p_id);
            ResultSet rs=prs.executeQuery();
            while(rs.next()){
                episodes.add(new Episode(rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)));
            }
        }
        catch (SQLException e){
            System.out.println(e);
        }
        return episodes;
    }

    @Override
    public List<Episode> getAllEpisodesByName(String name) {
        try(Connection con=new DBConnection().getConnection()){
            PreparedStatement prs=con.prepareStatement("select * from episode where pod_id in(select pod_id from podcast where pod_name=?)");
            prs.setString(1,name);
            ResultSet rs=prs.executeQuery();
            while(rs.next()){
                episodes.add(new Episode(rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)));
            }
        }
        catch (SQLException e){
            System.out.println(e);
        }
        return episodes;
    }

    @Override
    public List<Episode> getAllEpisodesByDate(String podcast) {
        try(Connection con=new DBConnection().getConnection()){
            PreparedStatement prs=con.prepareStatement("select * from episode where pod_id in (select pod_id from podcast where pod_name=?) order by upload_date");
            prs.setString(1,podcast);
            ResultSet rs=prs.executeQuery();
            while(rs.next()){
                episodes.add(new Episode(rs.getInt(1),
                        rs.getInt(2),
                        rs.getString(3),
                        rs.getString(4),
                        rs.getString(5)));
            }
        }
        catch (SQLException e){
            System.out.println(e);
        }
        return episodes;
    }
}
