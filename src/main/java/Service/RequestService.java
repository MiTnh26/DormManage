package Service;


import Entity.Request;

import java.util.List;

public interface RequestService {
    List<Request> getAll();
    List<Request> getAll(String param, int offset, int limit);
    Request getById(Integer id);

    void updateStatus(Request request);
}
