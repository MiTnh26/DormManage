package Dao.Impl;

import Dao.GenericDao.Impl.GenericDaoImpl;
import Dao.UsersDao;
import Entity.Users;
import Mapper.UsersMap;

import java.util.List;

public class UsersDaoImpl extends GenericDaoImpl<Users> implements UsersDao {

    @Override
    public Users findById(Integer id) {
        String sql = "SELECT * FROM Users WHERE userId =?";
        return query(sql, new UsersMap(), id).stream().findFirst().orElse(null);
    }

    @Override
    public Users getByGmail(String gmail) {
        String sql = "SELECT * FROM Users WHERE gmail = ?";
        return query(sql, new UsersMap(), gmail).stream().findFirst().orElse(null);
    }

    @Override
    public void update(Users users) {
        String sql = "UPDATE Users SET gmail = ?, password = ?, roleId = ?, gender = ?, fullName = ? WHERE userId = ?";
        update(sql, users.getGmail(), users.getPassword(), users.getRoleId(), users.getGender(), users.getFullName(), users.getUserId());
    }

    @Override
    public List<Users> findAll() {
        String sql = "SELECT * FROM Users";
        return query(sql, new UsersMap());
    }

    @Override
    public void insert(Users user) {
        String sql = "INSERT INTO Users(fullName, password, gender, gmail, roleId) VALUES(?,?,?,?,?)";
        insert(sql, user.getFullName(), user.getPassword(), user.getGender(), user.getGmail(), user.getRoleId());
    }
    public void delete(Integer id){
        String sql = "DELETE FROM Users WHERE userId = ?";
        delete(sql, id);
    }

    // thứ tự các trường insert đang sai, chưa có status, chuwa co truong password. them cao

//    public static void main(String[] args) {
//        UsersDaoImpl dao = new UsersDaoImpl();
//        System.out.println(dao.findAll());
//    }
}
