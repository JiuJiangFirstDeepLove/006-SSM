package club.service;

import club.pojo.Commodity;
import club.pojo.Page;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface CommodityService {

    public boolean addCommodity(Commodity commodity, int a_type);

    public boolean deleteCommodity(int c_id);

    public boolean updateCommodity(Commodity commodity,  int a_type);

    public List<Commodity> queryCommodityByAnimalType(int a_type, int pageNumber, int pageSize);

    public PageInfo<Commodity> queryCommodityByAnimalType(int a_type, int pageNumber);

    List<Commodity> getListByType(int a_type);
    //加入大分类
    public boolean addAnimal(int c_id,int a_type);
    //移除大分类
    //如果成功从分类Mapper中移除了推荐项，则返回 true ，否则捕捉到的异常信息将被打印到控制台，并返回false。
    public boolean remoteAnimal(int c_id,int a_type);

    //按id查询一个商品,需要传入商品ID号作为参数，返回一个Commodity 对象；
    public Commodity queryCommodityById(int c_id);

}
