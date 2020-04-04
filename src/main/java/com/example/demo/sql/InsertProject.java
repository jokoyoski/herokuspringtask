package com.example.demo.sql;

import com.example.demo.domain.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Component
public class InsertProject {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall simpleJdbcCall;

    // init SimpleJdbcCall
    @PostConstruct
    void init() {
        // o_name and O_NAME, same
        jdbcTemplate.setResultsMapCaseInsensitive(true);

        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("Insert_Project_Name")
                .returningResultSet("result", new BeanPropertyRowMapper<>(Project.class))
        ;

    }


    public String InsertProject(Project project) {

    //    SqlParameterSource in = new MapSqlParameterSource();
        //     in.addValue()
        MapSqlParameterSource namedParameters = new MapSqlParameterSource();

        namedParameters.addValue("ProjectName", project.getProjectName());
        namedParameters.addValue("projectIdentifier", project.getProjectIdentifier());
        namedParameters.addValue("description", project.getDescription());
        namedParameters.addValue("startDate", project.getStartDate());
        namedParameters.addValue("endDate", project.getStartDate());
        namedParameters.addValue("updatedat", project.getUpdated_At());
        namedParameters.addValue("createdat", project.getCreated_At());




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
