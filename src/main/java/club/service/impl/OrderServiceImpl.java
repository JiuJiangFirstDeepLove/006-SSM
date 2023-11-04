package club.service.impl;

import club.dao.CommodityMapper;
import club.dao.OrderMapper;
import club.pojo.*;
import club.service.OrderService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    public OrderMapper orderMapper;
    @Autowired
    public CommodityMapper commodityMapper;

    @Override
    public boolean addOrder(Order order) {
//        年月日时分秒好毫秒值
        String oid = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        order.setOid(oid);
        try {
            orderMapper.addOrder(order);
            order.getItemMap().forEach((key, value) -> {
                value.setOid(oid);
                orderMapper.addOrderItem(value);
            });
            return true;
        } catch (Exception e) {
            orderMapper.deleteOrder(oid);
            orderMapper.deleteOrderItem(oid);
            System.out.println(e.getMessage());
            return false;
        }


    }

    @Override
    public List<Order> queryOrderByUid(int uid) {
        List<Order> orders = new ArrayList<>();
        try {
            orders = orderMapper.queryOrderByUid(uid);
            for (int i = 0; i < orders.size(); i++) {
                orders.get(i).setItemList(orderMapper.queryOrderItemByUidAndOid(orders.get(i).getOid()));
                for (int j = 0; j < orders.get(i).getItemList().size(); j++) {
                    orders.get(i).getItemList().get(j).setCommodity(commodityMapper.queryCommodityById(orders.get(i).getItemList().get(j).getC_id()));
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return orders;
    }

    @Override
    public PageInfo<Order> queryOrders(int pageNumber) {

        PageHelper.startPage(pageNumber, 10);

        EntityWrapper orderEntityWrapper = new EntityWrapper<>();
        List list = orderMapper.selectList(orderEntityWrapper);

        PageInfo<Order> pageInfo = new PageInfo<>(list);
        pageInfo.setList(pageInfo.getList()
                .stream()
                .peek(l -> l.setItemList(orderMapper.queryOrderItemByUidAndOid(l.getOid())
                        .stream()
                        .peek(orderItem -> orderItem.setCommodity(commodityMapper.selectById(orderItem.getC_id())))
                        .collect(Collectors.toList())))
                .collect(Collectors.toList()));
        return pageInfo;
    }

//    @Override
//    public boolean updateOrderStatus(String oid, int ostatus) {
//        try {
//            orderMapper.updateOrderStatus(oid, ostatus);
//            return true;
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            return false;
//        }
//    }

    @Override
    public boolean deleteOrderByOid(String oid) {
        try {
            orderMapper.deleteOrderItem(oid);
            orderMapper.deleteOrder(oid);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }

    }
}
