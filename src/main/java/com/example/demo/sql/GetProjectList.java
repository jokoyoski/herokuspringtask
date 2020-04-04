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
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class GetProjectList {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall simpleJdbcCall;

    // init SimpleJdbcCall
    @PostConstruct
    void init() {
        // o_name and O_NAME, same
        jdbcTemplate.setResultsMapCaseInsensitive(true);

        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("Get_All_Project")
                .returningResultSet("result", new BeanPropertyRowMapper<>(Project.class))
        ;

    }


    public List<Project> GetProject() {


        try {

            Map out = simpleJdbcCall.execute();

            if (out != null) {

                var result2 = ((List) out.get("result")).size();
                if (result2 == 0) {
                    return new ArrayList<Project>();
                }
                List<Project> result1 = ((List<Project>) out.get("result"));
                //  Project project = new Project();

                return result1;

            }

        } catch (Exception e) {
            // ORA-01403: no data found, or any java.sql.SQLException
            System.err.println(e.getMessage());


        }
        return new ArrayList<Project>();
    }
}
