package club.service;

import club.pojo.AdoptAnimal;
import club.pojo.Apply;
import club.pojo.ReturnVisit;
import com.github.pagehelper.PageInfo;

public interface ReturnVisitService {

    Integer add(ReturnVisit returnVisit);

//    PageInfo<ReturnVisit> all(String adoptTime, Integer pageNum, Integer pageSize);

    int update(ReturnVisit returnVisit);

    PageInfo<ReturnVisit> allReturnVisits(String uName,Integer pageNum,Integer pageSize);

    int del(Integer id);

    //根据id查询进行修改框的数据回显
    ReturnVisit findByid(Integer id);
}
