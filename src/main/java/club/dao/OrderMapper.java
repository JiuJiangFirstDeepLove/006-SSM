package club.dao;

import club.pojo.Commodity;
import club.pojo.Order;
import club.pojo.OrderItem;
import com.baomidou.mybatisplus.mapper.BaseMapper;

import java.util.List;

public interface OrderMapper extends BaseMapper<Order> {
    public void addOrder(Order order);
    public void addOrderItem(OrderItem orderItem);
    public void deleteOrder(String oid);
    public void deleteOrderItem(String oid);
    public List<Order> queryOrderByUid(int uid);
    public List<OrderItem> queryOrderItemByUidAndOid(String oid);
//    public List<Order> queryOrder(int pageIndex,int pageSize);
//    public int queryCountOfOrder();
//    public List<Order> queryOrderByOstatus(int ostatus,int pageIndex,int pageSize);
//    public int queryCountOfOrderByOstatus(int ostatus);
//    public void updateOrderStatus(String oid,int ostatus);
}
