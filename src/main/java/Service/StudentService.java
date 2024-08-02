package Service;

import Dto.StudentBedDto;
import Entity.Student;

import java.util.List;

public interface StudentService {
    Student getByRollId(String rollId);
    Student getByGmail(String gmail);
    List<Student> getAll();
    List<Student> getAll(String param, int offset, int limit);
    List<StudentBedDto> getByRoomNameAndSemesterAndYear(String roomName, String semester, int year);

    void updateStatus(String rollId, String status);

    void updateBalance(String rollId, Long balance);

    void update(Student student);

    List<Student> getByDate();

}
