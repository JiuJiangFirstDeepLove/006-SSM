package club.service;

import club.pojo.Order;
import club.pojo.Page;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface OrderService {
    public boolean addOrder(Order order);
    public List<Order> queryOrderByUid(int uid);
//    public Page queryOrders(int status, int pageNumber);

    public PageInfo<Order> queryOrders(int pageNumber);
//    public boolean updateOrderStatus(String oid,int ostatus);
    public boolean deleteOrderByOid(String oid);
}
