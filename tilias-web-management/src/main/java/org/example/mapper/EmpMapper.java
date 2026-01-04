package org.example.mapper;

import org.apache.ibatis.annotations.*;
import org.example.pojo.Emp;
import org.example.pojo.EmpQueryParam;
import org.example.pojo.LoginInfo;

import java.util.List;
import java.util.Map;

@Mapper
public interface EmpMapper {

    //原始分页查询代码
//    @Select("select count(*) from emp left join dept on emp.dept_id = dept.id")
//    public long count();
//
//    @Select("select emp.*,dept.name deptName from emp left join dept on emp.dept_id = dept.id order by emp.update_time desc limit #{start},#{PageSize}")
//    public List<Emp> list(Integer start, Integer PageSize);


    //select语句最后不能加; 因为会把分页工具后面的limit给注释掉,无法进行分页
//    @Select("select emp.*,dept.name deptName from emp left join dept on emp.dept_id = dept.id " +
//            "where emp.name like '%#{}%' and emp.gender = #{} and emp.entry_date between '#{}' and '#{}'  " +
//            "order by emp.update_time desc ")


//    public List<Emp> list(String name, Integer gender,
//                          @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate begin,
//                          @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate end);


    List<Emp> list(EmpQueryParam empQueryParam);


    @Options(useGeneratedKeys = true, keyProperty = "id")//获取到生成的主键
    @Insert("insert into emp(username, name, gender, phone, job, salary, image, entry_date, dept_id, create_time, update_time) " +
            "values (#{username},#{name},#{gender},#{phone},#{job},#{salary},#{image},#{entryDate},#{deptId},#{createTime},#{updateTime})")
    void insert(Emp emp);


    //根据id删除员工的基本信息
    void deleteByIds(List<Integer> ids);


    //根据id获取员工的基本信息
    Emp getinfo(Integer id);

    //根据id修改员工的基本信息
    void updateById(Emp emp);

    //根据pos(job名字)对应num(job人数)使用List<Map>

    @MapKey("pos")
    List<Map<String, Object>> countEmpJobData();

    @MapKey("name")
    List<Map<String, Integer>> countEmpGenderData();

    List<Emp> jieKou();

    LoginInfo LoginInfo(Emp emp);
}



