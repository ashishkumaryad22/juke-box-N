package ModelClass;

public class User {
    int user_id;
    String user_name;

    public User(int user_id, String user_name){
        this.user_id=user_id;
        this.user_name=user_name;
    }

    public int getUser_id(){
        return user_id;
    }
    public String getUser_name(){
        return user_name;
    }
    public void setUser_id(int id){
        this.user_id=id;
    }
    public void setUser_name(String name){
        this.user_name=name;
    }

    public String toString(){
        return "User id: "+user_id+", User Name: "+user_name;
    }
}
