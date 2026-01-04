package org.example.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import io.jsonwebtoken.impl.crypto.JwtSigner;
import lombok.extern.slf4j.Slf4j;
import org.example.mapper.EmpExprMapper;
import org.example.mapper.EmpMapper;
import org.example.pojo.*;
import org.example.service.EmpLogService;
import org.example.service.EmpService;
import org.example.utils.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

@Service
@Slf4j
public class EmpServiceImpl implements EmpService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private EmpExprMapper empExprMapper;

    @Autowired
    private EmpLogService empLogService;

    @Override//
    public PageResult<Emp> page(EmpQueryParam empQueryParam) {

        //设置分页参数;        用了PageHelper SQL语句后面不能有;
        //PageHelper只执行第一个分页查询语句
        PageHelper.startPage(empQueryParam.getPage(),empQueryParam.getPageSize());

        List<Emp> empList = empMapper.list(empQueryParam);
//        List<Emp> empList = empMapper.list(); 第二句select操作不能执行,

        Page<Emp> p = (Page<Emp>) empList;//empList数据类型就是Page,多态的体现
        return new PageResult<>(p.getTotal(), p.getResult());

    }

    //事务管理的注解-默认是出现运行时异常RunTimeException才会回滚
    @Transactional(rollbackFor = {Exception.class})//Exception.class出现所有异常都会回滚
    @Override//插入员工基本信息
    public void save(Emp emp) {
        try {
            emp.setCreateTime(LocalDateTime.now());
            emp.setUpdateTime(LocalDateTime.now());

            empMapper.insert(emp);//插入员工基本信息

            //批量插入员工工作经历信息
            List<EmpExpr> exprList = emp.getExprList();
            if(!CollectionUtils.isEmpty(exprList)){
                exprList.forEach(empExpr -> {
                    empExpr.setEmpId(emp.getId());
                });
                empExprMapper.insertBatch(exprList);
            }
        } finally {
            //记录操作日志
            EmpLog empLog = new EmpLog(null,LocalDateTime.now(),"新增员工: "+emp);
            empLogService.insertLog(empLog);
        }
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void delete(List<Integer> ids) {
        //根据id删除员工基本信息
        empMapper.deleteByIds(ids);
        //批量删除员工工作经历
        empExprMapper.deleteByEmpIds(ids);
    }

    @Override
    public Emp getinfo(Integer id) {
        //根据id查询回显
        return empMapper.getinfo(id);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public void update(Emp emp) {
        //根据Id修改员工的基本数据
        emp.setUpdateTime(LocalDateTime.now());
        empMapper.updateById(emp);

        //根据Id修改员工的工作经历信息,先删再插
        //1 先删除,用ID查询删除信息
        //System.out.println("0000000000"+Arrays.asList(emp.getId())+"删除员工工作经历id----------------------------------");
        empExprMapper.deleteByEmpIds(Arrays.asList(emp.getId()));
        //2再插入
        List<EmpExpr> exprList = emp.getExprList();
        if(!CollectionUtils.isEmpty(exprList)){
            exprList.forEach(empExpr -> empExpr.setEmpId(emp.getId()));
                empExprMapper.insertBatch(exprList);
        }
    }

    @Override
    public List<Emp> list() {
        return empMapper.jieKou();
    }

    @Override
    public LoginInfo login(Emp emp) {
        LoginInfo e = empMapper.LoginInfo(emp);

        if(e != null ){
            log.info("登陆成功,员工信息: {}",e);

            //生成jwt令牌
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", e.getId());
            claims.put("username", e.getUsername());
            String jwt = JwtUtils.generateJwt(claims);


            return new LoginInfo(e.getId(),e.getUsername(),e.getName(),jwt);
        }
        return null;
    }


//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize) {
    //原始分页查询
//        //调用Mapper接口中的总记录数
//        Long total = empMapper.count();
//
//        //调用Mapper接口,查询结果列表
//        Integer start = (page - 1) * pageSize;
//        List<Emp> rows = empMapper.list(start, pageSize);
//
//        return new PageResult<Emp>(total,rows);



//    @Override
//    public PageResult<Emp> page(Integer page, Integer pageSize,String name, Integer gender,
//                                LocalDate begin,
//                                LocalDate end) {
//
//        //设置分页参数;        用了PageHelper SQL语句后面不能有;
//        PageHelper.startPage(page, pageSize);//PageHelper只执行第一个分页查询语句
//
//
//        List<Emp> empList = empMapper.list(name,gender,begin,end);
////        List<Emp> empList = empMapper.list(); 第二句select操作不能执行,
//
//        Page<Emp> p = (Page<Emp>) empList; //empList数据类型就是Page,多态的体现
//        return new PageResult<Emp>(p.getTotal(), p.getResult());
//
//    }

}
