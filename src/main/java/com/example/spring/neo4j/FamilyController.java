package com.example.spring.neo4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
public class FamilyController {
    @Autowired
    JdbcOperations jdbcOperations;
    @RequestMapping(value="/api/v1/member", method = RequestMethod.PUT)
    public ResponseEntity addMember(@RequestBody  Map<String,Object> request ) {
        String name= request.get("name").toString();
        String gender= request.get("gender").toString();
        String ADD_FAMILY_MEMBER_QUERY="CREATE (a:Person{name:?,gender:?})";
        jdbcOperations.update(ADD_FAMILY_MEMBER_QUERY,new Object[]{name,gender});
        Map<String,Object> payload= new HashMap<>();
        Map<String,Object> response= new HashMap<>();
        payload.put("messgae", "member added.");
        response.put("success",true);
        response.put("payload",payload);
        return ResponseEntity.ok(response);

    }
}
