/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mapper;

import Entity.Request;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author ADMIN
 */
public class RequestMap implements RowMapper<Request> {
    public Request mapRow(ResultSet rs) {
        Request.RequestBuilder request = Request.builder();
        try {
            request.requestDetail(rs.getString("requestDetail"))
                    .requestStatus(rs.getString("requestStatus"))
                    .requestType(rs.getString("requestType"))
                    .rollId(rs.getString("rollId"))
                    .domName(rs.getString("domName"))
                    .floor(rs.getInt("floor"))
                    .roomName(rs.getString("roomName"))
                    .term(rs.getString("termId"))
                    .requestId(rs.getInt("requestId"))
                    .createDate(rs.getTimestamp("createDate"))
                    .roomType(rs.getString("roomType"))
                    .bed(rs.getInt("bedId"))
                    .checkOutDate(rs.getTimestamp("checkOutDate"));
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
        return request.build();
    }
}
