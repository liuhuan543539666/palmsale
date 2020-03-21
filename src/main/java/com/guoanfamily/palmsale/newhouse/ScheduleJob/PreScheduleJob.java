package com.guoanfamily.palmsale.newhouse.ScheduleJob;

import com.guoanfamily.palmsale.newhouse.entity.PCCount;
import com.guoanfamily.palmsale.newhouse.repository.PCCountRepository;
import com.guoanfamily.palmsale.newhouse.service.ObjectDaoServiceI;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


import javax.persistence.EntityManager;
import javax.persistence.Query;
import java.util.*;


/**
 * Created by Administrator on 2017/5/22.
 */
@Component
public class PreScheduleJob {
    @Autowired
    private final ObjectDaoServiceI objectDaoService;
    private final PCCountRepository repository;
    public PreScheduleJob(ObjectDaoServiceI objectDaoService, PCCountRepository repository) {
        this.objectDaoService = objectDaoService;
        this.repository = repository;
    }
    @Scheduled(cron="0 0 0 * * ?")//每天零点执行
    public void doCountPreSchedule(){
        List<String> list = new ArrayList<>();
        EntityManager entityManager =objectDaoService.getEntityManager();
        Map<String,String> map = new HashMap<>();
        // 1、查询待分配客户
        String sql1 = "SELECT count(id) from  n_customsallocate where userid is null and agentid is not null ";
        //2、查询待处理入会订单
        String sql2 = "SELECT COUNT(*) from  cust_order where orderstate='11'";
        //3、待确认预约带看
        String sql3="SELECT COUNT(*) from  bookscanbuilding where datatype='1' and state='0'";
        //4、待处理客户预约
        String sql4 = "SELECT count(*)from  bookscanbuilding where datatype='0' and visitstate='0'";
        //5、待处理客户逾期重新分配
        //6、待确认佣金拆账比例
        //7、待处理客户认购审批
        String sql7 = "SELECT COUNT(*) from salesmanageaudit where auditstate='0' and classtype='cust_order'";
        //8、待处理入会退款
        //9、待处理结佣审批
        String  sql9 ="SELECT count(*) from b_paymoney where paystate='1'";
        map.put("pCustAllot",sql1);
        map.put("pLvjuOrder",sql2);
        map.put("pConfirmVistit",sql3);
        map.put("pCustBook",sql4);
        map.put("pPayAudit",sql7);
        doExecuteSql(map);
    }
    //执行sql
    public void doExecuteSql(Map<String,String> map){
        List<String> list = new ArrayList<>();
        EntityManager entityManager =objectDaoService.getEntityManager();
        PCCount pcCount = new PCCount();
        pcCount.setId(UUID.randomUUID().toString());
        pcCount.setCreate_date(new Date());

        for (Map.Entry<String,String> entry : map.entrySet()) {
            String type = entry.getKey();//类型
            String sql = entry.getValue();//sql

            Query query= entityManager.createNativeQuery(sql);
            list= query.getResultList();
            if(list.size()>0){
                Object object = list.get(0);
                int count = Integer.parseInt(object.toString());
                if(type.equals("pCustAllot")){
                    pcCount.setP_cust_allot(count);
                }else if(type.equals("pLvjuOrder")){
                    pcCount.setP_lvju_order(count);
                }else if(type.equals("pConfirmVistit")){
                    pcCount.setP_confirm_vistit(count);
                }else if(type.equals("pCustBook")){
                    pcCount.setP_cust_book(count);
                }else if(type.equals("pPayAudit")){
                    pcCount.setP_pay_audit(count);
                }else{}
                repository.deleteAll();
                repository.save(pcCount);
            }
        }
    }
}
