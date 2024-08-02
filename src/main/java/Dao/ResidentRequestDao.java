/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dao;

import Dao.GenericDao.GenericDao;
import Entity.Request;

import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface ResidentRequestDao  extends GenericDao<Request>{
    long createNewRequest(Request request);
    Request findById(Integer id);

    void update (Request request);

    List<Request> findAll();
    List<Request> findAll(String param, int offset, int limit);

    List<Request> getByRollId(String rollId);

    void createRequestBookRoom(Request request);
    void createRequestOther(Request request);

    void createRequestCheckOut(Request request);

    void updateStatus(Request request);

    boolean checkByRollNameAndTermAndYearAndType(String rollName, String term, int year, String type);
}
