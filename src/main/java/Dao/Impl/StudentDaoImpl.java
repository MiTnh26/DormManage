package Dao.Impl;

import Dao.GenericDao.Impl.GenericDaoImpl;
import Dao.StudentDao;
import Dto.StudentBedDto;
import Entity.Student;
import Mapper.StudentBedDtoMap;
import Mapper.StudentMap;

import java.util.List;

public class StudentDaoImpl extends GenericDaoImpl<Student> implements StudentDao {
    @Override
    public Student findById(String rollId) {
        String sql = "SELECT * FROM Student WHERE rollId = ?";
        return query(sql, new StudentMap(), rollId).stream().findFirst().orElse(null);
    }

    @Override
    public void update(Student student) {
        String sql = "UPDATE Student SET fullName = ?, gender = ?, avatar = ?, status = ?, balance = ?, gmail = ? WHERE rollId = ?";
        update(sql, student.getFullName(), student.getGender(), student.getAvatar(), student.getStudentStatus(),
                student.getBalance(), student.getGmail(), student.getRollId());
    }

    @Override
    public List<Student> findAll() {
        String sql = "SELECT * FROM Student";
        return query(sql, new StudentMap());
    }

    @Override
    public List<Student> findAll(String param, int offset, int limit) {
        StringBuilder sql = new StringBuilder("SELECT * FROM Student Where rollId LIKE '%");
        sql.append(param).append("%' OR fullName LIKE '%").append(param).append("%' ORDER BY rollId ");
        sql.append(" OFFSET ").append(offset).append(" ROWS FETCH NEXT ").append(limit).append(" ROWS ONLY");
        return query(sql.toString(), new StudentMap());
    }

    @Override
    public Student findByGmail(String gmail) {
        String sql = "SELECT * FROM Student WHERE gmail = ?";
        return query(sql, new StudentMap(), gmail).stream().findFirst().orElse(null);
    }

    @Override
    public List<StudentBedDto> getByRoomNameAndSemesterAndYear(String roomName, String semester, int year) {
        String sql = "SELECT s.rollId, s.fullName, s.gender, s.gmail, d.bedId FROM Student s JOIN DomResident d ON s.rollId = d.rollId WHERE d.roomName = ? AND d.termId = ? AND year(checkInDate) = ?";
        return query(sql, new StudentBedDtoMap(), roomName, semester, year);
    }

    @Override
    public void updateStatus(String rollId, String status) {
        String sql = "UPDATE Student SET  status = ? WHERE rollId = ?";
        update(sql, status, rollId);
    }

    @Override
    public void updateBalance(String rollId, Long balance) {
        String sql = "UPDATE Student SET  balance = ? WHERE rollId = ?";
        update(sql, balance, rollId);
    }

    @Override
    public List<Student> getByDate() {
        String sql = "SELECT s.* FROM Student s JOIN DomResident d ON s.rollId = d.rollId WHERE CAST(d.checkOutDate AS DATE) = CAST(GETDATE() AS DATE)";
        return query(sql, new StudentMap());
    }

}
