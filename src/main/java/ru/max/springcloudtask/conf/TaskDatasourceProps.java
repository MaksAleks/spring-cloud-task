package ru.max.springcloudtask.conf;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component
public class TaskDatasourceProps {

    String url;
    String username;
    String password;
    String driverClassName;
}
