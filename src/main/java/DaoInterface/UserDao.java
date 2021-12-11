package DaoInterface;

import ModelClass.User;

import java.util.List;

public interface UserDao {
    public List<User> getAllUsers();

    public boolean delete(int user_id);

    public void addUser(User user);

    public boolean checkUser(String name, int id);

    public int getuserId();


}
