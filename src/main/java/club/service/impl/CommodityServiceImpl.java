package club.service.impl;

import club.dao.AnimalMapper;
import club.dao.CommodityMapper;
import club.pojo.Admins;
import club.pojo.Animal;
import club.pojo.Commodity;
import club.pojo.Page;
import club.service.CommodityService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommodityServiceImpl implements CommodityService {

    @Autowired
    public CommodityMapper commodityMapper;

    @Autowired
    public AnimalMapper animalMapper;

    @Override
    public boolean addCommodity(Commodity commodity, int a_type) {

        try {
            commodityMapper.insert(commodity);

            animalMapper.insert(Animal.builder()
                    .c_id(commodity.getC_id())
                    .a_type(a_type)
                    .build());
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteCommodity(int c_id) {
        try {
            commodityMapper.deleteCommodity(c_id);

            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());

            return true;
        }
    }

    @Override
    public boolean updateCommodity(Commodity commodity, int a_type) {

        try {
            commodityMapper.updateById(commodity);

            Animal animal = animalMapper.selectOne(Animal.builder().c_id(commodity.getC_id()).build());
            animal.setA_type(a_type);
            animalMapper.updateById(animal);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());

            return true;
        }
    }

    //根据推荐类型a_type、页码pageNumber和页面大小pageSize范围内的商品列表，并返回结果集合List<Commodity>
    @Override
    public List<Commodity> queryCommodityByAnimalType(int a_type, int pageNumber, int pageSize) {

        return animalMapper.queryCommodityByAnimalType(a_type, (pageNumber - 1) * pageSize, pageSize);

    }

    //根据推荐类型rtype和页码pageNumber查询图书列表，并以分页形式返回结果Page
    @Override
    public PageInfo<Commodity> queryCommodityByAnimalType(int a_type, int pageNumber) {

        PageHelper.startPage(pageNumber, 10);
        EntityWrapper<Commodity> wrapper = new EntityWrapper<>();
        if (a_type != 0) {
            EntityWrapper<Animal> animalEntityWrapper = new EntityWrapper<>();
            animalEntityWrapper.eq("a_type", a_type);
            List<Animal> animals = animalMapper.selectList(animalEntityWrapper);
            List<Integer> cIdS = animals.stream().map(Animal::getC_id).collect(Collectors.toList());
            if (cIdS.isEmpty()) {
                return new PageInfo<>();
            }
            wrapper.in("c_id", cIdS);
        }
        List<Commodity> list = commodityMapper.selectList(wrapper);
        return new PageInfo<>(list);

    }

    @Override
    public List<Commodity> getListByType(int a_type) {
        EntityWrapper<Commodity> wrapper = new EntityWrapper<>();
        EntityWrapper<Animal> animalEntityWrapper = new EntityWrapper<>();
        animalEntityWrapper.eq("a_type", a_type);
        List<Animal> animals = animalMapper.selectList(animalEntityWrapper);
        List<Integer> cIdS = animals.stream().map(Animal::getC_id).collect(Collectors.toList());
        if (cIdS.isEmpty()) {
            return new ArrayList<>();
        }
        wrapper.in("c_id", cIdS);
        return commodityMapper.selectList(wrapper);
    }


    // 从指定的书籍bid中删除推荐类型rtype，返回boolean类型值，表示操作是否成功。
    @Override
    public boolean remoteAnimal(int c_id, int a_type) {
        try {
            // 调用recommendMapper对象的方法删除推荐信息
            animalMapper.removeAnimalCommodity(c_id, a_type);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    // 向指定的书籍bid中添加推荐类型rtype，返回boolean类型值，表示操作是否成功。
    @Override
    public boolean addAnimal(int c_id, int a_type) {
        try {
            animalMapper.addAnimal(c_id, a_type);// 调用recommendMapper对象的方法添加推荐信息
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }


    public Commodity queryCommodityById(int c_id) {
        return commodityMapper.queryCommodityById(c_id);
    }


}
