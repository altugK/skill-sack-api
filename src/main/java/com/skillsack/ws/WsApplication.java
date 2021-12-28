package com.skillsack.ws;

import com.skillsack.ws.departments.DepartmentService;
import com.skillsack.ws.departments.vm.DepartmentSubmitVM;
import com.skillsack.ws.employees.EmployeeService;
import com.skillsack.ws.employees.vm.EmployeeSubmitVM;
import com.skillsack.ws.skills.SkillService;
import com.skillsack.ws.skills.vm.SkillSubmitVM;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EnableSwagger2
public class WsApplication {

    public static void main(String[] args) {
        SpringApplication.run(WsApplication.class, args);
    }

    @Bean
    @Profile("dev")
    CommandLineRunner createInitialData(EmployeeService employeeService, DepartmentService departmentService, SkillService skillService) {
        return (args) -> {

            for (int i = 1; i <= 25; i++) {
                DepartmentSubmitVM departmentSubmitVM = new DepartmentSubmitVM();
                departmentSubmitVM.setName("department " + i);
                departmentService.save(departmentSubmitVM);

                SkillSubmitVM skillSubmitVM = new SkillSubmitVM();
                skillSubmitVM.setName("skill " + i);
                skillService.save(skillSubmitVM);

                EmployeeSubmitVM employee = new EmployeeSubmitVM();
                employee.setName("employee " + i);
                employee.setDepartment(Long.valueOf(i));
                employee.setSkill(Long.valueOf(i));
                employeeService.save(employee);
            }
        };
    }


    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2)
                .select()
                .apis(RequestHandlerSelectors.any())
                .paths(PathSelectors.any())
                .build();
    }


}
