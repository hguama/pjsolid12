package com.solid12.solid12.adapters.dbrelational.jdbc;

import com.solid12.solid12.domain.Citizen;
import com.solid12.solid12.domain.Document;
import com.solid12.solid12.domain.ICitizenManagement;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository("JDBCService")
public class JDBCAdapter implements ICitizenManagement {

    private final JdbcTemplate jdbcTemplate;



    public JDBCAdapter(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Citizen createCitizen(Citizen citizen) {
        String sqlQuery = "INSERT INTO citizen_entity (name, document_type, document_number) VALUES(?, ?, ?)";

        int queryResult = jdbcTemplate.update(sqlQuery, citizen.getName(), citizen.getCitizenDocument()
                                                                                  .getDocumentType(), citizen.getCitizenDocument()
                                                                                                             .getDocumentNumber());
        return queryResult == 1 ? citizen : null;
    }

    @Override
    public int updateCitizen(Citizen citizen) {
        String sqlQuery = "UPDATE citizen_entity SET name = ?, document_type = ?, document_number = ? WHERE id = ? ";
        return jdbcTemplate.update(sqlQuery, citizen.getName(), citizen.getCitizenDocument()
                                                                       .getDocumentType(), citizen.getCitizenDocument()
                                                                                                  .getDocumentNumber(), citizen.getId());

    }

    @Override
    public int deleteCitizen(int idCitizen) {
        String sqlQuery = "DELETE from citizen_entity WHERE id = ?";

        return jdbcTemplate.update(sqlQuery, idCitizen);
    }

    @Override
    public List<Citizen> getCitizens() {

        String sqlQuery = "Select * from citizen_entity";

        return jdbcTemplate.query(sqlQuery, new PersonRowMapper());
    }

    private static class PersonRowMapper implements RowMapper<Citizen> {

        @Override
        public Citizen mapRow(ResultSet rs, int rowNum) throws SQLException {

            Citizen citizen = new Citizen(rs.getString("name"),
                    new Document(rs.getString("document_type"),
                            rs.getString("document_number")), rs.getInt("id"));

            return citizen;
        }
    }
}
