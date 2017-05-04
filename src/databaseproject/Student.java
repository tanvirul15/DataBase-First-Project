
package databaseproject;
import javafx.beans.property.*;
//import static javafx.beans.property.DoubleProperty.doubleProperty;


public class Student {
    private final StringProperty name;
    private final StringProperty email;
    private final StringProperty id;
    private final DoubleProperty gpa;

    public  Student(String nm, String student_id,double s_gpa,String s_email) {
        this.name=new SimpleStringProperty(nm);
        this.email=new SimpleStringProperty(s_email);
        this.id = new SimpleStringProperty(student_id);
        this.gpa = new SimpleDoubleProperty(s_gpa);
    }

    public String getName() {
        return name.get();
    }

    public String getEmail() {
        return email.get();
    }

    public String getId() {
        return id.get();
    }

    public Double getGpa() {
        return gpa.get();
    }
    
    
    
}
