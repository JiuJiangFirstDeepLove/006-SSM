package club.dao;

import club.pojo.Admins;
import club.pojo.Commodity;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CommodityMapper extends BaseMapper<Commodity> {
    //添加
    public void addCommodity(Commodity commodity);
    //删除
    public void deleteCommodity(int c_id);
    //修改
    public void updateCommodity(Commodity commodity);
    //按id查到商品,需要传入商品ID号作为参数，返回一个Commodity 对象；
    public Commodity queryCommodityById(int c_id);
    //查询全部 可以分页，需要指定当前页码（pageIndex）和每页显示数量（pageSize）
    public List<Commodity> queryCommodity(int pageIndex, int pageSize);
    //查询所有商品的总数，返回一个整数值；
    public int queryCountOfCommodity();


}
