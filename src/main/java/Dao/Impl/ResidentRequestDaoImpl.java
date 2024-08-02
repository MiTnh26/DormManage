/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao.Impl;

import Dao.GenericDao.Impl.GenericDaoImpl;
import Dao.ResidentRequestDao;
import Entity.Request;
import Mapper.DomResidentMap;
import Mapper.RequestMap;

import java.util.List;


/**
 * @author ADMIN
 */
public class ResidentRequestDaoImpl extends GenericDaoImpl<Request> implements ResidentRequestDao {


    @Override
    public Request findById(Integer id) {
        String sql = "SELECT * FROM Request WHERE requestId = ?";
        return query(sql, new RequestMap(), id).stream().findFirst().orElse(null);
    }

    @Override
    public void update(Request request) {
        String sql = "UPDATE Request SET requestStatus= ?, requestDetail = ?, rollId = ?, requestType = ?, domId = ?, floor = ?, roomName = ?, termId = ?";
        update(sql, request.getRequestStatus(), request.getRequestDetail(), request.getRollId(), request.getRequestType(), request.getDomName(), request.getFloor(),
                request.getRoomName(), request.getTerm());
    }

    @Override
    public List<Request> findAll() {
        String sql = "SELECT * FROM Request ORDER BY requestId desc";
        return query(sql, new RequestMap());
    }

    @Override
    public List<Request> findAll(String param, int offset, int limit) {
        StringBuilder sql = new StringBuilder("SELECT * FROM Request Where rollId LIKE '%");
        sql.append(param).append("%' OR roomName LIKE '%").append(param).append("%' ORDER BY createDate desc ");
        sql.append(" OFFSET ").append(offset).append(" ROWS FETCH NEXT ").append(limit).append(" ROWS ONLY");
        return query(sql.toString(), new RequestMap());
    }

    @Override
    public List<Request> getByRollId(String rollId) {
        String sql = "SELECT * FROM Request WHERE rollId = ? ORDER BY requestId desc";
        return query(sql, new RequestMap(), rollId);
    }

    @Override
    public void createRequestBookRoom(Request request) {
        String sql = "INSERT INTO Request (requestStatus, requestDetail, rollId, requestType, domName, floor, termId, createDate, roomType) VALUES(?,?,?,?,?,?,?,?,?)";
         insert(sql, request.getRequestStatus(), request.getRequestDetail(), request.getRollId(), request.getRequestType(),
                request.getDomName(), request.getFloor(), request.getTerm(), request.getCreateDate(), request.getRoomType());
    }

    @Override
    public void createRequestOther(Request request) {
        String sql = "INSERT INTO Request (requestStatus, requestDetail, rollId, requestType, domName, floor, roomName, termId, createDate, bedId, roomType) VALUES(?,?,?,?,?,?,?,?,?,?,?)";
        insert(sql, request.getRequestStatus(), request.getRequestDetail(), request.getRollId(), request.getRequestType(),
                request.getDomName(), request.getFloor(), request.getRoomName(), request.getTerm(), request.getCreateDate(), request.getBed(), request.getRoomType());
    }

    @Override
    public void createRequestCheckOut(Request request) {
        String sql = "INSERT INTO Request (requestStatus, requestDetail, rollId, requestType, domName, floor, roomName, termId, createDate, bedId, checkOutDate, roomType) VALUES(?,?,?,?,?,?,?,?,?,?,?,?)";
        insert(sql, request.getRequestStatus(), request.getRequestDetail(), request.getRollId(), request.getRequestType(),
                request.getDomName(), request.getFloor(), request.getRoomName(), request.getTerm(), request.getCreateDate(), request.getBed(), request.getCheckOutDate(), request.getRoomType());

    }

    @Override
    public void updateStatus(Request request) {
        String sql = "UPDATE Request SET requestStatus = ? WHERE requestId = ?";
        update(sql, request.getRequestStatus(), request.getRequestId());
    }

    @Override
    public boolean checkByRollNameAndTermAndYearAndType(String rollName, String term, int year, String type) {
        String sql = "SELECT * FROM Request WHERE rollId = ? AND termId = ? AND year(createDate) = ? AND requestType = ? AND requestStatus = 'WAITING'";
        return query(sql, new RequestMap(), rollName, term, year, type).size() > 0;
    }

    @Override
    public long createNewRequest(Request request) {
        StringBuilder sql = new StringBuilder("INSERT INTO Request (requestStatus, requestDetail, rollId, requestType, domId, floor, roomName, termId) ");
        sql.append(" VALUES(?,?,?,?,?,?) ");
        insert(sql.toString(), request.getRequestStatus(), request.getRequestDetail(), request.getRollId(), request.getRequestType(), request.getDomName(), request.getFloor(),
                request.getRoomName(), request.getTerm());
        return 0;
    }


}
