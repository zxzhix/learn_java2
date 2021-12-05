package starter;


import lombok.Data;

import java.util.List;

@Data
public class Klass { 
    
    List<MyStudent> students;
    
    public void dong(){
        System.out.println(this.getStudents());
    }
    
}
