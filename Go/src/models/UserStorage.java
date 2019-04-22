package models;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class UserStorage extends MainStorage {
    public static ArrayList<User> newUserSinceLastLogin(LocalDateTime lastLoggedIn) throws Exception {
        ArrayList<User> result = new ArrayList<User>();
        for (int i = 0; i < users.size(); i++)
        {
            if (users.get(i).getJoinDate().isAfter(lastLoggedIn)){
                result.add(users.get(i));
            }
        }
        return result;
    }
}
