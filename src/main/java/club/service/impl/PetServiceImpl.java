package club.service.impl;

import club.dao.AdoptAnimalMapper;
import club.dao.PetMapper;
import club.pojo.AdoptAnimal;
import club.pojo.Pet;
import club.service.PetService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.baomidou.mybatisplus.mapper.EntityWrapper;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PetServiceImpl implements PetService {

    @Resource
    private PetMapper petMapper;

    @Override
    public PageInfo<Pet> pets(Integer pageNum, Integer pagesize) {
        PageHelper.startPage(pageNum, pagesize);
        List<Pet> pets = petMapper.selectList(null);
        PageInfo<Pet> pageInfo = new PageInfo<Pet>(pets, 3);
        return pageInfo;
    }

    @Override
    public Pet findById(Integer id) {
        return petMapper.selectById(id);
    }

    @Override
    public Integer create(Pet pet) {
        return petMapper.insert(pet);
    }

    @Override
    public PageInfo<Pet> allPet(String petType, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        EntityWrapper<Pet> wrapper = new EntityWrapper<>();
        if (petType != null && !"".equals(petType)) {
            wrapper.like("petType", petType);
        }
        wrapper.in("state", Arrays.asList(0, 1));
        List<Pet> pets = petMapper.selectList(wrapper);

        PageInfo<Pet> pageInfo = new PageInfo<>(pets, 3);
        return pageInfo;
    }

    @Override
    public int update(Pet pet) {
        return petMapper.updateById(pet);
    }

    @Override
    public int del(Integer id) {
        return petMapper.deleteById(id);
    }
}
