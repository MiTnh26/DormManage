package Service.Impl;

import Dao.Impl.ResidentRequestDaoImpl;
import Dao.ResidentRequestDao;
import Entity.Request;
import Service.RequestService;

import java.util.List;

public class RequestServiceImpl implements RequestService {
    private static ResidentRequestDao residentRequestDao = new ResidentRequestDaoImpl();

    @Override
    public List<Request> getAll() {
        return residentRequestDao.findAll();
    }

    @Override
    public List<Request> getAll(String param, int offset, int limit) {
        return residentRequestDao.findAll(param, offset, limit);
    }

    @Override
    public Request getById(Integer id) {
        return residentRequestDao.findById(id);
    }

    @Override
    public void updateStatus(Request request) {
        residentRequestDao.updateStatus(request);
    }

}
