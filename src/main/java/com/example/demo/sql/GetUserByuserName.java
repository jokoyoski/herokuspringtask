package com.example.demo.sql;

import com.example.demo.domain.Project;
import com.example.demo.domain.User;
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
public class GetUserByuserName {
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
                .withProcedureName("Get_user_by_UserName")
                .returningResultSet("result", new BeanPropertyRowMapper<>(User.class))
        ;

    }





    public User getUserbyUserName(String username) {

        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("usr", username);

        Optional result = Optional.empty();

        try {

            Map out = simpleJdbcCall.execute(in);

            if (out!=null) {
                var result2 = ((List)out.get("result")).get(0);

                return  (User)result2;




            }

        } catch (Exception e) {
            // ORA-01403: no data found, or any java.sql.SQLException
            System.err.println(e.getMessage());
        }

        return new User();
    }
}
