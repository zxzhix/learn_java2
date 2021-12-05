package starter;


import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import school.ISchool;

import javax.annotation.Resource;

@Data
public class MySchool implements ISchool {
    
    // Resource 
    @Autowired(required = true) //primary
    Klass class1;
    
    @Resource(name = "student100")
    MyStudent student100;
    
    @Override
    public void ding(){
    
        System.out.println("Class1 have " + this.class1.getStudents().size() + " students and one is " + this.student100);
        
    }
    
}
