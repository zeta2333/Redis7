package usts.pycro.redis7.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import usts.pycro.redis7.service.OrderService;

import javax.annotation.Resource;

/**
 * @author Pycro
 * @version 1.0
 * 2023-04-07 9:26 AM
 */
@RestController
@Slf4j
@Api(tags = "订单接口")
public class OrderController {

    @Resource
    private OrderService orderService;

    @ApiOperation("新增订单")
    @RequestMapping(value = "/order/add", method = RequestMethod.POST)
    public void addOrder() {
        orderService.addOrder();
    }

    @ApiOperation("按照keyId查询订单")
    @RequestMapping(value = "/order/{keyId}", method = RequestMethod.GET)
    public String getOrderById(@PathVariable Integer keyId) {
        return orderService.getOrderById(keyId);
    }
}
