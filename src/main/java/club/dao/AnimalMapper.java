package club.dao;

import club.pojo.Animal;
import club.pojo.Commodity;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

public interface AnimalMapper extends BaseMapper<Animal> {

    //该方法的主要功能是通过调用数据库API查询符合条件的商品列表
    //其中a_type参数用于筛选动物类型，pageIndex和pageSize参数用于分页查询商品。
    //通过返回一个List<Commodity>类型的结果集，将查询到的商品数据返回给调用方。
    //按动物种类大分类
    public List<Commodity> queryCommodityByAnimalType(int a_type, int pageIndex, int pageSize);

    //根据动物种类查询总数
    public int queryCountOfCommodityByAnimalType(int a_type);

    public int queryCommodityByatypeAndcid(int a_type,int c_id);
    //移除推荐商品
    public void removeAnimalCommodity(int c_id,int a_type);

    public void addAnimal(int c_id,int a_type);//添加推荐书籍
//

//
//    public void removeRecommend(int bid);
}
