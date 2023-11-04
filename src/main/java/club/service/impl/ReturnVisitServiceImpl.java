package club.service.impl;

import club.dao.AdoptAnimalMapper;
import club.dao.ReturnVisitMapper;
import club.pojo.Admins;
import club.pojo.Apply;
import club.pojo.ReturnVisit;
import club.service.ReturnVisitService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class ReturnVisitServiceImpl implements ReturnVisitService {

    @Resource
    private ReturnVisitMapper returnVisitMapper;


    @Override
    public Integer add(ReturnVisit returnVisit) {
        return returnVisitMapper.insert(returnVisit);
    }

    @Override
    public ReturnVisit findByid(Integer id) {
        return returnVisitMapper.selectById(id);
    }


    @Override
    public int update(ReturnVisit returnVisit) {

        return returnVisitMapper.updateById(returnVisit);
    }

    @Override
    public PageInfo<ReturnVisit> allReturnVisits(String uName, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum,pageSize);
        EntityWrapper wrapper = new EntityWrapper();
        if(uName != null && !"".equals(uName)){
            wrapper.like("uName",uName);
        }
        List list = returnVisitMapper.selectList(wrapper);
        PageInfo<ReturnVisit> pageInfo = new PageInfo<>(list);
        return pageInfo;
    }

    @Override
    public int del(Integer id) {

        return returnVisitMapper.deleteById(id);
    }
}
