package database.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * 数据源获取，模拟简单的从库负载均衡
 */
@Component
public class ManagementCenter {

    @Autowired
    @Qualifier("master")
    DataSource masterDataSource;
    @Autowired
    @Qualifier("slave1")
    DataSource slave1DataSource;

    int slaveIndex = 1;

    public DataSource getDefaultDataSource()
    {
        return masterDataSource;
    }


    public DataSource getSlaveDataSource()
    {
            return slave1DataSource;
    }
}
