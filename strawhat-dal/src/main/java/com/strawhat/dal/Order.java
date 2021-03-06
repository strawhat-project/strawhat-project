package com.strawhat.dal;

import com.strawhat.model.Model;
import org.springframework.util.StringUtils;

import java.util.List;

public class Order implements Model {


    private static final long serialVersionUID = -1294107256173289176L;

    /** 排序的字段 */
    private String field;
    /** 排序方式（正序还是反序） */
    private Direction direction;

    public Order() {
        direction = Direction.ASC;
    }
    /**
     * 构造
     * @param field 排序字段
     */
    public Order(String field) {
        this.field = field;
    }

    /**
     * 构造
     * @param field 排序字段
     * @param direction 排序方式
     */
    public Order(String field, Direction direction) {
        this(field);
        this.direction = direction;
    }

    /**
     * 构造
     * @param field 排序字段
     * @param directionStr 排序方式
     */
    public Order(String field, String directionStr) {
        this(field);
        Direction direction = Direction.fromString(directionStr);
        if(direction == null){
            throw new IllegalArgumentException("order param Formatting error");
        }
        this.direction = direction;
    }

    /**
     * @return 排序字段
     */
    public String getField() {
        return this.field;
    }
    /**
     * 设置排序字段
     * @param field 排序字段
     */
    public void setField(String field) {
        this.field = field;
    }

    /**
     * @return 排序方向
     */
    public Direction getDirection() {
        return direction;
    }
    /**
     * 设置排序方向
     * @param direction 排序方向
     */
    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "Order [field=" + field + ", direction=" + direction + "]";
    }

    public String toOrderByString() {
        if(getDirection() == null){
            setDirection(Direction.ASC);
        }
        return field+" "+direction;
    }

    public static String orderToString(List<Order> orders){
        String orderBy = "";
        for(Order order:orders){
            if(StringUtils.isEmpty(order.getField())){
                throw new IllegalArgumentException("sort field is empty");
            }
            orderBy = orderBy + ","+ order.toOrderByString();
        }
        return orderBy.substring(1,orderBy.length());
    }
}
