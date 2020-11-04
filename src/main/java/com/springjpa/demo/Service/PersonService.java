package com.springjpa.demo.Service;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.springjpa.demo.entity.Person;
import com.springjpa.demo.entity.QPerson;
import com.springjpa.demo.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class PersonService {
    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    JPAQueryFactory jpaQueryFactory;

    public List getPerson() {

        QPerson qPerson=QPerson.person;
        return personRepository.findAll(qPerson.age.eq(1));
    }


    public List<Map<String, Object>> getPerson1() {
       String sql ="select person0_.id as id1_0_, person0_.address as address2_0_, person0_.age as age3_0_, person0_.name as name4_0_ from person person0_";
              List<Map<String, Object>> list= jdbcTemplate.queryForList(sql);
return list;
    }
}
