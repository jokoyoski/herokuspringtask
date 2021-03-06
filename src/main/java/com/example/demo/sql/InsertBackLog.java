package com.example.demo.sql;

import com.example.demo.domain.BackLog;
import com.example.demo.domain.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Map;

@Component
public class InsertBackLog {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall simpleJdbcCall;

    // init SimpleJdbcCall
    @PostConstruct
    void init() {
        // o_name and O_NAME, same
        jdbcTemplate.setResultsMapCaseInsensitive(true);

        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("Insert_BackLog")
                .returningResultSet("result", new BeanPropertyRowMapper<>(Project.class))
        ;

    }


    public String InsertBackLog(BackLog backlog) {

        //    SqlParameterSource in = new MapSqlParameterSource();
        //     in.addValue()
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();


        namedParameters.addValue("projectiden", backlog.getProjectIdentifier());
        namedParameters.addValue("pid", backlog.getProjectId());
        namedParameters.addValue("pts", backlog.getPTSequence());


        try {

            Map out = simpleJdbcCall.execute(namedParameters);

            if (out != null) {


                return "";
            }

        } catch (Exception e) {
            // ORA-01403: no data found, or any java.sql.SQLException
            System.err.println(e.getMessage());

            return e.getMessage();
        }
      return "";
    }
}
