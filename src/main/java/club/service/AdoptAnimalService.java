package club.service;

import club.pojo.Admins;
import club.pojo.AdoptAnimal;
import club.pojo.Order;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface AdoptAnimalService {
    Integer create(AdoptAnimal adoptAnimal);
    PageInfo<AdoptAnimal> all(String adoptTime,Integer pageNum, Integer pageSize);
    int update(Integer id, Integer state);
    PageInfo<AdoptAnimal> allAdoptAnimal(String userName,Integer pageNum,Integer pageSize, Integer state);
    AdoptAnimal findById(Integer id);
}
