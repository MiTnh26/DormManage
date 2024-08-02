/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Entity.Request;

import java.util.List;

/**
 *
 * @author ADMIN
 */
public interface ResidentRequestService {
   
    Request findById(Integer id);
    
    long createNewRequest(Request request);

    void update(Request request);

    List<Request> findAll();

    List<Request> getByRollId(String rollId);

    void createRequestBookRoom(Request request);
    void createRequestOther(Request request);

    boolean checkRequestByRollNameAndTermAndYearAndType(String roomName, String term, int year, String type);

}
