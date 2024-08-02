package Service;



import Entity.Users;

import java.util.List;

public interface UserService {
    List<Users> getALl();

    void addUser(Users user);
    Users getByGmail(String gmail);

    void deleteById(int id);

    Users findById(int id);
}
