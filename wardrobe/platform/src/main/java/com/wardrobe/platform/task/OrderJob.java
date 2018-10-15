package com.wardrobe.platform.task;

import com.wardrobe.common.po.UserOrderInfo;
import com.wardrobe.platform.service.IOrderService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by Administrator on 2016/9/13.
 */
@Component("orderJob")
public class OrderJob {

    private static org.slf4j.Logger logger = LoggerFactory.getLogger(OrderJob.class);

    @Autowired
    private IOrderService orderService;

    @Scheduled(cron = "0 0/1 * * * ?")   //@Scheduled(cron = "0 0 15 * * ?")//每周四12点执行
    public void execute() {
        try {
            logger.info(">>>>>>>>>orderJob execute...");
            logger.info("===================================================================");
            List<UserOrderInfo> overtimeOrders = orderService.getOvertimeOrders();
            orderService.updateOvertimeOrders(overtimeOrders);

            System.out.println("===================================================================");
        } catch (Exception ex) {
            logger.error("orderJob error!", ex);
        }
    }
}
