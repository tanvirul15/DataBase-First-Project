
package databaseproject;

import java.sql.*;
import java.net.URL;
import java.util.ResourceBundle;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
//import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javax.swing.JOptionPane;
import javafx.collections.*;
import javafx.scene.control.cell.PropertyValueFactory;

public class FXMLDocumentController implements Initializable {
  
  
    
  //Variable Declearation -------------------------------------------------------------------------  
    @FXML private TextField tname;
    @FXML private TextField tid;
    @FXML private TextField tgpa;
    @FXML private TextField temail;
     @FXML private TextField delete_id;
    Connection connection;
    Statement stm;
    
  
    @FXML private TableView<Student> table;
    @FXML private TableColumn<Student,String> nameCol;
    @FXML private TableColumn<Student,String> idCol;
    @FXML private TableColumn<Student,Double> gpaCol;
    @FXML  private TableColumn<Student,String> emailCol;
    ObservableList<Student> list=FXCollections.observableArrayList();
     
    
    
    
    
    
    //----------------------------------------------Insert Button----------------------------------
    @FXML
    private void AddData(ActionEvent event) {
        String name=tname.getText();
        String id=tid.getText();
        double gpa=0;
        
         try {
             
         gpa=Double.parseDouble(tgpa.getText());
       } catch (Exception e) {
            Alert alert=new Alert(Alert.AlertType.WARNING);   //Insert Button----------------------------------
           alert.setTitle("");
           alert.setContentText("Enter Valid Information!");
           alert.showAndWait();
           return;
       
       }
        String email=temail.getText();
        boolean flag=name.isEmpty()||id.isEmpty()||gpa==0||email.isEmpty();
        
        if(flag){
        Alert alert=new Alert(Alert.AlertType.ERROR);            //Insert Button----------------------------------
        alert.setTitle("");
        alert.setContentText("Please Enter All Information!");
        alert.showAndWait();
        return;
        }
        String str="INSERT INTO `test`.`stdnt` (`sname`, `sid`, `sgpa`, `semail`) VALUES ('"+name+"', '"+id+"', '"+gpa+"', '"+email+"');";
        try {
            int sc=stm.executeUpdate(str);
        } catch (SQLException ex) {
              Alert alert=new Alert(Alert.AlertType.ERROR);
             alert.setContentText("Error Occure During Insert");
            alert.showAndWait();
            return;
        }
        Alert al=new Alert(Alert.AlertType.CONFIRMATION);
        al.setTitle("");
        al.setContentText("Success");
        al.showAndWait();
        }
    //=========================================Finished Insert Button===============================
     @FXML
     private void LoadData(ActionEvent event) {
        try {
            list.clear();
            ResultSet rs=stm.executeQuery("SELECT * FROM test.stdnt;");
            while(rs.next()){
                String vname=rs.getString("sname");
                String vid=rs.getString("sid");
                Double vgpa=rs.getDouble("sgpa");
                String vemail=rs.getString("semail");
                list.add(new Student(vname, vid, vgpa, vemail));
                
                
            
            
            
            }
            
            
            
        } catch (SQLException ex) {
            Alert al=new Alert(Alert.AlertType.INFORMATION);
        al.setTitle("");
        al.setContentText("Error while reading data");
        al.showAndWait();
        }
        
       
         
      }
    
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        table.setEditable(true);
         nameCol.setCellValueFactory(new PropertyValueFactory<Student,String>("Name"));
         idCol.setCellValueFactory(new PropertyValueFactory<Student,String>("Id"));
        gpaCol.setCellValueFactory(new PropertyValueFactory<Student,Double>("Gpa"));
        emailCol.setCellValueFactory(new PropertyValueFactory<Student,String>("Email"));
        table.setItems(list);
       
        
        //Database Connectivity
        try {
            Class.forName("com.mysql.jdbc.Driver");
           connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/test", "root","root");
           stm=connection.createStatement();
           
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
    }    
    @FXML
    private void ClearList(ActionEvent event) {
        list.clear();
    
    }
    
    @FXML
    private void DeleteSelected(ActionEvent event) throws SQLException {
       String getId=delete_id.getText();
       String qry="DELETE FROM `test`.`stdnt` WHERE `sid`='"+getId+"';";
       int f=stm.executeUpdate(qry);
       
       Alert alert=new Alert(Alert.AlertType.CONFIRMATION);
       alert.setTitle("");
       alert.setContentText("Deleted..!");
       
       if(f==0){
       alert=new Alert(Alert.AlertType.ERROR);
       alert.setContentText("ÏD Not Found");
        }
        alert.showAndWait();
       
    }
    


}
