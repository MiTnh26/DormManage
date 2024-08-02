package Mapper;

import Entity.Term;

import java.sql.ResultSet;
import java.sql.SQLException;

public class TermMap implements RowMapper<Term> {
    @Override
    public Term mapRow(ResultSet rs) {
        Term.TermBuilder term = Term.builder();
        try {
            term.termId(rs.getInt("termId"))
                    .termName(rs.getString("termName"));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return term.build();
    }
}
