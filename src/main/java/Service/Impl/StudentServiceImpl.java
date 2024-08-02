package Service.Impl;

import Dao.Impl.StudentDaoImpl;
import Dao.StudentDao;
import Dto.StudentBedDto;
import Entity.Student;
import Service.StudentService;

import java.util.List;

public class StudentServiceImpl implements StudentService {
    private final StudentDao studentDao = new StudentDaoImpl();
    @Override
    public Student getByRollId(String rollId) {
        return studentDao.findById(rollId);
    }

    @Override
    public Student getByGmail(String gmail) {
        return studentDao.findByGmail(gmail);
    }

    @Override
    public List<Student> getAll() {
        return studentDao.findAll();
    }

    @Override
    public List<Student> getAll(String param, int offset, int limit) {
        return studentDao.findAll(param, offset, limit);
    }

    @Override
    public List<StudentBedDto> getByRoomNameAndSemesterAndYear(String roomName, String semester, int year) {
        return studentDao.getByRoomNameAndSemesterAndYear(roomName, semester, year);
    }
    @Override
    public void updateStatus(String rollId, String status) {
        studentDao.updateStatus(rollId, status);
    }

    @Override
    public void updateBalance(String rollId, Long balance) {
        studentDao.updateBalance(rollId, balance);
    }

    @Override
    public void update(Student student) {
        studentDao.update(student);
    }

    @Override
    public List<Student> getByDate() {
        return studentDao.getByDate();
    }
}
