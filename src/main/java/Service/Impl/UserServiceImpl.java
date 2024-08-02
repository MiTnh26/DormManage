package Service.Impl;

import Dao.Impl.UsersDaoImpl;
import Dao.UsersDao;
import Entity.Users;
import Service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private UsersDao usersDao = new UsersDaoImpl();

    @Override
    public List<Users> getALl() {
        List<Users> userList = usersDao.findAll();
        return userList;
    }

    @Override
    public Users getByGmail(String gmail) {
        return usersDao.getByGmail(gmail);
    }

    @Override
    public void deleteById(int id) {
        usersDao.delete(id);
    }

    @Override
    public Users findById(int id) {
        return usersDao.findById(id);
    }

    @Override
    public void addUser(Users user) {
        usersDao.insert(user);
    }

}
