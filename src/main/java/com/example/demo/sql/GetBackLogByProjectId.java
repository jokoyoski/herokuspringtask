package com.example.demo.sql;

import com.example.demo.domain.BackLog;
import com.example.demo.domain.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class GetBackLogByProjectId {


    //  private static final Logger log = LoggerFactory.getLogger(StoredProcedure1.class);

    // @Autowired
    // @Qualifier("jdbcBookRepository")
    // private BookRepository bookRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SimpleJdbcCall simpleJdbcCall;

    // init SimpleJdbcCall
    @PostConstruct
    void init() {
        // o_name and O_NAME, same
        jdbcTemplate.setResultsMapCaseInsensitive(true);

        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("Get_BackLog_ByProjectId")
                .returningResultSet("result", new BeanPropertyRowMapper<>(Project.class))
        ;

    }





    public List<BackLog>GetBackLogByProjectId(Long projectId) {

        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("projectid", projectId);

        Optional result = Optional.empty();

        try {

            Map out = simpleJdbcCall.execute(in);

            if (out!=null) {
                var result2 = ((List)out.get("result")).size();
                if(result2==0){
                    return new ArrayList<BackLog>();
                }
                List<BackLog> result1 = ((List<BackLog>) out.get("result"));
                //  Project project = new Project();

                return result1;
            }

        } catch (Exception e) {
            // ORA-01403: no data found, or any java.sql.SQLException
            System.err.println(e.getMessage());
        }

        return new ArrayList<BackLog>();
    }
}
