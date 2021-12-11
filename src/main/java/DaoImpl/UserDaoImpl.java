package DaoImpl;

import DaoInterface.UserDao;
import ModelClass.User;
import org.example.hellomaven.DBConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {
    List<User> users = new ArrayList<>();

    public void addUser(User user) {
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement prs = con.prepareStatement("insert into user values(?,?)");
            prs.setInt(1, user.getUser_id());
            prs.setString(2, user.getUser_name());
            int rows = prs.executeUpdate();
            if (rows > 0) {
                System.out.print("\nUser account created successfully");
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    }

    @Override
    public boolean checkUser(String name, int id) {
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement prs = con.prepareStatement("select user_id from user where user_name=?");
            prs.setString(1, name);
            ResultSet rs = prs.executeQuery();
            while(rs.next()) {
                int idd = rs.getInt(1);
                if (idd == id) {
                    return true;
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }

    @Override
    public int getuserId() {
        try (Connection con = DBConnection.getConnection()) {
            Statement stmnt = con.createStatement();
            ResultSet rs=stmnt.executeQuery("select user_id from user order by user_id desc limit 1");
            rs.next();
            int userid=rs.getInt(1);
            return userid;

        } catch (SQLException e) {
            System.out.println(e);
        }
        return 0;
    }

    public List<User> getAllUsers() {
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement prs = con.prepareStatement("select * from user");
            ResultSet rs = prs.executeQuery();
            while (rs.next()) {
                users.add(new User(rs.getInt(1), rs.getString(2)));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return users;

    }

    @Override
    public boolean delete(int user_id) {
        try (Connection con = DBConnection.getConnection()) {
            PreparedStatement prs = con.prepareStatement("delete from user where user_id=?");
            prs.setInt(1, user_id);
            int rows = prs.executeUpdate();
            if (rows > 0) {
                System.out.print("\nUser deleted successfully");
                return true;
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return false;
    }
}
